package doo.doo.dao;
import java.util.*;
import doo.doo.vo.*;
import doo.doo.mapper.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class FoodDAO {
	@Autowired
	private FoodMapper mapper;
	
	public List<CategoryVO> categoryAllData(){
		return mapper.categoryAllData();
	}
	
	public List<FoodVO> foodCategoryListData(int cno) {
		return mapper.foodCategoryListData(cno);
	}
	
	public CategoryVO categoryInfoData(int cno) {
		return mapper.categoryInfoData(cno);
	}
	
	public List<String> foodLikeRecipe(String type){
		return mapper.foodLikeRecipe(type);
	}
	
	public FoodVO foodDetailData(int fno) {
		return mapper.foodDetailData(fno);
	}
}
