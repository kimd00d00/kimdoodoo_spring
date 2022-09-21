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
	width: 350px;
}
</style>
<script src="https://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	$('#logBtn').click(function(){
		let id=$('#id').val();
		if(id.trim()===""){
			$('#id').focus();
			return;
		}
		let pwd=$('#pwd').val();
		if(pwd.trim()===""){
			$('#pwd').focus();
			return;
		}
		$.ajax({
			type:'post',
			url:'../member/login_ok.do',
			data:{"id":id, "pwd":pwd},
			success:function(result){
				let res = result.trim();
				if(res==='NOID'){
					alert("ID없음");
					$('#id').val("");
					$('#id').focus();
				}else if(res==='NOPWD'){
					alert("비번틀림");
					$('#pwd').val("");
					$('#pwd').focus();
				}else{
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
		<%-- @ResponseBody 로 처리하겠다~ --%>
		<div class="row row1">
			<table class="table">
				<tr>
					<th width="35%">ID</th>
					<td width="65%">
						<input type="text" class="input-sm" id="id" size=15>
					</td>
				</tr>
				<tr>
					<th width="35%">PW</th>
					<td width="65%">
						<input type="password" class="input-sm" id="pwd" size=15>
					</td>
				</tr>
				<tr>
					<!-- 자동 로그인 체크 영역 -->
					<td colspan="2">
						<input type="checkbox" id="ck" value="true">로그인 상태 유지
					</td>
				</tr>
				<tr>
					<td class="text-center" colspan="2">
						<input type="button" value="로그인" class="btn btn-sm btn-success" id="logBtn">
						<input type="button" value="취소" class="btn btn-sm btn-warning" onclick="javascript:history.back()">
					</td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>