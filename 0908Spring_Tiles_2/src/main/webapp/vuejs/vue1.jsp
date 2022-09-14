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
	<div id="app">
		<div>{{message}}</div>
	</div>
	<div id="app2">
		<div></div>
	</div>
	<script>
		new Vue({
			el:'#app', //#app div영역을 제어하겠다.
			data:{
				message:'Hello Vue'
			}
		})
	</script>
</body>
</html>