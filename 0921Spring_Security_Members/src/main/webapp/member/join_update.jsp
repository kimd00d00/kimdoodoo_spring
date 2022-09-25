<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery.js"></script>
<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
<script type="text/javascript" src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript">
$(function(){
	$('#postBtn').click(function(){
		new daum.Postcode({
			oncomplete:function(data){
				$('#post').val(data.zonecode);
				$('#addr1').val(data.address);
			}
		}).open()
	})
});
</script>
</head>
<body>
	<div class="container">
		<h1 class="text-center">회원수정</h1>
		<div class="row">
			<form method="post" action="../member/join_update_ok.do">
			<table class="table">
				<tr>
					<th width="10%" class="text-right warning">ID</th>
					<td width="90%">
						<input type="text" class="input-sm" size=20 name="id" readonly="readonly" id="myId" value="${vo.id }">
						<input type="button" class="btn btn-sm btn-danger" value="아이디중복체크" id="idCheck">
					</td>
				</tr>
				<tr>
					<th width="10%" class="text-right warning">이름</th>
					<td width="90%">
						<input type="text" class="input-sm" size=20 name="name" value="${vo.name }">
					</td>
				</tr>
				<tr>
					<th width="10%" class="text-right warning">성별</th>
					<td width="90%">
						<input type="radio" name="sex" value="남자" ${vo.sex=='남자'?'checked':'' }>남자
						<input type="radio" name="sex" value="여자" ${vo.sex=='여자'?'checked':'' }>여자
					</td>
				</tr>
				<tr>
					<th width="10%" class="text-right warning">이메일</th>
					<td width="90%">
						<input type="text" name="email" size=70 class="input-sm" value="${vo.email }">
					</td>
				</tr>
				<tr>
					<th width="10%" class="text-right warning">우편번호</th>
					<td width="90%">
						<input type="text" name="post" size=10 readonly="readonly" class="input-sm" id="post" value="${vo.post }">
						<input type="button" class="btn btn-sm btn-danger" value="우편번호검색" id="postBtn">
					</td>
				</tr>
				<tr>
					<th width="10%" class="text-right warning">주소</th>
					<td width="90%">
						<input type="text" name="addr1" size=70 class="input-sm" id="addr1" value="${vo.addr1 }">
					</td>
				</tr>
				<tr>
					<th width="10%" class="text-right warning">상세주소</th>
					<td width="90%">
						<input type="text" name="addr2" size=70 class="input-sm" value="${vo.addr2 }">
					</td>
				</tr>
				<tr>
					<th width="10%" class="text-right warning">전화번호</th>
					<td width="90%">
						<input type="text" size=10 class="input-sm" value="010" readonly="readonly">-
						<input type="text" name="tel1" size=10 class="input-sm" value="${fn:substring(vo.tel,4,8) }">-
						<input type="text" name="tel2" size=10 class="input-sm" value="${fn:substring(vo.tel,9,13) }">
					</td>
				</tr>
				<tr>
					<th width="10%" class="text-right warning">소개</th>
					<td width="90%">
						<textarea rows="3" cols="70" name="content">${vo.content }</textarea>
					</td>
				</tr>
				<tr>
					<td colspan="2" class="text-center">
						<input type="submit" value="회원수정" class="btn btn-sm btn-primary">
						<input type="button" value="취소" class="btn btn-sm btn-danger" onclick="javascript:history.back()">
					</td>
				</tr>
			</table>
			</form>
		</div>
	</div>
</body>
</html>