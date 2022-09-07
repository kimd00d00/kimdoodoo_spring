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
			        <a href="detail.do?mno=${vo.mno }">
			          <img src="${vo.poster }" style="width:240px; height:300px;">
			          <div class="caption">
			            <p>${vo.title }</p>
			          </div>
			        </a>
			      </div>
			    </div>
			</c:forEach>
		</div>
	<%-- 	<div class="row">
			<div class="text-center">
				<a href="list.do?page=${curPage>1?curPage-1:curPage }" class="btn btn-sm btn-danger">이전</a>
				${curPage } page / ${totalPage } pages
				<a href="list.do?page=${curPage<totalPage?curPage+1:curPage }" class="btn btn-sm btn-danger">다음</a>
			</div>
		</div> --%>
	</div>
</body>
</html>