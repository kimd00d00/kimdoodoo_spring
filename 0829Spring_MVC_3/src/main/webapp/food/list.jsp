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
			        <a href="detail_before.do?fno=${vo.fno }">
			          <img src="${vo.poster }" alt="${vo.name }" style="width:100%">
			          <div class="caption">
			            <p>${vo.name }</p>
			          </div>
			        </a>
			      </div>
			    </div>
			</c:forEach>
		</div>
		<div class="row">
			<div class="text-center">
				<a href="list.do?page=${curPage>1?curPage-1:curPage }" class="btn btn-sm btn-danger">이전</a>
				${curPage } page / ${totalPage } pages
				<a href="list.do?page=${curPage<totalPage?curPage+1:curPage }" class="btn btn-sm btn-danger">다음</a>
			</div>
		</div>
		<div style="height:50px"></div>
		<div class="row">
			<h3>최근 본 상품</h3>
			<a href="cookie_all_delete.do" class="btn btn-xs btn-danger">쿠키전체삭제</a>
			<hr>
			<c:if test="${size<1 }">
				<span style="color:orange">방문 기록이 없습니다.</span>	
			</c:if>
			<c:if test="${size>0 }">
				<c:forEach var="c" items="${cList }" varStatus="s">
				<c:if test="${s.index<6 }">
					<div class="col-md-1">
				      <div class="thumbnail">
				        <a href="detail_before.do?no=${c.fno }">
				          <img src="${c.poster }" style="width:100%">
				          <div class="caption text-center">
				          <p><a href="cookie_delete.do?fno=${c.fno }" class="btn btn-xs btn-danger">삭제</a></p>
				          </div>
				        </a>
			      	  </div>
			    	</div>
			    </c:if>
				</c:forEach>
			</c:if>
		</div>
		<div style="height:50px"></div>
	</div>
</body>
</html>