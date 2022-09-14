package doo.doo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.*;
import doo.doo.mapper.*;
import doo.doo.vo.*;

@Repository
public class FoodDAO {
	@Autowired
	private FoodMapper mapper;
	
	public List<CategoryVO> categoryListData(Map map){
		return mapper.categoryListData(map);
	}
	public List<FoodVO> foodListData(int fno){
		return mapper.foodListData(fno);
	}
	public CategoryVO categoryInfoData(int cno) {
		return mapper.categoryInfoData(cno);
	}
	public FoodVO foodDetailData(int fno) {
		return mapper.foodDetailData(fno);
	}
	
	public List<FoodVO> foodFindData(Map map){
		return mapper.foodFindData(map);
	}
	
	public int foodLocationTotalPage(String address) {
		return mapper.foodLocationTotalPage(address);
	}
}
