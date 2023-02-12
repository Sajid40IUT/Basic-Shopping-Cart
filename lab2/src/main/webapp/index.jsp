<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>
<a href="hello">Hello Servlet</a>
<form action="hello">
    <input type="text" name="username-field" placeholder="Enter your name">
    <input type="submit" >
</form>
</body>
</html>