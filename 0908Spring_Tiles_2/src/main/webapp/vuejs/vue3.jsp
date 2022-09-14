<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://unpkg.com/vue@3"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
	.container{
		margin-top: 30px;
	}
	.row{
		margin: 0px auto;
		width: 100%;
	}
</style>
</head>
<body>
	<div class="container">
		<div class="row">
			<table class="table">
				<tr>
					<td>
						<input type=text size=30 class="input-sm" v-model="msg" ref="msg">
						<input type=button class="btn btn-sm btn-danger" value="입력" @click="myclick()"> 
					</td>
				</tr>
				<tr>
					<td>{{msg}}</td>
				</tr>
			</table>
		</div>
	</div>
	<script>
	const myApp={
			data(){
				return {
					msg:''
				}
			},
			//생명주기 함수
			beforeCreate:function(){
				console.log("beforeCreate : Vue 객체 생성 전!")
			},
			created:function(){
				console.log("created : Vue객체가 생성됨")
			},
			beforeMount:function(){
				console.log("beforeMount : 가상 메모리에 DOM 만들기 전 상태")
				//componentWillMount()
			},
			//mounted가 가장 많이 사용됨!(스프링에서 데이터 읽어오는 등)
			mounted:function(){
				console.log("mounted : 메모리에 HTML이 저장된 상태 = window.onload")
				//conponentDidMount()
			},
			beforeUpdate:function(){
				//상태가 변경된 상태 == data값 등
				console.log("beforeUpdate : 상태(state)가 변경된 시점 직전")
			},
			updated:function(){
				console.log("updated : 변경된 상태")
			},
			beforeDestroy:function(){
				console.log("beforeDestory : 메모리 해제 전")
			},
			destoryed:function(){
				console.log("destroyed : 메모리 해제된 상태") 
			},
			methods:{
				myclick:function(){
					if(this.msg===''){
						alert("빈칸을 채워주세요");
						this.$refs.msg.foucs();
					}
				}
			}
	}
	Vue.createApp(myApp).mount('.container')
	</script>
</body>
</html>