<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
         <%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="${pageContext.request.contextPath}/openLetters.css" /> 
<title>Profile Page</title>

</head>
<body>

<h1>${username}'s Page</h1>

<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.3/jquery.min.js" type="text/javascript"></script>
<script>
// http://stackoverflow.com/questions/2153917/how-to-send-a-put-delete-request-in-jquery
function readingUser(username){
	$.ajax({	
		url: "${pageContext.request.contextPath}/reading/"+username,
	    type: 'POST',
	    success: function(result) {
	       alert("You are now a Reader");
	       location.reload();
	    }
	});
}
</script>
<script>
// http://stackoverflow.com/questions/2153917/how-to-send-a-put-delete-request-in-jquery
function deleteUser(username){
	$.ajax({		
		url: "${pageContext.request.contextPath}/reading/"+username,
	    type: 'DELETE',	  
	    success: function(result) {
	       alert("You have stopped Reading this user");
	       location.reload();
	    }
	});
}
</script>


<c:if test="${readingCheck == true}">
<button type="button" onclick="deleteUser('${username}')">Stop Reading</button>
</c:if>
<c:if test="${readingCheck == false}">
<button type="button" onclick="readingUser('${username}')">Become a Reader</button>
</c:if>

<ul>
<li><a href=<%=request.getContextPath()%>/Letter>Home</a></li>
<li><a href=<%=request.getContextPath()%>/UserList>User List</a></li>
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


<c:forEach var="letterObject" items="${Letters}">
  <div id="usersLetters">
   <p>  
   PostID: <c:out value="${letterObject.letterID}"></c:out>  
          <c:out value="${letterObject.letterUser}"></c:out> 
    Says "<c:out value="${letterObject.letterContent}"></c:out>"    
    At <c:out value="${letterObject.timeDate}"></c:out>  
    </p></div>
</c:forEach>

</body>
</html>