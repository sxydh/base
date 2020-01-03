<%@ page pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta
    http-equiv="Content-Type"
    content="text/html; charset=UTF-8">
<title>Picture Upload And Download</title>
</head>
<body>
    <h2 style="color:red;">${hint}</h2>
    <form
        action="/servlet/picture/post"
        method="post"
        enctype="multipart/form-data">
        Picture file:
        <input
            type="file"
            name="sample" />
        <input
            type="submit"
            value="Upload" />
    </form>
    <hr />
    <form
        action="/servlet/picture/get"
        method="post">
        Picture name:
        <input
            type="text"
            name="name" />
        <input
            type="submit"
            value="Download" />
    </form>
</body>
</html>