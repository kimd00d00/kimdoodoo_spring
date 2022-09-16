<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.row1{
	margin : 0px auto;
	width : 900px;
}
h1{
	text-align:center;
}
</style>
<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.min.js"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
</head>
<body>
	<div class="container">
		<h1>글 수정하기</h1>
		<div class="row row1">
			<table class="table">
				<tr>
					<th width=20% class="text-right">이름</th>
					<td width=80%>
						<input type=text v-model="name" ref="name" size=20 class="input-sm">
						<input type=hidden name=no :value="vo.no">
					</td>
				</tr>
				<tr>
					<th width=20% class="text-right">제목</th>
					<td width=80%>
						<input type=text v-model="subject" ref="subject" size=50 class="input-sm">
					</td>
				</tr>
				<tr>
					<th width=20% class="text-right">내용</th>
					<td width=80%>
						<textarea rows="10" cols="50" v-model="content" ref="content">{{content }}</textarea>
					</td>
				</tr>
				<tr>
					<th width=20% class="text-right">비밀번호</th>
					<td width=80%>
						<input type=password v-model="pwd" ref="pwd" size=15 class="input-sm">
					</td>
				</tr>
				<tr>
					<td colspan="2" class="text-center">
						<input type=button value="수정하기" class="btn btn-sm btn-warning" v-on:click="boardUpdate()">
						<input type=button value="취소" class="btn btn-sm btn-danger"
							onclick="javascript:history.back()">
					</td>
				</tr>
			</table>
		</div>
	</div>
<script>
new Vue({
	el:'.container',
	data:{
		name:'',
		subject:'',
		content:'',
		pwd:'',
		vo:{},
		no:${no},
		res:''
	},
	mounted:function(){
		let _this = this;
		axios.get("http://localhost:8080/web/board/update_vue.do",{
			params:{
				no:_this.no
			}
		}).then(function(result){
			_this.vo = result.data;
			_this.name = _this.vo.name;
			_this.subject = _this.vo.subject;
			_this.content = _this.vo.content;
		})
	},
	methods:{
		boardUpdate:function(){
			console.log("boardUpdate() : 함수호출");
			//유효성 검사
			if(this.name.trim()==""){
				//태그를 가져올 때는 ref를 이용해 가져옴
				this.$refs.name.focus();
				return;
			}
			if(this.subject.trim()==""){
				this.$refs.subject.focus();
				return;
			}
			if(this.content.trim()==""){
				this.$refs.content.focus();
				return;
			}
			if(this.pwd.trim()==""){
				this.$refs.pwd.focus();
				return;
			}
			console.log("boardUpdate() : 유효성 검사 끝");
			let _this = this;
			axios.get("http://localhost:8080/web/board/update_vue_ok.do",{
				params:{
					no: _this.no,
					name: _this.name,
					subject: _this.subject,
					content: _this.content,
					pwd: _this.pwd
				}
			}).then(function(result){
				_this.res = result.data; //YES면 OK, NO면 X
				if(_this.res==='yes'){
					location.href="../board/detail.do?no="+_this.no;
				}else{
					alert("비밀번호 확인하셈!!!");
					_this.pwd = "";
					_this.$refs.pwd.focus();
				}
			})
		}
	}
})
</script>
</body>
</html>