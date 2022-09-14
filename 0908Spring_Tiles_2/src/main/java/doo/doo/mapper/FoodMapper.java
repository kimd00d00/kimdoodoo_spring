package doo.doo.mapper;

import java.util.*;
import doo.doo.vo.*;
import org.apache.ibatis.annotations.Select;

public interface FoodMapper {
	@Select("SELECT cno, title, poster, subject "
			+ "FROM food_category "
			+ "WHERE cno BETWEEN #{start} AND #{end}")
	public List<CategoryVO> categoryListData(Map map);
	
	@Select("SELECT fno, name, poster, address, tel, type, score "
			+ "FROM food_house WHERE cno=#{cno}")
	public List<FoodVO> foodListData(int cno);
	
	@Select("SELECT cno, title, subject "
			+ "FROM food_category "
			+ "WHERE cno=#{cno}")
	public CategoryVO categoryInfoData(int cno);
	
	@Select("SELECT fno, name, poster, address, score, tel, type, time, parking, menu, price "
			+ "FROM food_house WHERE fno=#{fno}")
	public FoodVO foodDetailData(int fno);
	
	@Select("SELECT fno, name, poster, num "
			+ "FROM (SELECT fno, name, poster, rownum as num "
			+ "FROM (SELECT fno, name, poster "
			+ "FROM food_location WHERE address LIKE '%'||#{address}||'%')) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<FoodVO> foodFindData(Map map);
	
	@Select("SELECT CEIL(COUNT(*)/12.0) FROM food_location "
			+ "WHERE address LIKE '%'||#{address}||'%'")
	public int foodLocationTotalPage(String address);
}
