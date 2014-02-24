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
<h1> See you soon!</h1>

<ul>
<a href=<%=request.getContextPath()%>/Login.jsp>Log in</a>
</ul>

<div id= usersLetters><p>You have been logged out of your account.</p></div>


<% request.getSession(false);
if(session!=null)
session.invalidate(); %>

</body>
</html>