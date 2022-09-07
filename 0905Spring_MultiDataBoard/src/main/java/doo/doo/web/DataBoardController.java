package doo.doo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import doo.doo.dao.*;

import java.io.*;
import java.net.URLEncoder;
import java.util.*;
import org.snu.ids.ha.index.*;

import javax.servlet.http.HttpServletResponse;
@Controller
@RequestMapping("databoard/")//공통으로 적용되는 URI 주소 설정
public class DataBoardController {
	@Autowired
	private DataBoardDAO dao;
	
	@GetMapping("list.do") //자동으로 databoard/list.do 로 인식됨.
	public String databoard_list(String page, Model model) {
		if(page==null)
			page="1";
		int curPage = Integer.parseInt(page);
		
		Map map = new HashMap();
		int rowSize = 10;
		int start = rowSize*curPage-(rowSize-1);
		int end = rowSize*curPage;
		
		map.put("start",start);
		map.put("end",end);
		List<DataBoardVO> list = dao.boardListData(map);
		
		int totalPage = dao.boardTotalPage();
		
		model.addAttribute("list", list);
		model.addAttribute("curPage", curPage);
		model.addAttribute("totalPage", totalPage);
		
		return "databoard/list";
	}
	
	@GetMapping("insert.do")
	public String databoard_insert() {
		return "databoard/insert";
	}
	
	@PostMapping("insert_ok.do")
	public String databoard_insert_ok(DataBoardVO vo) {
		List<MultipartFile> list = vo.getFiles();
		System.out.println("size:"+list.size());
		String path="c:\\download\\";
		try {
			if(list==null) {//첨부된 파일이 없을 경우
				vo.setFilename("");
				vo.setFilesize("");
				vo.setFilecount(0);
			}else {
				String temp1="";
				String temp2="";
				for(MultipartFile mf:list) {
					String filename = mf.getOriginalFilename();//사용자가 선택한 파일명
					//파일을 저장한다!
					mf.transferTo(new File(path+filename));
					temp1 += filename+",";
					File f = new File(path+filename);
					long len = f.length(); //파일 사이즈를 가져온다
					temp2 += len+",";
				}
				temp1 = temp1.substring(0,temp1.lastIndexOf(","));
				temp2 = temp2.substring(0,temp2.lastIndexOf(","));
				vo.setFilename(temp1);
				vo.setFilesize(temp2);
				vo.setFilecount(list.size());
			}
			dao.boardInsert(vo);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return "redirect:list.do";
	}
	
	@GetMapping("detail.do")
	public String databoard_detail(int no, Model model) {
		DataBoardVO vo = dao.boardDetailData(no);
		model.addAttribute("vo", vo);
		if(vo.getFilecount()!=0) { //업로드된 파일이 있을 때만
			List<String> fList = new ArrayList<String>();
			List<String> sList = new ArrayList<String>();
			StringTokenizer st = new StringTokenizer(vo.getFilename(),",");
			while(st.hasMoreTokens()) {
				fList.add(st.nextToken());
			}
			st = new StringTokenizer(vo.getFilesize(),",");
			while(st.hasMoreTokens()) {
				sList.add(st.nextToken());
			}
			
			model.addAttribute("fList",fList);
			model.addAttribute("sList",sList); // 파일이름(파일크기) < 이렇게 출력해주려고...
		}
		
		String data = vo.getContent();
		data = data.replaceAll("[0-9]", "");
		KeywordExtractor ke = new KeywordExtractor(); //명사 자를때 씀
		KeywordList kl = ke.extractKeyword(data, true);
		List<DataVO> list = new ArrayList<DataVO>();
		
		for(int i=0;i<kl.size();i++) {
			Keyword kwrd = kl.get(i);
			if(kwrd.getCnt()>2) {
				DataVO dvo = new DataVO();
				dvo.setWord(kwrd.getString());
				dvo.setCount(kwrd.getCnt());
				list.add(dvo);
			}
		}
		model.addAttribute("list",list);
		
		return "databoard/detail";
	}
	
	@GetMapping("download.do")
	public void databoard_download(String fn, HttpServletResponse response) {
		try {
			File file = new File("c:\\download\\"+fn);
			//다운로드창을 먼저 띄운다!
			response.setContentLength((int)file.length());
			response.setHeader("Content-Disposition", 
					"attatchment;filename="+URLEncoder.encode(fn,"UTF-8"));
			//이제 파일을 보내줍니다
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file)); //서버
			BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream()); //클라이언트
			byte[] buffer = new byte[1024];
			int i=0; //읽은 byte수
			while((i=bis.read(buffer,0,1024))!=-1) { //-1 == End of File(EOF)
				bos.write(buffer,0,i);
			}
			bis.close();
			bos.close();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	//수정하기
	@GetMapping("update.do")
	public String databoard_update(int no, Model model) {
		DataBoardVO vo = dao.boardUpdateData(no);
		model.addAttribute("vo",vo);
		return "databoard/update";
	}
	
	//삭제하기
	@GetMapping("delete.do")
	public String databoard_delete(int no, Model model){
		model.addAttribute("no",no);
		return "databoard/delete";
	}
}
