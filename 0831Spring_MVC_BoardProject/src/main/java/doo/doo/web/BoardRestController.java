package doo.doo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import doo.doo.dao.*;

//JavaScript, �Ϲ� ���ڿ�, JSON ������ ���ۿ� ����. JSP ������ ������ ����.
@RestController
public class BoardRestController {
	@Autowired
	private BoardDAO dao;
	
	//HTML : text/html , JSON : text/plain
	@PostMapping(value="board/update_ok.do", produces="text/html;charset=UTF-8")//�ѱ۱�������
	public String board_update_ok(BoardVO vo) {
		String result="";
		boolean bCheck = dao.boardUpdate(vo);
		if(bCheck==true) {
			result="<script> "
					+ "location.href=\"detail.do?no=" + vo.getNo()+"\";"
					+ "</script>";
		}else {
			result="<script> "
					+ "alert(\"��й�ȣ�� Ʋ����!!!\");"
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
					+ "alert(\"��й�ȣ�� Ʋ����!!!\");"
					+ "history.back();"
					+ "</script>";
		}
		return result;
	}
}
