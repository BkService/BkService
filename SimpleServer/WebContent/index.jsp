<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="juniors.server.core.data.User" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Its working</title>
	<link rel="stylesheet" type="text/css" href="index.css"/>
	<script src="index.js" type="text/javascript"></script>
</head>
<body>
	<h2>
		Welcome to <s>hell</s> Simple Server
	</h2>
	<%
		User user = (User)request.getSession().getAttribute("user");
		if(user != null) {
			request.getRequestDispatcher("/cabinet.jsp").forward(request, response);
		} else {
	%>
		<div id="signin" class="visible">
			<h4>Sign in, please:</h4>
			<form action="/SimpleServer/LoginHandler" method="post">
				<input name="uname" type="text" value="input name here"/>
				<input type="submit" value="SignIn"/>
			</form>	
			<br>
			<div class="action" onClick="replaceShow();">Registrations</div>
		</div>
		<div id="registration" class="none">
			Fields for registration new account will be here.<br><br>
			Input users data and<br>
			save it to storage on server.<br>
			If you have account of <s>hell</s> Simple Server,
			<div class="action" onClick="replaceShow();">Sign in here</div>
		</div>
	<%	}  %>
	
</body>
</html>