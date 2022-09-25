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
	<div class="container">
		<div class="row">
			<div class="text-center">
				<form method="post" action="../recipe/list.do">
					<input type="text" size=30 class="input-sm" name="type">
					<input type="submit" class="btn btn-sm btn-primary" value="검색">
				</form>
			</div>
		</div>
		<div style="height:20px"></div>
		<div class="row">
			<c:forEach var="vo" items="${list }">
				<div class="col-md-4">
				    <div class="thumbnail">
				      <a href="../recipe/detail.do?cno=${vo.no }">
				        <img src="${vo.poster }" style="width:100%">
				        <div class="caption">
				          <p style="font-size:8px">${vo.title }</p>
				          <p style="font-size:8px">${vo.chef }</p>
				        </div>
				      </a>
			    	</div>
			    </div>
			</c:forEach>
		</div>
		<div style="height:20px"></div>
		<div class="row">
			<div class="text-center">
				<ul class="pagination pagination-lg">
				  <c:if test="${startpage>1 }">
				  	<li><a href="../recipe/list.do?page=${startpage-1 }&type=${type}">&lt;</a></li>
				  </c:if>
				  <c:forEach var="i" begin="${startpage }" end="${endpage }">
				  	<c:if test="${curpage==i }">
				  		<c:set var="style" value="active"/>
					</c:if>
				  	<c:if test="${curpage!=i }">
				  		<c:set var="style" value=""/>
					</c:if>
					<li class="${style}"><a href="../recipe/list.do?page=${i }&type=${type}">${i }</a></li>
				  </c:forEach>
				  <c:if test="${endpage<totalpage }">
				  	<li><a href="../recipe/list.do?page=${endpage+1 }&type=${type}">&gt;</a></li>
				  </c:if>
				</ul>
			</div>
		</div>
	</div>
</body>
</html>