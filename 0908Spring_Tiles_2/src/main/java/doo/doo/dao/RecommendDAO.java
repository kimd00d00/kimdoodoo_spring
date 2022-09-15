package doo.doo.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.*;
import doo.doo.vo.*;
import doo.doo.mapper.*;
@Repository
public class RecommendDAO {
	@Autowired
	private RecommendMapper mapper;
	public List<String> recommendNameData(){
		return mapper.recommendNameData();
	}
	public FoodVO recommendDetailData(String name){
		return mapper.recommendDetailData(name).get(0);
		//여러 개 받아오되 맨 첫번째것만 가져올 것
	}
}
