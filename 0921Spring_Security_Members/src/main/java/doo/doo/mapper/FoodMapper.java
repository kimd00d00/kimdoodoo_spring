package doo.doo.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Select;

import doo.doo.vo.*;
public interface FoodMapper {
	@Select("SELECT cno, title, subject, poster FROM food_category ORDER BY cno ASC")
	public List<CategoryVO> categoryAllData();
	
	//카테고리별 맛집 출력
	@Select("SELECT fno, cno, name, tel, poster, type, address FROM food_house "
			+ "WHERE cno=#{cno}")
	public List<FoodVO> foodCategoryListData(int cno);
	
	@Select("SELECT title, subject FROM food_category WHERE cno=#{cno}")
	public CategoryVO categoryInfoData(int cno);
	
	@Select("SELECT poster, rownum FROM recipe "
			+ "WHERE REGEXP_LIKE(title,#{type}) "
			+ "AND rownum<=12")
	public List<String> foodLikeRecipe(String type);
	//상세보기 => Vue지도 출력
	@Select("SELECT * FROM food_house WHERE fno=#{fno}")
	public FoodVO foodDetailData(int fno);
}
