<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery.js"></script>
<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
<script type="text/javascript" src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript">
$(function(){
	$('#okId').hide();
	$('#idCheck').click(function(){
		$( "#dialog" ).dialog({
			autoOpen:false,
			width:390,
			height:250,
			modal:true //완전 처리된 이후에 띄울 수 있도록 세팅
		}).dialog("open");
	})
	$('#okBtn').click(function(){
		$('#myId').val($('#id').val());
		$('#dialog').dialog("close");
	})
	$('#postBtn').click(function(){
		
	})
	$('#idBtn').click(function(){
		let id=$('#id').val();
		if(id.trim()==""){ //입력값이 없을 때 포커스
			$('#id').focus();
			return;
		}
		//스프링 서버로 전송
		/* axios.get(url,{params:{}}).then()
		axios.post(url,...) */
		$.ajax({
			type:'post',
			url:'../member/idcheck.do',
			data:{"id":id},
			success:function(result){
				let res = result.trim();
				if(res==='YES'){
					let msg ='<span style="color:blue">'+id+'(은)는 사용 가능합니다.</span>';
					$('#result').html(msg);
					$('#okId').show();
				}else{
					let msg ='<span style="color:blue">'+id+'(은)는 사용중인 아이디입다.</span>';
					$('#result').html(msg);
				}
			}
		})
	})
});
</script>
</head>
<body>
	<div class="container">
		<h1 class="text-center">회원가입</h1>
		<div class="row">
			<form method="post" action="../member/join_ok.do">
			<table class="table">
				<tr>
					<th width="10%" class="text-right warning">ID</th>
					<td width="90%">
						<input type="text" class="input-sm" size=20 name="id" readonly="readonly" id="myId">
						<input type="button" class="btn btn-sm btn-danger" value="아이디중복체크" id="idCheck">
					</td>
				</tr>
				<tr>
					<th width="10%" class="text-right warning">PW</th>
					<td width="90%">
						<input type="password" class="input-sm" size=20 name="pwd">
					</td>
				</tr>
				<tr>
					<th width="10%" class="text-right warning">이름</th>
					<td width="90%">
						<input type="text" class="input-sm" size=20 name="name">
					</td>
				</tr>
				<tr>
					<th width="10%" class="text-right warning">성별</th>
					<td width="90%">
						<input type="radio" name="sex" value="남자" checked="checked">남자
						<input type="radio" name="sex" value="여자">여자
					</td>
				</tr>
				<tr>
					<th width="10%" class="text-right warning">생년월일</th>
					<td width="90%">
						<input type="date" name="birthday" size=20>
					</td>
				</tr>
				<tr>
					<th width="10%" class="text-right warning">이메일</th>
					<td width="90%">
						<input type="text" name="email" size=70 class="input-sm">
					</td>
				</tr>
				<tr>
					<th width="10%" class="text-right warning">우편번호</th>
					<td width="90%">
						<input type="text" name="post" size=10 readonly="readonly" class="input-sm" id="post">
						<input type="button" class="btn btn-sm btn-danger" value="우편번호검색" id="postBtn">
					</td>
				</tr>
				<tr>
					<th width="10%" class="text-right warning">주소</th>
					<td width="90%">
						<input type="text" name="addr1" size=70 class="input-sm" id="addr1">
					</td>
				</tr>
				<tr>
					<th width="10%" class="text-right warning">상세주소</th>
					<td width="90%">
						<input type="text" name="addr2" size=70 class="input-sm">
					</td>
				</tr>
				<tr>
					<th width="10%" class="text-right warning">전화번호</th>
					<td width="90%">
						<input type="text" size=10 class="input-sm" value="010" readonly="readonly">-
						<input type="text" name="tel1" size=10 class="input-sm">-
						<input type="text" name="tel2" size=10 class="input-sm">
					</td>
				</tr>
				<tr>
					<th width="10%" class="text-right warning">소개</th>
					<td width="90%">
						<textarea rows="3" cols="70" name="content"></textarea>
					</td>
				</tr>
				<tr>
					<td colspan="2" class="text-center">
						<input type="submit" value="회원가입" class="btn btn-sm btn-primary">
						<input type="button" value="취소" class="btn btn-sm btn-danger" onclick="javascript:history.back()">
					</td>
				</tr>
			</table>
			</form>
		</div>
		<div id="dialog" title="아이디 중복 체크" style="display:none">
		  <table class="table">
		  	<tr>
		  		<td>
		  			아이디:<input type="text" name="id" size=15 class="input-sm" id="id">
		  			<input type="button" class="btn btn-sm btn-success" id="idBtn" value="아이디체크">
		  		</td>
		  	</tr>
		  	<tr>
		  		<td class="text-center" id="result"></td>
		  	</tr>
		  	<tr id="okId">
		  		<td class="text-center">
		  			<input type="button" class="btn btn-sm btn-info" id="okBtn" value="확인">
		  		</td>
		  	</tr>
		  </table>
		</div>
	</div>
</body>
</html>