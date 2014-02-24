<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="${pageContext.request.contextPath}/openLetters.css" />
<title>Log In Page</title>
</head>
<body>

<!--  making sure they can't visit the page if they are already logged in. -->
<% if (request.getSession().getAttribute("user") != null) {  
 response.sendRedirect(request.getContextPath()+"/Letter");
 }
%>
<h1>OpenLetters</h1>

<ul>
<li><a href=<%=request.getContextPath()%>/Register.jsp>Register</a></li>
</ul>




<form name="input" action="Login" method="POST">
<div id=usernameDiv>Username: <input type="text" name="username"></div>
<div id=passwordDiv>Password: <input type="password" name="password"></div>
<input type="submit" id = "LogInButton" value="Log In">
</form>
</body>
</html>