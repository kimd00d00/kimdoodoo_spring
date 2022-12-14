<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container{
	margin-top: 50px;
}
.row{
	margin : 0px auto;
	width : 900px;
}
h1{
	text-align:center;
}
</style>
</head>
<body>
	<div class="container">
		<h1>검색 결과</h1>
		<div class="row">
			<table class="table" style="margin:0px auto;">
				<tr class="success">
					<th class="text-center" width=10%>번호</th>
					<th class="text-center" width=45%>제목</th>
					<th class="text-center" width=15%>이름</th>
					<th class="text-center" width=20%>작성일</th>
					<th class="text-center" width=10%>조회수</th>
				</tr>
				<c:forEach var="vo" items="${list }">
				<tr>
					<td class="text-center" width=10%>${vo.no }</td>
					<td width=45%><a href="detail.do?no=${vo.no }">${vo.subject }</a></td>
					<td class="text-center" width=15%>${vo.name }</td>
					<td class="text-center" width=20%>${vo.dbday }</td>
					<td class="text-center" width=10%>${vo.hit }</td>
				</tr>
				</c:forEach>
			</table>
			<table class="table">
				<tr>
					<td class="text-right">
						<a href="#" class="btn btn-sm btn-danger">이전</a>
						${curPage } page / ${totalPage } pages
						<a href="#" class="btn btn-sm btn-success">다음</a>
					</td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>