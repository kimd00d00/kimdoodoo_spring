package doo.doo.web;

import doo.doo.dao.*;

import java.io.File;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataBoardRestController {
	@Autowired
	private DataBoardDAO dao;
	
	@PostMapping(value="databoard/update_ok.do", produces="text/html;charset=UTF-8") //�ѱ۱�������
	public String databoard_update_ok(DataBoardVO vo) {
		String result = "";
		boolean bCheck = dao.boardUpdate(vo);
		if(bCheck==true) {
			result = "<script>"
					+ "location.href=\"detail.do?no="+vo.getNo()+"\";"
					+ "</script>";
		}else {
			result = "<script>"
					+"alert(\"��й�ȣ�� Ʋ���׿�;;��;;��;;\");"
					+ "history.back();"
					+ "</script>";
		}
		return result;
	}
	
	@PostMapping(value="databoard/delete_ok.do", produces="text/html;charset=UTF-8") //�ѱ۱�������
	public String databoard_delete_ok(int no, String pwd) {
		String result = "";
		DataBoardVO vo = dao.boardInfoData(no);
		boolean bCheck = dao.boardDelete(no,pwd);
		if(bCheck==true) {
			result = "<script>"
					+ "location.href=\"list.do\";"
					+ "</script>";
			try {
				if(vo.getFilecount()>0) {//������ ������
					StringTokenizer st = new StringTokenizer(vo.getFilename(),",");
					while(st.hasMoreTokens()) {
						File file = new File("c:\\download\\"+st.nextToken());
						file.delete();
					}
				}
			}catch(Exception ex) {
				ex.printStackTrace();
			}
		}else {
			result = "<script>"
					+"alert(\"��й�ȣ�� Ʋ���׿�;;��;;��;;\");"
					+ "history.back();"
					+ "</script>";
		}
		return result;
	}
}
