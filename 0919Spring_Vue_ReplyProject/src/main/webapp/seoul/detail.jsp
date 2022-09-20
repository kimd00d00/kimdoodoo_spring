<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
		<div class="row" id="seoul_detail">
			<table class="table">
				<tr>
					<td class="text-center">
						<img :src="seoul_detail.poster" style="width:700px; height:250px;">
					</td>
				</tr>
				<tr>
					<td><h3>{{seoul_detail.title}}</h3></td>
				</tr>
				<tr>
					<td><h3>{{seoul_detail.msg}}</h3></td>
				</tr>
				<tr>
					<td><h3>{{seoul_detail.address}}</h3></td>
				</tr>
				<tr>
					<td class="text-right">
						<a href="../seoul/list.do" class="btn btn-xs btn-primary" @click="javascript:history.back()">이전</a>
					</td>
				</tr>
			</table>
		</div>
		<div stlyee="height:20px"></div>
		<div class="row" id="seoul_reply">
			<table class="table">
				<tr>
					<td>
						<%--JSONArray 안에 JSONObject가 이렇게 [{},{},{},..] --%>
						<table class="table" v-for="re in reply_list">
							<tr>
								<td class="text-left">◮{{re.name}}◮ ({{re.dbday}})</td>
								<td class="text-right">
									<input class="btn btn-sm btn-warning" v-if="re.id===sessionId" value="수정" @click="replyUpdate_tmp()">
									<input class="btn btn-sm btn-danger" v-if="re.id===sessionId" value="삭제" @click="replyDelete(re.no)">
								</td>
							</tr>
							<tr>
								<td colspan="2" valign="top" class="text-left">
									<pre style="white-space: pre-wrap; border:none; background-color:white">{{re.msg}}</pre>
								</td>
							</tr>
							<tr v-show="isShow">
								<td colspan="2">
									<form method="post" action="../seoul/reply_update.do">
									<table class="table">
										<tr>
											<td>
												<input type="hidden" name="cno" :value="cno">
												<input type="hidden" name="type" :value="type">
												<input type="hidden" name="no" :value="re.no">
												<textarea rows="5" cols="75" ref="msg" style="float:left" name="msg">{{re.msg}}</textarea>
												<input type="submit" value="댓글수정" class="btn btn-sm btn-primary" style="height:105px">
											</td>
										</tr>
									</table>
									</form>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			<c:if test="${sessionScope.id!=null }">
				<table class="table">
					<tr>
						<td>
							<textarea rows="5" cols="85" ref="msg" style="float:left" v-model="msg"></textarea>
							<input type="button" value="댓글쓰기" class="btn btn-sm btn-primary" style="height:105px" @click="replyWrite()">
						</td>
					</tr>
				</table>
			</c:if>
		</div>
	</div>
<script>
new Vue({
	el:'#seoul_detail',
	data:{
		no:${no},
		type:${type},
		seoul_detail:{}
	},
	mounted:function(){
		let _this=this;
		axios.get("http://localhost:8080/web/seoul/detail_vue.do",{
			params:{
				no:_this.no,
    			type:_this.type
			}
		}).then(function(result){
			_this.seoul_detail=result.data
		})
	}
})
new Vue({
	el:'#seoul_reply',
	data:{
		cno:${no},
		type:${type},
		reply_list:[],
		sessionId:'',
		msg:'',
		isShow:false
	},
	mounted:function(){
		let _this = this;
		axios.get("http://localhost:8080/web/seoul/reply_list.do",{
			params:{
				cno:_this.cno,
				type:_this.type
			}
		}).then(function(result){
			console.log(result.data);
			_this.reply_list = result.data;
			_this.sessionId = result.data[0].sessionId;
		})
	},
	methods:{
		replyWrite:function(){
			if(this.msg==''){
				this.$refs.msg.focus();
				return;
			}
			let _this = this;
			axios.get("http://localhost:8080/web/seoul/reply_insert.do",{
				params:{
					cno:_this.cno,
					type:_this.type,
					msg:_this.msg
				}
			}).then(function(result){
				_this.msg="";
				console.log(result.data);
				_this.reply_list = result.data;
			})
		},
		replyUpdate_tmp:function(){
			this.isShow=true;
		},
		/*replyUpdate:function(no){
			if(this.msg==''){
				this.$refs.msg.focus();
				return;
			}
			let _this = this;
			axios.get("http://localhost:8080/web/seoul/reply_update.do",{
				params:{
					cno:_this.cno,
					type:_this.type,
					msg:_this.msg
				}
			}).then(function(result){
				_this.msg="";
				_this.reply_list = result.data;
			})
		}, */
		replyDelete:function(no){
			let _this = this;
			axios.get("http://localhost:8080/web/seoul/reply_delete.do",{
				params:{
					no:no,
					cno:_this.cno,
					type:_this.type
				}
			}).then(function(result){
				console.log(result.data);
				_this.reply_list = result.data;
				_this.sessionId = result.data[0].sessionId;
			})
		}
	}
})
</script>
</body>
</html>