<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container{
	margin-top: 50px;
}
.row{
	margin : 0px auto;
	width : 100%;
}
</style>
</head>
<body>
	<div class="container">
		<div class="row">
			<c:forEach var="vo" items="${list }">
				<div class="col-md-3">
			      <div class="thumbnail">
			        <a href="goods_new_detail.do?no=${vo.no }">
			          <img src="${vo.poster }" style="width:100%">
			          <div class="caption">
			            <p>${vo.name }</p>
			            <p>${vo.price }</p>
			          </div>
			        </a>
			      </div>
			    </div>
			</c:forEach>
		</div>
		<div style="height:20px"></div>
		<div class="row">
			<div class="text-center">
				<c:if test="${curPage>1 }">
					<a href="goods_new.do?page=${curPage-1 }" class="btn btn-sm btn-danger">이전</a>
				</c:if>
				<a href="#">${curPage } page / ${totalPage } pages</a>
				<c:if test="${curPage<totalPage }">
					<a href="goods_new.do?page=${curPage+1 }" class="btn btn-sm btn-primary">다음</a>
				</c:if>
			</div>
		</div>
	</div>
</body>
</html>