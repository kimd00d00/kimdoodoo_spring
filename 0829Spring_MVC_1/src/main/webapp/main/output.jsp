<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	이름:${name }<br>
	성별:${sex }<br>
	지역:${loc }<br>
	<c:if test="${hobby!=null }">
		<c:forEach var="hobby" items="${hobby}">
			<li>${hobby }</li>
		</c:forEach>
	</c:if>
	<c:if test="${hobby==null }">
	</c:if>
</body>
</html>