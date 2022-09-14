package doo.doo.service;

import java.util.List;
import java.util.Map;

import doo.doo.vo.RecipeVO;

public interface RecipeService {
	public List<RecipeVO> recipeListData(Map map);
	public int recipeTotalPage();
	public List<RecipeVO> recipeFindData(String ss);
}