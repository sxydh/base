function endRes(res, suc, err) {
    if (err) {
        res.writeHead(300);
        res.write(err + "");
    } else {
        res.writeHead(200);
        res.write(suc + "");
    }
    res.end();
}

var http = require('http');
var dbc = require('./components/js/dbc.js')
var fs = require('fs');
var url = require('url');
var formidable = require('formidable')

//create a server object:
http.createServer(function (req, res) {
    res.setHeader('Access-Control-Allow-Origin', req.headers.origin);
    res.setHeader('Access-Control-Allow-Methods', 'POST, GET, OPTIONS, DELETE');
    res.setHeader('Access-Control-Allow-Headers', '*');
    var queryObj = url.parse(req.url, true).query;
    if (queryObj.protocol == "upload") {
        var form = new formidable.IncomingForm();
        form.uploadDir = "./components/img";
        form.keepExtensions = true;
        form.parse(req, function (pserr, fields, files) {
            if (!files.file) {
                endRes(res, "", "");
                return;
            }
            fs.rename(files.file.path, form.uploadDir + "/" + files.file.name, function (rnerr) {
                if (rnerr) {
                    fs.unlink(form.uploadDir + "/" + files.file.name);
                    endRes(res, "", rnerr);
                }
            });
            if (pserr) {
                endRes(res, "", pserr);
            }
            endRes(
                res,
                JSON.stringify({
                    url: form.uploadDir + "/" + files.file.name,
                    name: files.file.name
                }),
                "");
        });
    } else {
        if (req.method == "OPTIONS") {
            endRes(res, "", "");
        } else if (req.method == "POST") {
            var body = "";
            req.on('data', function (data) {
                body += data;
            });
            req.on('end', function () {
                body = JSON.parse(body);
                var protocol = body.protocol;
                if (protocol == "query") {
                    dbc.query(body.sql, body.params).then(rows => {
                        endRes(res, JSON.stringify({ "code": 200, "data": rows }), "");
                    }).catch(err => {
                        endRes(res, "", JSON.stringify({ "code": 300, "data": err + "" }));
                    });
                } else if (protocol == "execute") {
                    dbc.execute(body.data).then(() => {
                        endRes(res, JSON.stringify({ "code": 200, "data": "" }), "");
                    }).catch(err => {
                        endRes(res, "", JSON.stringify({ "code": 300, "data": err + "" }));
                    });
                } else if (protocol == "upload") {
                    fs.writeFile(body.file, body.text, function (err) {
                        if (err) {
                            endRes(res, "", err);
                        } else {
                            endRes(res, "", "");
                        }
                    });
                }
                else if (protocol == "download") {
                    fs.readFile(body.file, function read(err, data) {
                        if (err) {
                            endRes(res, "", err);
                        } else {
                            endRes(res, data, "");
                        }
                    });
                }
            });
        }
    }
}).listen(55343); //the server object listens on port 8080