<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
h1{
	text-align:center;
}
</style>
</head>
<body>
	<div class="container">
		<h1>상세보기</h1>
		<div class="row" style="height:650px">
			<table class="table">
				<tr>
					<th width=20% class="text-center warning">번호</th>
					<td width=30% class="text-center">${vo.no }</td>
					<th width=20% class="text-center warning">작성일</th>
					<td width=30% class="text-center">${vo.dbday }</td>
				</tr>
				<tr>
					<th width=20% class="text-center warning">이름</th>
					<td width=30% class="text-center">${vo.name }</td>
					<th width=20% class="text-center warning">조회수</th>
					<td width=30% class="text-center">${vo.hit }</td>
				</tr>
				<tr>
					<th width=20% class="text-center warning">제목</th>
					<td colspan="3">${vo.subject }</td>
				</tr>
				<tr>
					<td colspan="4" class="text-left" valign="top" height="200">
						<pre style="white-space:pre-wrap; border:none; background-color:white;">${vo.content }</pre>
					</td>
				</tr>
				<tr>
					<td colspan="4" class="text-right">
						<a href="#" class="btn btn-xs btn-info">수정</a>
						<a href="#" class="btn btn-xs btn-success">답글</a>
						<a href="#" class="btn btn-xs btn-danger">삭제</a>
						<a href="../board/list.do" class="btn btn-xs btn-primary">목록</a>
					</td>
				</tr>
			</table>
			<table class="table">
				<c:if test="${prevVO.no!=0 }">
				<tr>
					<td><span style="color:gray">이전글:</span>&nbsp;<a href="../board/detail.do?no=${prevVO.no }">${prevVO.subject }</a></td>
				</tr>
				</c:if>
				<c:if test="${nextVO.no!=0 }">
				<tr>
					<td><span style="color:gray">다음글:</span>&nbsp;<a href="../board/detail.do?no=${nextVO.no }">${nextVO.subject }</a></td>
				</tr>
				</c:if>
			</table>
		</div>
	</div>
</body>
</html>