package doo.doo.web;

import org.springframework.beans.factory.annotation.Autowired;
//request�� ���� ��� JSP�� �����Ұ���
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.*;
import doo.doo.dao.*;
@Controller
public class BoardController {
	@Autowired
	private BoardDAO dao;
	
	//���
	@GetMapping("board/list.do")
	public String board_list(String page, Model model) {
		//null������ ���� �� �ִ� �κ��� String���� �޾ƾ� �Ѵ�.
		if(page==null)
			page="1";
		int curPage = Integer.parseInt(page);
		Map map = new HashMap();
		map.put("start", ((curPage*10)-9));
		map.put("end",curPage*10);
		List<BoardVO> list = dao.boardListData(map);
		int totalPage = dao.boardTotalPage();
		
		model.addAttribute("curPage",curPage);
		model.addAttribute("totalPage",totalPage);
		model.addAttribute("list",list);
		
		return "board/list";
	}
	
	//�Խñ� �߰� ȭ��(�Է� �� ���)
	@GetMapping("board/insert.do")
	public String board_insert() {
		return "board/insert";
	}
	//�Խñ� �߰� ó��
	@PostMapping("board/insert_ok.do")
	public String board_insert_ok(BoardVO vo) {
		dao.boardInsert(vo); //vo == Ŀ�ǵ� ��ü
		return "redirect:list.do";
	}
	
	//�Խñ� �󼼺���
	@GetMapping("board/detail.do")
	public String board_detail(int no, Model model) {
		BoardVO vo = dao.boardDetailData(no);
		model.addAttribute("vo",vo);
		return "board/detail";
	}
	
	//�Խñ� ���� ȭ��
	@GetMapping("board/update.do")
	public String board_update(int no, Model model) {
		BoardVO vo = dao.boardUpdateData(no);
		model.addAttribute("vo",vo);
		return "board/update";
	}
	
	//�Խñ� �˻�
	@PostMapping("board/find.do")
	public String board_find(String[] fd, String ss, Model model) {
		//üũ�ڽ��� �迭�� ��� ��� �Ѵ�
		Map map = new HashMap();
		map.put("fsArr",fd);
		map.put("ss",ss);
		List<BoardVO> list = dao.boardFindData(map);
		model.addAttribute("list",list);
		return "board/find";
	}
	
	//�Խñ� ����ȭ������ �̵�
	@GetMapping("board/delete.do")
	public String board_delete(int no, Model model) {
		BoardVO vo = dao.boardDeleteData(no);
		model.addAttribute("vo",vo);
		return "board/delete";
	}
}