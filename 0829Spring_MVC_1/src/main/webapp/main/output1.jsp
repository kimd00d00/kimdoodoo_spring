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
	이름:${vo.name }<br>
	성별:${vo.sex }<br>
	지역:${vo.loc }<br>
	<c:if test="${vo.hobby!=null }">
		<c:forEach var="ho" items="${vo.hobby}">
			<li>${vo.hobby }</li>
		</c:forEach>
	</c:if>
	<c:if test="${vo.hobby==null }">
	</c:if>
</body>
</html>