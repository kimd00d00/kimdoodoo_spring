package doo.doo.mapper;

import org.apache.ibatis.annotations.Select;
import doo.doo.vo.*;
import java.util.*;

public interface RecommendMapper {
	@Select("SELECT DISTINCT name FROM food_location "
			+ "WHERE LENGTH(name)>1")
	public List<String> recommendNameData();
	
	@Select("SELECT fno, name, poster FROM food_location "
			+ "WHERE name = #{name}")
	public List<FoodVO> recommendDetailData(String name);//같은 이름의 다른 업체가 있을 수도 있음
}
