<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.min.js"></script>
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
						<input type=text size=30 class="input-sm" v-model="msg" ref="msg"><%-- 포커스 주기 위한 ref속성 설정 --%>
						<%-- 클릭할 때 myclick()이라는 함수를 호출하도록 설정 --%>
						<input type=button class="btn btn-sm btn-danger" value="입력" v-on:click="myclick()"> 
						<%-- v-on:click 대신 @click 로 대체가 가능하다  --%>
					</td>
				</tr>
				<tr>
					<td>{{msg}}</td>
				</tr>
			</table>
		</div>
	</div>
</body>
<script>
	new Vue({
		el: '.container',
		data:{
			msg:'' //멤버변수라서 사용시에는 반드시 this. 붙여줘야 함
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
					//data에 설정된 변수는 멤버변수 = 사용할 때는 꼭 this.msg로!
					alert("데이터를 입력하세용!");
					this.$refs.msg.focus(); //참조 변수를 가져올 때는 반드시 ref 속성을 이용
				}
			}
			
		}
	})
</script>
</html>