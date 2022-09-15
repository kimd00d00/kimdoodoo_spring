package doo.doo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;
import doo.doo.recommend.*;
@Controller
public class RecommendController {
	@Autowired
	private RecommendManager mgr;
	
	@GetMapping("recommend/recommend.do")
	public String food_recommend() {
		return "recommend/recommend";
	}
	@GetMapping("recommend/recommend_vue.do")
	public String recommend_vue() {
		return "recommend/recommend_vue";
	}
}
