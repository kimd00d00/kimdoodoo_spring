<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.row1{
	width: 800px;
	height: 750px;
}
#chatArea{
	height: 250px;
	overflow-y:auto;
	border: 1px solid black; 
}
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.3.0/sockjs.min.js"></script>
<script type="text/javascript">
let websocket;
//연결
function connection(){ 
	//webSocket == 웹에서 연결이 되게 만드는 소프트웨어
	websocket=new WebSocket("ws://localhost:8080/web/site/chat/chat-ws");//프로토콜 이름이 ws(WebSocket)임
	websocket.onopen = onOpen; //웹소켓이 연결됐을 때 onOpen()이 자동 호출되도록 함 == CallBack(시스템에 의해서 자동으로 호출하는 함수)
	websocket.onclose = onClose; //마찬가지
	websocket.onmessage = onMessage; //마찬가지
}
//연결 후 처리
function onOpen(event){ 
	alert("채팅서버와 연결되었습니다.");
}
//퇴장 처리
function onClose(event){ 
	alert("채팅방에서 퇴장했습니다.");
}
//채팅메시지 전송
function onMessage(event){ 
	//사용자가 보낸 메시지가 들어오면 event 내에 저장된다.
	let data = event.data; 
	if(data.substring(0,4)=="msg:"){ //roomin, makeroon, roomout 등으로 구분할 것임
		appendMessage(data.substring(4));
	}
}
//연결 해제
function disconnection(){ 
	//퇴장 버튼을 눌렀을 때
	websocket.close();
}
function send(){ //
	let name = $('#name').val();
	if(name.trim()==""){
		$('#name').focus();
		return;
	}
	let msg = $('#sendMsg').val();
	if(msg.trim()==""){
		$('#sendMsg').focus();
		return;
	}
	console.log("send호출");
	websocket.send("msg:["+name+"]"+msg); //onMessage
	$('#sendMsg').val(""); //전송후 입력창 비우기
	$('#sendMsg').focus(); //전송후 입력창 비우기
}
//채팅 문자열을 #recvMsg에 추가
function appendMessage(msg){ 
	$('#recvMsg').append(msg+"<br>");
	let ch=$('#chatArea').height();
    let m=$('#recvMsg').height()-ch;
	$('#chatArea').scrollTop(m);
}
//함수 선언이 끝났으니 이제 이벤트 처리 시작
$(function(){
	$('#startBtn').click(function(){
		connection();
	})
	$('#endBtn').click(function(){
		disconnection();
	})
	$('#sendBtn').click(function(){
		send();
	})
	$('#sendMsg').keydown(function(key){
		if(key.keyCode==13){
			send(); //엔터치면 전송되도록
		}
	})
})
</script>
</head>
<body>
	<div class="container">
		<div class="row row1">
			<h1 class="text-center">WebSocket</h1>
			<table class="table">
				<tr>
					<td>
						이름:<input type=text id="name" size=15 class="input-sm">
						<input type="button" id="startBtn" value="입장" class="btn btn-sm btn-primary">
						<input type="button" id="endBtn" value="퇴장" class="btn btn-sm btn-danger">
					</td>
				</tr>
				<tr>
					<td>
						<div id="chatArea">
							<!-- Receive Message -->
							<div id="recvMsg"></div>
						</div>
					</td>
				</tr>
				<tr>
					<td><!-- 채팅 문자열 입력하는 부분 -->
						<input type="text" id="sendMsg" size=80 class="input-sm">
						<input type="button" id="sendBtn" value="전송" class="btn btn-sm btn-success">
					</td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>