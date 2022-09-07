package doo.doo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;
import doo.doo.dao.*;

@Controller
public class MovieController {
	@Autowired
	private MovieDAO dao;
	
	@GetMapping("food/movie/list.do")
	public String movie_list(Model model) {
		List<MovieVO> list = dao.movieListData();
		model.addAttribute("list",list);
		return "movie/list";
	}
	
	@GetMapping("food/movie/detail.do")
	public String movie_detail(int mno, Model model) {
		MovieVO vo = dao.movieDetailData(mno);
		model.addAttribute("vo",vo);
		return "movie/detail";
	}
}
