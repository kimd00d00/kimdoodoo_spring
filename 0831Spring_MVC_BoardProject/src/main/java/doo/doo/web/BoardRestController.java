package doo.doo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import doo.doo.dao.*;

//JavaScript, 일반 문자열, JSON 데이터 전송에 사용됨. JSP 지정에 사용되지 않음.
@RestController
public class BoardRestController {
	@Autowired
	private BoardDAO dao;
	
	//HTML : text/html , JSON : text/plain
	@PostMapping(value="board/update_ok.do", produces="text/html;charset=UTF-8")//한글깨짐방지
	public String board_update_ok(BoardVO vo) {
		String result="";
		boolean bCheck = dao.boardUpdate(vo);
		if(bCheck==true) {
			result="<script> "
					+ "location.href=\"detail.do?no=" + vo.getNo()+"\";"
					+ "</script>";
		}else {
			result="<script> "
					+ "alert(\"비밀번호가 틀려요!!!\");"
					+ "history.back();"
					+ "</script>";
		}
		return result;
	}
	
	@PostMapping(value="board/delete_ok.do", produces="text/html;charset=UTF-8")
	public String board_delete_ok(int no, String pwd) {
		String result="";
		boolean bCheck = dao.boardDelete(no,pwd);
		if(bCheck==true) {
			result="<script> "
					+ "location.href=\"list.do"+"\";"
					+ "</script>";
		}else {
			result="<script> "
					+ "alert(\"비밀번호가 틀려요!!!\");"
					+ "history.back();"
					+ "</script>";
		}
		return result;
	}
}
