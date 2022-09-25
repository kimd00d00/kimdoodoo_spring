package doo.doo.dao;
import java.util.*;
import doo.doo.vo.*;
import doo.doo.mapper.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RecipeDAO {
	@Autowired
	private RecipeMapper mapper;
	
	public List<RecipeVO> recipeListData(Map map){
		return mapper.recipeListData(map);
	}
	public List<RecipeVO> recipeFindData(Map map){
		return mapper.recipeFindData(map);
	}
	public List<ChefVO> chefListData(Map map){
		return mapper.chefListData(map);
	}
	public List<ChefVO> chefFindData(Map map){
		return mapper.chefFindData(map);
	}
	public int recipeTotalPage() {
		return mapper.recipeTotalPage();
	}
	public int recipeFindTotalPage(Map map) {
		return mapper.recipeFindTotalPage(map);
	}
	public int chefTotalPage() {
		return mapper.chefTotalPage();
	}
	public List<RecipeVO> chefMakeRecipeData(Map map){
		return mapper.chefMakeRecipeData(map);
	}
	
	public int chefMakeRecipeTotalPage(Map map) {
		return mapper.chefMakeRecipeTotalPage(map);
	}
	
	public List<RecipeVO> chefMakeRecipeFindData(Map map){
		return mapper.chefMakeRecipeFindData(map);
	}
	public int chefMakeRecipeFindTotalPage(Map map){
		return mapper.chefMakeRecipeFindTotalPage(map);
	}
}

