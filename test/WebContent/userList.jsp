<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="${pageContext.request.contextPath}/openLetters.css" />
<title>Lists all Members</title>
</head>
<body>
<h1>Current Members</h1>

<ul>
<li><a href=<%=request.getContextPath()%>/Letter>Home</a></li>
<li><a href=<%=request.getContextPath()%>/Letter/all>All Letters</a></li>
<li><a href=<%=request.getContextPath()%>/Logout.jsp>Logout</a></li>
</ul>

<%  objects.Member user = (objects.Member)request.getSession().getAttribute("user");%>

 <% if (user.getUsername() == null) {  
 response.sendRedirect(request.getContextPath()+"/Login.jsp");
 }
 else {%>
 	 <p> You are signed in as <%=user.getUsername()%> </p> 
 	 <%
 	 } 
 	 %>
 	 
<c:forEach var="members" items="${memberList}">
  <div id="usersLetters">
   <p>  
   <a href=<%=request.getContextPath()%>/profile/${members.username}>${members.username}</a>
    </p> </div>
</c:forEach>
</body>
</html>