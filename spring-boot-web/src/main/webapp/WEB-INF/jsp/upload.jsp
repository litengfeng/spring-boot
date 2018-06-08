<%--
  Created by IntelliJ IDEA.
  User: litengfeng
  Date: 2018/6/8
  Time: 17:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/upload" method="post" enctype="multipart/form-data">
文件1: <input type="file" name="file"/><br>
文件2  <input type="file" name="file"/><br>
文件3  <input type="file" name="file"/><br>
    <input type="submit" value="上传多个文件"/>
</form>
</body>
</html>
