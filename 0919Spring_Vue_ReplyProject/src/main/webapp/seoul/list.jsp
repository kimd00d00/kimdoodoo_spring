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
	width: 100%;
}
</style>
</head>
<body>
	<div class="container">
		<div id="seoul_list">
			<div class="row">
				<div class="text-center">
					<input type="button" class="btn btn-lg btn-danger" value="서울 명소" @click="seoulChange(1)">
					<input type="button" class="btn btn-lg btn-success" value="서울 자연" @click="seoulChange(2)">
					<input type="button" class="btn btn-lg btn-info" value="서울 쇼핑" v-on:click="seoulChange(3)">
				</div>
			</div>
			<div style="height:20px"></div>
			<div class="row">
				<div class="col-md-4" v-for="vo in seoul_list">
					<div class="thumbnail">
						<a :href="'../seoul/detail_before.do?no='+vo.no+'&type='+type">
							<img :src="vo.poster" style="width:320px;height:250px;" class="images">
							<div class="caption">
								<p>{{vo.title }}</p>
							</div>
						</a>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="text-center">
					<input type=button class="btn btn-lg btn-warning" value="이전" @click="prev()">
						{{curPage}} page / {{totalPage}} pages
					<input type=button class="btn btn-lg btn-warning" value="다음" @click="next()">
				</div>
			</div>
		</div>
		<div style="height:20px"></div>
		<div class="row" id="seoul_cookie">
			<img :src="c.poster" style="width:100px;height:100px;margin-left:5px" v-for="c in cook_list">
		</div>
	</div>
<script>
	const list = new Vue({
		el:'.container',
		data:{
			curPage:1,
			totalPage:0,
			seoul_list:[],
			type:1,
			cook_list:[]
		},
		mounted:function(){
			this.send();
		},
		methods:{
			send:function(){
				let _this = this;
				axios.get("http://localhost:8080/web/seoul/list_vue.do",{
					params:{
						page:_this.curPage,
						type:_this.type
					}
				}).then(function(result){
					_this.seoul_list = result.data;
					_this.curPage = result.data[0].curPage;
					_this.totalPage = result.data[0].totalPage;
					_this.type = result.data[0].type;
				})
				//쿠키 데이터 가져올 것임
				axios.get("http://localhost:8080/web/seoul/cook_list.do",{
					params:{
						type:_this.type
					}
				}).then(function(result){
					console.log(result.data);
					_this.cook_list = result.data;
				})
			},
			seoulChange:function(no){
				this.type = no;
				this.curPage = 1;
				this.send();
			},
			prev:function(){
				this.curPage = this.curPage>1?this.curPage-1:this.curPage;
				this.send();
			},
			next:function(){
				this.curPage = this.curPage<this.totalPage?this.curPage+1:this.curPage;
				this.send();
			}
		}
	})
</script>
</body>
</html>