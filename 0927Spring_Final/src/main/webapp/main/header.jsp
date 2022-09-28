<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="../main/main.do">WebSiteName</a>
    </div>
    <ul class="nav navbar-nav">
      <li class="active"><a href="../main/main.do">Home</a></li>
      <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">상품 <span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a href="../goods/list.do?type=1">전체 상품</a></li>
          <li><a href="../goods/list.do?type=2">신상품</a></li>
          <li><a href="../goods/list.do?type=3">특가 상품</a></li>
          <li><a href="../goods/list.do?type=4">베스트 상품</a></li>
        </ul>
      </li>
      <li><a href="../news/find.do">뉴스</a></li>
      <li><a href="../board/list.do">커뮤니티</a></li>
    </ul>
  </div>
</nav>
</body>
</html>