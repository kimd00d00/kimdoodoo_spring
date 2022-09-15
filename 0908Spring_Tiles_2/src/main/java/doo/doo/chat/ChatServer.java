package doo.doo.chat;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.util.*;

//URL 주소를 보냈을 때 얘가 받아서 처리하겠다
@ServerEndpoint("/site/chat/chat-ws") //연결 주소
public class ChatServer {
	//접속자가 들어올 때마다 해당 유저를 저장해 놔야 한다. -> 그래야 채팅방 안에 있는 사람들에게 메시지를 보여줄 수 있으니까
	private Set<Session> users //같은 접속자를 여러 번 저장하면 안되니까==중복 비허용이니까=>Set
		= Collections.synchronizedSet(new HashSet<Session>());//저장시에는 동기 방식으로! 한 사람 저장하고 그 다음 사람 저장하고...
	
	@OnOpen //채팅방에 입장했을 때
	public void onOpen(Session session) {
		//시작할 때마다 세션에 고유 번호를 배정받는다. (IP가 포함되어 있음)
		users.add(session);
		System.out.println("접속했다:"+session.getId());
	}
	
	@OnClose //채팅방에서 퇴장했을 때
	public void onClose(Session session) {
		users.remove(session);
		System.out.println("퇴장했다:"+session.getId());
	}
	
	@OnMessage //메시지를 보냈을 때
	public void onMessage(String message, Session session) throws Exception{
		//스레드의 동기화 프로그램을 사용하는 것임. 왜?
		synchronized (users) {
			for(Session s:users) {
				s.getBasicRemote().sendText(message);//원격 접속이라 예외 처리가 필요함
			}
		}
	}
}