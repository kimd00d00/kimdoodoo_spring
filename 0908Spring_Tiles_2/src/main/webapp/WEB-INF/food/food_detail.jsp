<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<table class="table">
				<tr>
					<c:forTokens items="${vo.poster }" delims="^" var="image">
						<td>
							<img src="${image }" style="width:100%">
						</td>
					</c:forTokens>
				</tr>
			</table>
		</div>
	</div>
	<div style="height:20px"></div>
	<div class="row">
		<div class="col-sm-8">
			<table class="table">
				<tr>
					<td colspan="2"><h3>${vo.name }&nbsp;<span style="color:orange">${vo.score }</span></h3></td>
				</tr>
				<tr>
					<td width="30%" style="color:gray">주소</td>
					<td width="70%">${fn:substring(vo.address,0,fn:indexOf(vo.address,'지')) }</td>
				</tr>
				<tr>
					<td width="30%" style="color:gray">전화번호</td>
					<td width="70%">${vo.tel }</td>
				</tr>
				<tr>
					<td width="30%" style="color:gray">음식종류</td>
					<td width="70%">${vo.menu }</td>
				</tr>
				<tr>
					<td width="30%" style="color:gray">가격대</td>
					<td width="70%">${vo.price }</td>
				</tr>
				<tr>
					<td width="30%" style="color:gray">영업시간</td>
					<td width="70%">${vo.time }</td>
				</tr>
				<tr>
					<td width="30%" style="color:gray">주차</td>
					<td width="70%">${vo.parking }</td>
				</tr>
				<c:if test="${vo.menu!='no' }">
					<tr>
						<td width="30%" style="color:gray">메뉴</td>
						<td width="70%">
							<ul>
								<c:forTokens items="${vo.menu }" delims="원" var="m">
									<li>${m }원</li>
								</c:forTokens>
							</ul>
						</td>
					</tr>
				</c:if>
				<tr>
					<td colspan="2" class="text-right">
						<input type="button" value="목록" class="btn btn-xs btn-warning" onclick="javascript:history.back()">
					</td>
				</tr>
			</table>
		</div>
		<div class="col-sm-4">
			<div id="map" style="width:100%;height:350px;"></div>
			<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=b18319530b6d6d62d5c86a8807893413&libraries=services"></script>
			<script>
			var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
			    mapOption = {
			        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
			        level: 3 // 지도의 확대 레벨
			    };  
			
			var map = new kakao.maps.Map(mapContainer, mapOption); 
			var geocoder = new kakao.maps.services.Geocoder();
			geocoder.addressSearch('${fn:substring(vo.address,0,fn:indexOf(vo.address,'지')) }', function(result, status) {
			
			     if (status === kakao.maps.services.Status.OK) {
			
			        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
			
			        var marker = new kakao.maps.Marker({
			            map: map,
			            position: coords
			        });
			
			        var infowindow = new kakao.maps.InfoWindow({
			            content: '<div style="width:150px;text-align:center;padding:6px 0;">${vo.name}</div>'
			        });
			        infowindow.open(map, marker);
			        map.setCenter(coords);
			    } 
			});    
			</script>
			</div>
	</div>
	<div style="height:20px"></div>
	<div class="row">
		<h2>관련 레시피</h2>
		<hr>
		<c:forEach var="rvo" items="${ rList}">
			<div class="col-md-2">
					<div class="thumbnail">
						<a href="../recipe/detail.do?no=${rvo.no }">
							<img src="${rvo.poster }" style="width:100%">
						</a>
					</div>
				</div>
		</c:forEach>
	</div>
</body>
</html>