<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.min.js"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="text-center">
				<input type="button" value="상황" class="btn btn-lg btn-danger btns" v-on:click="menu(1)">
				<input type="button" value="감성" class="btn btn-lg btn-warning btns" v-on:click="menu(2)">
				<input type="button" value="스타일" class="btn btn-lg btn-primary btns" v-on:click="menu(3)">
				<input type="button" value="날씨/계절" class="btn btn-lg btn-success btns" v-on:click="menu(4)">
			</div>
		</div>
		<div style="height:30px"></div>
		<div class="row"><%--세부 옵션 출력 --%>
			<div class="text-center" id="sub">
				<span class="btn btn-sm btn-info" v-for="m in menu_data" style="margin-left:5px">{{m}}</span>
			</div>
		</div>
		<div style="height:30px"></div>
		<div class="row"><%--맛집 출력 --%>
			<div class="text-center" id="recom"> </div>
		</div>
	</div>
<script>
new Vue({
	el:'.container', //제어할 영역(element) 지정
	data:{
		menu_data:[]
	},
	methods:{
		 menu:function(no){
			 axios.get('http://localhost:8080/web/recommend/recommend_sub.do',{
				 params:{
					 type:no
				 }
			 }).then(result=>{
				 this.menu_data=result.data 
			 })
		 }
	 }
})
</script>
</body>
</html>