<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="juniors.server.core.data.User" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>My private cabinet</title>
	<link rel="stylesheet" type="text/css" href="cabinet.css"/>
</head>
<body>
<%
	User user = (User)request.getSession().getAttribute("user");
%>
	<div class="header">
		<img src="" class="avatar"/>
		<div class="account">
			Your name: <%= " " + user.getUserName() %><br>
			Your id:   <%= " " + user.getUserId().toString() %>
		</div>
		<a class="logout" href="/SimpleServer/LogoutHandler">Logout</a>
	</div>
	
	<div class="content">
		<br>
		Content here
	</div>
</body>
</html>