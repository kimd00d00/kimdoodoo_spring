package doo.doo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import doo.doo.mapper.RecipeMapper;
import doo.doo.vo.*;
import java.util.*;

@Repository
public class RecipeDAO {
	@Autowired
	private RecipeMapper mapper;
	public List<RecipeVO> recipeListData(Map map){
		return mapper.recipeListData(map);
	}
	public int recipeTotalPage() {
		return mapper.recipeTotalPage();
	}
	public List<RecipeVO> recipeFindData(String ss){
		return mapper.recipeFindData(ss);
	}
}
