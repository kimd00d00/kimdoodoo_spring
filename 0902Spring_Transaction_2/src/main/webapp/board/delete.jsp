<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	$('#delBtn').click(function(){
		$('#delFrm').submit();
	})
})
</script>
</head>
<body>
	<div class="container">
	<h1>글 수정하기</h1>
	<form method="post" action="delete_ok.do" id="delFrm">
		<center>
			<input type="password" name="pwd">
			<input type="hidden" name="no" value="${no }">
			<input type=button value="삭제하기" class="btn btn-sm btn-danger" id="delBtn">
			<input type=button value="취소" class="btn btn-sm btn-warning" onclick="javascript:history.back()">
		</center>
	</form>
	</div>
</body>
</html>