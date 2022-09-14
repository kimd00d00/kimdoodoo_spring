package doo.doo.service;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import doo.doo.dao.*;
import doo.doo.vo.*;
//의존성(결합성)이 낮은 프로그램! == 다른 클래스에 영향을 덜 주어야 함 => 캡슐화
@Service
public class RecipeServiceImpl implements RecipeService {
	@Autowired
	private RecipeDAO dao;
	@Override
	public List<RecipeVO> recipeListData(Map map) {
		return dao.recipeListData(map);
	}
	@Override
	public int recipeTotalPage() {
		return dao.recipeTotalPage();
	}
	@Override
	public List<RecipeVO> recipeFindData(String ss) {
		return dao.recipeFindData(ss);
	}

}
