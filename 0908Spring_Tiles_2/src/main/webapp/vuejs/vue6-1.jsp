<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://unpkg.com/vue@3"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
</head>
<body>
	<div id="app">
		<button @click="showClick()">보여준다</button>
		<button @click="hideClick()">감춘다</button>
		<div v-show="isShow">
			Hello VUE
		</div>
	</div>
<script>
const myApp={
		data(){
			return{
				isShow:false
			}
		},
		methods:{
			showClick:function(){
				this.isShow=true;
			},
			hideClick:function(){
				this.isShow=false;
			}
		}
}
Vue.createApp(myApp).mount('#app')
</script>
</body>
</html>