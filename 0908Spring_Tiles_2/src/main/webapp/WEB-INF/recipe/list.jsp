<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<c:forEach var="vo" items="${list }">
				<div class="col-md-4">
					<div class="thumbnail">
						<a href="../recipe/detail.do?no=${vo.no }">
							<img src="${vo.poster }" style="width:100%">
							<div class="caption">
								<p>${vo.title }</p>
							</div>
						</a>
					</div>
				</div>
			</c:forEach>
		</div>
		<div style="height:20px"></div>
		<div class="row">
			<div class="text-center">
			<ul class="pagination">
				<c:if test="${startpage>1 }">
			  		<li><a href="../recipe/list.do?page=${startpage-1 }">&lt;</a></li>
			  	</c:if>
				  <c:forEach var="i" begin="${startpage }" end="${endpage }">
				  	<c:choose>
				  		<c:when test="${i==curpage }">
				  			<c:set var="style" value="class=active"/>
				  		</c:when>
				  		<c:otherwise>
				  			<c:set var="style" value=""/>
				  		</c:otherwise>
				  	</c:choose>
				  	<li ${style }><a href="../recipe/list.do?page=${i }">${i }</a></li>
				  </c:forEach>
				<c:if test="${endpage<totalpage }">
			  		<li><a href="../recipe/list.do?page=${endpage+1 }">&gt;</a></li>
			  	</c:if>
			</ul>
		</div>
		</div>
	</div>
</body>
</html>