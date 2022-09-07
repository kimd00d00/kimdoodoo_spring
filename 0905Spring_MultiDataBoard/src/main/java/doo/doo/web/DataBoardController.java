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
@RequestMapping("databoard/")//�������� ����Ǵ� URI �ּ� ����
public class DataBoardController {
	@Autowired
	private DataBoardDAO dao;
	
	@GetMapping("list.do") //�ڵ����� databoard/list.do �� �νĵ�.
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
			if(list==null) {//÷�ε� ������ ���� ���
				vo.setFilename("");
				vo.setFilesize("");
				vo.setFilecount(0);
			}else {
				String temp1="";
				String temp2="";
				for(MultipartFile mf:list) {
					String filename = mf.getOriginalFilename();//����ڰ� ������ ���ϸ�
					//������ �����Ѵ�!
					mf.transferTo(new File(path+filename));
					temp1 += filename+",";
					File f = new File(path+filename);
					long len = f.length(); //���� ����� �����´�
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
		if(vo.getFilecount()!=0) { //���ε�� ������ ���� ����
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
			model.addAttribute("sList",sList); // �����̸�(����ũ��) < �̷��� ������ַ���...
		}
		
		String data = vo.getContent();
		data = data.replaceAll("[0-9]", "");
		KeywordExtractor ke = new KeywordExtractor(); //��� �ڸ��� ��
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
			//�ٿ�ε�â�� ���� ����!
			response.setContentLength((int)file.length());
			response.setHeader("Content-Disposition", 
					"attatchment;filename="+URLEncoder.encode(fn,"UTF-8"));
			//���� ������ �����ݴϴ�
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file)); //����
			BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream()); //Ŭ���̾�Ʈ
			byte[] buffer = new byte[1024];
			int i=0; //���� byte��
			while((i=bis.read(buffer,0,1024))!=-1) { //-1 == End of File(EOF)
				bos.write(buffer,0,i);
			}
			bis.close();
			bos.close();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	//�����ϱ�
	@GetMapping("update.do")
	public String databoard_update(int no, Model model) {
		DataBoardVO vo = dao.boardUpdateData(no);
		model.addAttribute("vo",vo);
		return "databoard/update";
	}
	
	//�����ϱ�
	@GetMapping("delete.do")
	public String databoard_delete(int no, Model model){
		model.addAttribute("no",no);
		return "databoard/delete";
	}
}
