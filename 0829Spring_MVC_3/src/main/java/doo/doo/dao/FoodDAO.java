package doo.doo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.*;
import doo.doo.mapper.*;

@Repository
public class FoodDAO {
	@Autowired
	private FoodMapper mapper;
	
	public List<FoodVO> foodListData(Map map){
		return mapper.foodListData(map);
	}
	
	public int foodTotalPage() {
		return mapper.foodTotalPage();
	}
	
	public FoodVO foodDetail(int fno) {
		return mapper.foodDetail(fno);
	}
	
	public List<FoodVO> foodFindData(Map map){
		return mapper.foodFindData(map);
	}
}
