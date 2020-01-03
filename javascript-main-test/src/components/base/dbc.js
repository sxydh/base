const sqlite3 = require('sqlite3').verbose();

// open the database
let db = new sqlite3.Database('D:/Code/javascript-main-test/src/components/base/test.db', sqlite3.OPEN_READWRITE, (err) => {
    if (err) {
        console.error(err.message);
    } else {
        console.log('Connected to the database.');
    }
});

db.serialize(function () {
    db.run("DROP TABLE IF EXISTS lorem");
    db.run("CREATE TABLE lorem (info TEXT)");

    var stmt = db.prepare("INSERT INTO lorem VALUES (?)");
    for (var i = 0; i < 10; i++) {
        stmt.run("Ipsum " + i);
    }
    stmt.finalize();

    db.each("SELECT rowid AS id, info FROM lorem LIMIT $offset, $limit", { $offset: 0, $limit: 1 }, function (err, row) {
        console.log(row)
    });

    db.all("SELECT rowid AS id, info FROM lorem LIMIT $offset, $limit", { $offset: 0, $limit: 2 }, function (err, rows) {
        console.log(rows)
    });
});

db.close((err) => {
    if (err) {
        console.error(err.message);
    } else {
        console.log('Close the database connection.');
    }
});

setInterval(() => {

}, 2000);