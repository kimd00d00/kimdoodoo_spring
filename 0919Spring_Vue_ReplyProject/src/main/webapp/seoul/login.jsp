<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.min.js"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<style type="text/css">
.container{
	margin-top: 30px;
}
.row{
	margin: 0px auto;
	width: 420px;
}
</style>
</head>
<body>
	<div class="container">
		<h1 class="text-center">Login</h1>
		<div class="row">
			<table class="table">
				<tr>
					<th width=30%>ID</th>
					<td width=70%><input type="text" size=15 ref="id" class="input-sm" v-model="id"></td>
				</tr>
				<tr>
					<th width=30%>PWD</th>
					<td width=70%><input type="password" size=15 ref="pwd" class="input-sm" v-model="pwd"></td>
				</tr>
				<tr>
					<td class="text-center" colspan="2">
						<input type="button" value="LOGIN" class="btn btn-sm btn-danger" @click="login()">
						<input type="button" value="JOIN" class="btn btn-sm btn-danger" @click="join()">
					</td>
				</tr>
			</table>
		</div>
	</div>
<script>
	new Vue({
		el:'.container',
		data:{
			id:'',
			pwd:'',
			res:''
		},
		//시작하자마자 내용을 출력할 게 아니므로 사용자 정의 메서드를 넣는다.
		methods:{
			login:function(){
				if(this.id.trim()==""){
					this.$refs.id.focus();
					return;
				}
				if(this.pwd.trim()==""){
					this.$refs.pwd.focus();
					return;
				}
				let _this=this;
				axios.get("http://localhost:8080/web/member/login_vue.do",{
					params:{
						id:_this.id,
						pwd:_this.pwd
					}
				}).then(function(result){
					_this.res = result.data;
					if(_this.res==='NOID'){
						alert("NO ID!!");
						_this.id="";
						_this.pwd="";
						_this.$refs.id.focus();
					}else if(_this.res==='NOPWD'){
						alert("NO PWD!!");
						_this.pwd="";
						_this.$refs.pwd.focus();
					}else{
						alert(_this.id+"님 로그인");
						location.href="../seoul/list.do";
					}
				})
			}
		}
	})
</script>
</body>
</html>