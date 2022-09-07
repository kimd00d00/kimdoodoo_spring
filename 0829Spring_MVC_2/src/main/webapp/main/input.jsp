<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a href="output.do?no=1&name=홍길동&avg=10.5&isadmin=true">전송</a> 
	<!-- GET방식. ?가 붙으면 GET방식. 
		<a>, location.href, sendRedirect() -> @GetMapping으로 받아야함
		<form>, ajax, axios -> 선택해서 post, get이냐에 따라 @PostMapping으로 받아야 함
		bad request 
	 -->
	 <p>
	 <form method="post" action="output1.do">
	 	ID:<input type="text" name=id size=20 ><br>
	 	PW:<input type="password" name=pwd size=20><br>
	 	<button>전송</button>
	 </form>
</body>
</html>