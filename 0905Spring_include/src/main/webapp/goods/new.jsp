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
	<h1>상품</h1>
	<div class="container">
		<div class="row">
			<c:forEach var="vo" items="${list }">
			 <div class="col-md-4">
			    <div class="thumbnail">
			      <a href="../goods/new_detail.do?no=${vo.no }">
			        <img src="${vo.goods_poster }" style="width:300px; height:300px;">
			        <div class="caption">
			          <p>${vo.goods_name }</p>
			          <p>${vo.goods_price }</p>
			        </div>
			      </a>
			    </div>
			  </div>
			</c:forEach>
		</div>
		<div style="height:20px"></div>
		<div class="row">
			<div class="text-center">
				<a href="../goods/new.do?page=${curPage>1?curPage-1:curPage }" class="btn btn-sm btn-info">이전</a>
				${curPage } page / ${totalPage } pages
				<a href="../goods/new.do?page=${curPage<totalPage?curPage+1:curPage }" class="btn btn-sm btn-info">다음</a>
			</div>
		</div>
		<div style="height:50px"></div>
	</div>
</body>
</html>