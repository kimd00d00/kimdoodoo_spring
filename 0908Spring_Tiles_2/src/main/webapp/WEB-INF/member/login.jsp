<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.row1{
	margin: 0px auto;
	width: 420px;
	height: 800px;
}
</style>
<script src="https://unpkg.com/vue"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
</head>
<body>
	<div class="container">
		<div class="row row1">
		<form method="post" action="../member/login_ok.do">
			<table class="table">
			<caption><h3 class="text-center">로그인</h3></caption>
				<tr>
					<td width=30% class="text-right">ID</td>
					<td width=70% ><input type="text" name="id" size=15 class="input-sm"></td>
				</tr>
				<tr>
					<td width=30% class="text-right">PWD</td>
					<td width=70% ><input type="text" name="pwd" size=15 class="input-sm"></td>
				</tr>
				<tr>
					<td colspan="2" class="text-center">
						<input type=submit class="btn btn-sm btn-danger" value="로그인">
					</td>
				</tr>
			</table>
		</form>
		</div>
	</div>
</body>
</html>