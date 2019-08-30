<html>
<meta charset="UTF-8">
<body>
<h2>Hello World!</h2>
<form action="${pageContext.request.contextPath}/DevUser/submitFile" method="post" enctype="multipart/form-data">
    <input type="file" name="img">
    <input type="submit">
</form>
</body>
</html>
