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
	width : 900px;
}
h1{
	text-align:center;
}
</style>
</head>
<body>
	<div class="container">
		<h1>게시판 목록</h1>
		<div class="row">
			<table class="table">
				<tr>
					<td>
						<a href="insert.do" class="btn btn-sm btn-primary">새글</a>
					</td>
				</tr>
			</table>
			<table class="table" style="margin:0px auto;">
				<tr class="success">
					<th class="text-center" width=10%>번호</th>
					<th class="text-center" width=45%>제목</th>
					<th class="text-center" width=15%>이름</th>
					<th class="text-center" width=20%>작성일</th>
					<th class="text-center" width=10%>조회수</th>
				</tr>
				<c:set var="count" value="${totalCount }"/>
				<c:forEach var="vo" items="${list }">
				<tr>
					<td class="text-center" width=10%>${count }</td>
					<td width=45%>
						<c:if test="${vo.group_tab>0 }">
							<c:forEach var="i" begin="1" end="${vo.group_tab }">
								&nbsp;
							</c:forEach>
							<img src="re.png" style="width:20px;height:20px;">
						</c:if>
						<c:if test="${vo.subject=='관리자가 삭제한 게시물입니다.' }">
							<span style="color:gray">${vo.subject }</span>
						</c:if>
						<c:if test="${vo.subject!='관리자가 삭제한 게시물입니다.' }">
							<a href="detail.do?no=${vo.no }">${vo.subject }</a>
						</c:if>
						
					</td>
					<td class="text-center" width=15%>${vo.name }</td>
					<td class="text-center" width=20%>${vo.dbday }</td>
					<td class="text-center" width=10%>${vo.hit }</td>
				</tr>
				<c:set var="count" value="${count-1 }"/>
				</c:forEach>
			</table>
			<table class="table">
				<tr>
					<td class="text-left">
						<form method="post" action="find.do">
							<select name="fd">
								<option value="name">이름</option>
								<option value="subject">제목</option>
								<option value="content">내용</option>
							</select>
							<input type=text name=ss size=20 class="input-sm">
							<button class="btn btn-sm btn-success">검색</button>
						</form>
					</td>
					<td class="text-right">
						<a href="list.do?page=${curPage>1?curPage-1:curPage }" class="btn btn-sm btn-danger">이전</a>
						${curPage } page / ${totalPage } pages
						<a href="list.do?page=${curPage<totalPage?curPage+1:curPage }" class="btn btn-sm btn-success">다음</a>
					</td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>