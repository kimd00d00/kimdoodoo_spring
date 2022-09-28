<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LOGIN</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<style type="text/css">
.container{
  margin-top:50px;
}
.row{
  margin:0px auto;
  width:420px;
}
</style>
</head>
<body>
	<div class="container">
		<h1 class="text-center">로그인</h1>
		<div class="row">
			<form method="post" action="../member/login_ok.do">
			<table class="table">
				<tr>
					<th width=30% class="text-right">ID</th>
					<td width=70%>
						<input type="text" class="input-sm" name="id" id="id">
					</td>
				</tr>
				<tr>
					<th width=30% class="text-right">PW</th>
					<td width=70%>
						<input type="text" class="input-sm" name="pwd" id="pwd">
					</td>
				</tr>
				<tr>
					<td colspan="2" class="text-center">
						<input type="button" value="로그인" class="btn btn-sm btn-danger">
					</td>
				</tr>
			</table>
			</form>
		</div>
	</div>
</body>
</html>