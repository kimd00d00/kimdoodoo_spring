<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.container{
  margin-top:50px;
}
.row{
  margin:0px auto;
  width:420px;
}
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	$('#loginBtn').click(function(){
		let id = $('#id').val();
		if(id.trim()==""){
			$('#id').focus();
			//$('#id') == document.getElementId("id")
			return;
		}
		let pwd = $('#pwd').val();
		if(pwd.trim()==""){
			$('#pwd').focus();
			return;
		}
		
		$.ajax({
			type:'post',
			url:'../member/ajax_login_ok.do',
			data:{"id":id, "pwd":pwd},
			success:function(result){
				if(result=='NOID'){
					alert("아이디가 없습니다!");
					$('#id').val("");
					$('#pwd').val("");
					$('#id').focus();
				}else if(result=='NOPWD'){
					alert("비밀번호가 틀립니다!");
					$('#pwd').val("");
					$('#pwd').focus();
				}else{
					alert(result+"님 로그인되었습니다.");
					location.href="../main/main.do";
				}
			}
		})
	})
})
</script>
</head>
<body>
	<div class="container">
		<h1 class="text-center">로그인</h1>
		<div class="row">
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
						<input type="button" value="로그인" class="btn btn-sm btn-danger" id="loginBtn">
					</td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>