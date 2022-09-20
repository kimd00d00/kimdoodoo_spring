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
.container-fluid{
	margin: 50px;
}
.row1{
	margin: 0px auto;
	width: 100%;
}
.images:hover{
cursor: pointer;
}
</style>
</head>
<body>
	<div class="container-fluid">
		<div class="row row1">
			<div class="col-sm-8">
				<template id="food_template">
					<div class="col-md-4" v-for="vo in food_data">
						<div class="thumbnail">
							<img :src="vo.poster" style="width:100%" class="images" v-on:click="detailData(vo.fno)">
							<div class="caption">
								<p>{{vo.name }}</p>
							</div>
						</div>
					</div>
				</template>
				<div style="height:20px"></div>
				<div class="text-center">
					<button class="btn btn-sm btn-success" v-on:click="prev()">이전</button>
					{{curpage}} page / {{totalpage}} pages
					<button class="btn btn-sm btn-success" v-on:click="next()">다음</button>
				</div>
			</div>
			<div class="col-sm-4" v-show="isShow" v-if="isShow===true">
				<table class="table">
					<tr>
						<td class="text-center" v-for="img in food_detail.poster.split('^')">
							<img :src="img" style="width:100%">
						</td>
					</tr>
				</table>
				<div style="height:10px"></div>
				<table class="table">
					<tr>
						<td colspan="2"><h3>{{food_detail.name}}<span style="color:orange">{{food_detail.score}}</span></h3></td>
					</tr>
					<tr>
						<td style="color:gray" width="20%">주소</td>
						<td width="80%">{{food_detail.address}}</td>
					</tr>
					<tr>
						<td style="color:gray" width="20%">전화</td>
						<td width="80%">{{food_detail.tel}}</td>
					</tr>
					<tr>
						<td style="color:gray" width="20%">음식 종류</td>
						<td width="80%">{{food_detail.type}}</td>
					</tr>
					<tr>
						<td style="color:gray" width="20%">가격대</td>
						<td width="80%">{{food_detail.price}}</td>
					</tr>
					<tr v-if="food_detail.parking!=='no'">
						<td style="color:gray" width="20%">주차</td>
						<td width="80%">{{food_detail.parking}}</td>
					</tr>
					<tr v-if="food_detail.time!=='no'">
						<td style="color:gray" width="20%">시간대</td>
						<td width="80%">{{food_detail.time}}</td>
					</tr>
					<tr v-if="food_detail.menu!=='no'">
						<td style="color:gray" width="20%">메뉴</td>
						<td width="80%">
							<ul>
								<li v-for="m in food_detail.menu.split('원')">{{m}}원</li>
							</ul>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
<script>
new Vue({
	el:'.row',
	data:{
		curpage:1,
		totalpage:0,
		food_data:[],
		food_detail:{},
		fno:0,
		isShow:false
	},
	mounted:function(){
		console.log("VueJS:this="+this);
		let _this = this;
		axios.get("http://localhost:8080/web/food/food_all_vue.do",{
			params:{
				page:_this.curpage
			}
		}).then(function(result){
			console.log("function:this="+this);
			console.log("_this="+_this);
			_this.food_data = result.data;
			_this.curpage=result.data[0].curpage;
			_this.totalpage=result.data[0].totalpage;
		})
	},
	methods:{
		prev:function(){
			this.curpage = this.curpage>1?this.curpage-1:this.curpage;
			let _this = this;
			axios.get("http://localhost:8080/web/food/food_all_vue.do",{
				params:{
					page:_this.curpage
				}
			}).then(function(result){
				_this.food_data = result.data;
				_this.curpage=result.data[0].curpage;
				_this.totalpage=result.data[0].totalpage;
			})
		},
		next:function(){
			this.curpage = this.curpage<this.totalpage?this.curpage+1:this.curpage;
			let _this = this;
			axios.get("http://localhost:8080/web/food/food_all_vue.do",{
				params:{
					page:_this.curpage
				}
			}).then(function(result){
				_this.food_data = result.data;
				_this.curpage=result.data[0].curpage;
				_this.totalpage=result.data[0].totalpage;
			})
		},
		detailData:function(no){
			let _this = this;
			_this.isShow=true;
			axios.get("http://localhost:8080/web/food/detail_vue.do",{
				params:{
					fno:no
				}//데이터를 주고
			}).then(function(result){ //결과값을 받고
				_this.food_detail = result.data;
				console.log(result.data);
			})
		}
	}
})
</script>
</body>
</html>