<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.row1{
	width:850px;
}
</style>
<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.min.js"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
</head>
<body>
	<div class="container">
		<h1 class="text-center">글 목록</h1>
		<div class="row row1">
			<table class="table">
				<tr>
					<td>
						<a href="../board/insert.do" class="btn btn-sm btn-primary">새글</a>
					</td>
				</tr>
				<tr>
					<th width=10% class="text-center">번호</th>
					<th width=45% class="text-center">제목</th>
					<th width=15% class="text-center">작성자</th>
					<th width=20% class="text-center">작성일</th>
					<th width=10% class="text-center">조회수</th>
				</tr>
				<tr v-for="vo in board_list">
					<td width=10% class="text-center">{{vo.no}}</td>
					<td width=45%><a :href="'../board/detail.do?no='+vo.no">{{vo.subject}}</a></td>
					<td width=15% class="text-center">{{vo.name}}</td>
					<td width=20% class="text-center">{{vo.dbday}}</td>
					<td width=10% class="text-center">{{vo.hit}}</td>
				</tr>
			</table>
			<table class="table">
				<tr>
					<td class="text-center">
						<input type=button value="이전" class="btn btn-sm btn-danger">
						{{curpage}} page / {{totalpage}} pages
						<input type=button value="다음" class="btn btn-sm btn-danger">
					</td>
				</tr>
			</table>
		</div>
	</div>
	<script>
	new Vue({
		el:'.container',
		data:{
			board_list:[],
			curpage:1,
			totalpage:0
		},
		mounted:function(){
			axios.get("http://localhost:8080/web/board/list_vue.do",{
				params:{
					page:this.curpage
				}
			}).then(result=>{
				this.board_list = result.data;
				this.curpage = this.board_list[0].curpage;
				this.totalpage = this.board_list[0].totalpage;
			})
		}
	})
	</script>
</body>
</html>