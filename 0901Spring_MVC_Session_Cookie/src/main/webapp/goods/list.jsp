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
<link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery.js"></script>
<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
<script>
  $( function() {
    $('#cookieView').on("click",function(){
    	$('#dialog').dialog({
    		title:'쿠키 전체보기',
    		width:960,
    		height:600,
    		modal:true, //얘가 떠있을 땐 다른 창은 제어가 안되도록 하는것
    		autoOpen:false //클릭했을때 열려야하니까
    	}).dialog("open");
    })
  });
</script>
</head>
<body>
	<div class="container">
		<div class="row">
			<c:forEach var="vo" items="${list }">
				<div class="col-md-4">
			      <div class="thumbnail">
			        <a href="detail_before.do?no=${vo.no }">
			          <img src="${vo.goods_poster }" style="width:100%">
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
				<a href="list.do?page=${curPage>1?curPage-1:curPage }" class="btn btn-sm btn-danger">이전</a>
				${curPage } page / ${totalPage } pages
				<a href="list.do?page=${curPage<totalPage?curPage+1:curPage }" class="btn btn-sm btn-danger">다음</a>
			</div>
		</div>
		<div style="height:50px"></div>
		<div class="row">
			<h3>최근 본 상품</h3>
			<a href="cookie_all_delete.do" class="btn btn-xs btn-danger">쿠키전체삭제</a>
			<span class="btn btn-xs btn-danger" id="cookieView">쿠키전체보기</span>
			<hr>
			<c:if test="${size<1 }">
				<span style="color:orange">방문 기록이 없습니다.</span>	
			</c:if>
			<c:if test="${size>0 }">
				<c:forEach var="c" items="${cList }" varStatus="s">
				<c:if test="${s.index<6 }">
					<div class="col-md-2">
				      <div class="thumbnail">
				        <a href="detail_before.do?no=${c.no }">
				          <img src="${c.goods_poster }" style="width:100%">
				          <div class="caption text-center">
				          <p><a href="cookie_delete.do?no=${c.no }" class="btn btn-xs btn-danger">삭제</a></p>
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
	</head>
	<div id="dialog" title="Basic dialog" style="display:none">
		<c:if test="${size<1 }">
				<span style="color:orange">방문 기록이 없습니다.</span>	
			</c:if>
			<c:if test="${size>0 }">
				<c:forEach var="c" items="${cList }" varStatus="s">
					<div class="col-md-2">
				      <div class="thumbnail">
				        <a href="detail_before.do?no=${c.no }">
				          <img src="${c.goods_poster }" style="width:100px;height:100px;">
				        </a>
			      	  </div>
			    	</div>
				</c:forEach>
			</c:if>
	</div>
</body>
</html>