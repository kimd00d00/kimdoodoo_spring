<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.min.js"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
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
			<table class="table">
				<tr>
					<th width=30% class="text-right">ID</th>
					<td width=70%>
						<input type="text" class="input-sm" name="id" v-model="id">
					</td>
				</tr>
				<tr>
					<th width=30% class="text-right">PW</th>
					<td width=70%>
						<input type="text" class="input-sm" name="pwd" v-model="pwd">
					</td>
				</tr>
				<tr>
					<td colspan="2" class="text-center">
						<input type="submit" value="로그인" class="btn btn-sm btn-danger" id="loginBtn">
					</td>
				</tr>
			</table>
		</div>
	</div>
<script>
	new Vue({
		el:'.container',//element(태그), 얘가 제어하는 영역이 어디인지 지정하는 것
		data:{
			id:'', //해당 태그에 v-model 속성을 부여하여 연결
			pwd:'',
			result:{} //객체 단위로 결과값을 받겠다
		},
		methods:{
			login:function(){
				 if(this.id==""){
					 alert("아이디를 입력하세요!");
					 return;
				 }else if(this.pwd=""){
					 alert("비밀버노를 입력하세요!");
					 return;
				 }
				 axios.get("http://localhost:8080/web/member/vue_login_ok.do",{
					 params: {
						 id:this.id,
						 pwd:this.pwd
					 }//데이터 보내줌
				 }).then(result=>{
					 //결과값을 받는다
					 console.log(result.data);
					 this.result=result.data;
					 if(this.result.msg=="NOID"){
						 alert("아이디가 존재X");
						 this.id="";
						 this.pwd="";
						 return;
					 }else if(this.result.msg=="NOPWD"){
						 alert("비번 틀림");
						 this.pwd="";
						 return;
					 }else{
						 alert(this.result.name+"님 안녕하세요.");
						 location.href="../main/main.do";
					 }
				 })
			}
		}
	})
</script>
</body>
</html>