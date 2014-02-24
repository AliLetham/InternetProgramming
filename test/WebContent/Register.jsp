<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="${pageContext.request.contextPath}/openLetters.css" />
<title>Insert title here</title>
</head>
<body>

<h1> Welcome</h1>
<ul>
<a href=<%=request.getContextPath()%>/Login.jsp>Log in</a>
</ul>
<div id= usersLetters> Please enter your desired username and password</div>
<form name="input" action="Register" method="POST">
Username: <input type="text" name="username">
Password: <input type="password" name="password">
<input type="submit" value="Register">
</form>
</body>
</html>