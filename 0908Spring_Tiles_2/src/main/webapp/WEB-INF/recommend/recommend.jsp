<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	$('.btns').click(function(){
		let type = $(this).attr("data-type");
		console.log(type);
		$.ajax({
			//RecommendRestController에서 get으로 받아오기 때문에 get으로 보내줘야 한다.
			type:'get',
			url:'../recommend/recommend_sub.do',
			data:{"type":type},
			success:function(result){
				//alert(result);
				let res = JSON.parse(result);
				let data = "";
				for(let i=0;i<res.length;i++){
					data+='<span class="btn btn-sm btn-info" onclick="recommendData(\''+res[i]+'\')">'+res[i]+'</span>&nbsp;&nbsp;';
				}
				$('#sub').html(data);
			}
		})
	})
})
function recommendData(res){
	$.ajax({
		type:'post', //post로 보냈으니 Controller에서도 Post로 받아야 한다
		url:'../recommend/recommend_data.do',
		data:{"fd":res},
		success:function(result){
			let res = JSON.parse(result);
			let data = "";
			//for문으로 출력
			data = res.map((m)=>(
					'<div class="col-md-3">'
					+'<div class="thumbnail">'
					+'<a href="#">'
					+'<img src="'+m.poster+'" style="width:350px; height:200px">'
					+'<div class="caption">'
					+'<p>'+m.name+'</p>'
					+'</div>'
					+'</a>'
					+'</div>'
					+'</div>'
			));
			$('#recom').html(data);
		}
	})
}
</script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="text-center">
				<input type="button" value="상황" class="btn btn-lg btn-danger btns" data-type="1">
				<input type="button" value="감성" class="btn btn-lg btn-warning btns" data-type="2">
				<input type="button" value="스타일" class="btn btn-lg btn-primary btns" data-type="3">
				<input type="button" value="날씨/계절" class="btn btn-lg btn-success btns" data-type="4">
			</div>
		</div>
		<div style="height:30px"></div>
		<div class="row"><%--세부 옵션 출력 --%>
			<div class="text-center" id="sub"> </div>
		</div>
		<div style="height:30px"></div>
		<div class="row"><%--맛집 출력 --%>
			<div class="text-center" id="recom"> </div>
		</div>
	</div>
</body>
</html>