<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core' %>
<html>
<head>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Springweb - login</title>
</head>
<body>
<h2>Please log in</h2><br>
<form method="POST" action="<c:url value='j_acegi_security_check'/>">
	Username: <input type="text" name="j_username"><br>
	Password: <input type="password" name="j_password"><br>
	<input type="submit" value="Log in >>">
</form>
</body>
</html>