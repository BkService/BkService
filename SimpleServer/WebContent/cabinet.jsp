<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="juniors.server.core.data.User" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>My private cabinet</title>
	<link rel="stylesheet" type="text/css" href="styles/cabinet.css"/>
</head>
<body>
<%
	//это убрать отсюда нафиг. это очень плохая заглушка =)
	User user = (User)request.getSession().getAttribute("user");
	String namePage = (String) request.getSession().getAttribute("pagetype");
	if(namePage == null || namePage.isEmpty())
		namePage = "main.jsp";
%>
	<div class="header">
		<img src="imgs/ava.jpg" class="avatar"/>
		<div class="account">
			<!-- get info about user from connectionManager -->
			Your name: <%= " " + user.getUserName() %><br>
			Your id:   <%= " " + user.getUserId().toString() %><br>
			User info....
		</div>
		<div class="logout"><a href="/SimpleServer/LogoutHandler">Logout</a></div>
	</div>
	
	<div>
		<div class="blockmenu">
			<span class="labelmenu">Menu</span>
			<table>
				<tr><td class="itemmenu">Main page</td></tr>
				<tr><td class="itemmenu">Upcoming events</td></tr>
				<tr><td class="itemmenu">My history of bets</td></tr>
				<tr><td class="itemmenu">Options</td></tr>
			</table>
		</div>
		<div class="workspace">
			<jsp:include page="<%=namePage%>"></jsp:include>
		</div>
	</div>
</body>
</html>