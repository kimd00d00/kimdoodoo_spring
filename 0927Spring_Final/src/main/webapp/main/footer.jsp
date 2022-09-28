<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>
	<footer class="container-fluid text-center">
	  <div class="row">
	    <div class="col-sm-6">
	      <h3>최신 상품 뉴스</h3>
	      <table class="table">
		      <c:forEach var="newsvo" items="${newsList }" varStatus="s">
		      	<tr>
		      		<td class="text-left"><a href="${newsvo.link}">${s.index+1}.&nbsp;${newsvo.title }</a></td>
		      	</tr>
		      </c:forEach>
	      </table>
	    </div>
	    <div class="col-sm-3">
	      <h3>최신 게시물</h3>
	      <table class="table">
		      <c:forEach var="boardvo" items="${boardFooterList }" varStatus="s">
		      	<tr>
		      		<td class="text-left"><a href="../board/detail.do?no=${boardvo.no}">${s.index+1}위:&nbsp;${boardvo.subject }</a></td>
		      	</tr>
		      </c:forEach>
	      </table>
	    </div>
	    <div class="col-sm-3">
	      <h3>가장 많이 본 상품</h3>
	      <table class="table">
		      <c:forEach var="fvo" items="${footerList }" varStatus="s">
		      	<tr>
		      		<td class="text-left"><a href="../goods/detail.do?no=${fvo.no}&type=1">${s.index+1}위:&nbsp;${fvo.goods_name }</a></td>
		      	</tr>
		      </c:forEach>
	      </table>
	    </div>
	  </div>
	  <div style="height:50px"></div>
	  <div class="row">
	    <p>Made By <a href="#" title="Visit w3schools">쌍용교육센타~</a></p>
	  </div>
	</footer>
</body>
</html>