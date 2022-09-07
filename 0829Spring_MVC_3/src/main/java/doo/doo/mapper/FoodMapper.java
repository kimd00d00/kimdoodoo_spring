package doo.doo.mapper;
import java.util.*;
import org.apache.ibatis.annotations.Select;
import doo.doo.dao.FoodVO;
public interface FoodMapper {
	@Select("SELECT fno, name, poster, num "
			+ "FROM (SELECT fno, name, poster, rownum as num "
			+ "FROM (SELECT fno, name, poster "
			+ "FROM food_location ORDER BY fno ASC )) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<FoodVO> foodListData(Map map);
	
	@Select("SELECT CEIL(COUNT(*)/12.0) FROM food_location")
	public int foodTotalPage();
	
	//상세보기
	@Select("SELECT fno, name, poster, score, address, tel, type, menu, parking, price "
			+ "FROM food_location WHERE fno=#{fno}")
	public FoodVO foodDetail(int fno); //매개변수는 반드시 한 개만 사용이 가능하다. 여러 개 하고싶으면 VO나 map을 써야함.
	
	//검색
	@Select("SELECT fno, name, poster, num "
			+ "FROM (SELECT fno, name, poster, rownum as num "
			+ "FROM (SELECT fno, name, poster "
			+ "FROM food_location WHERE address LIKE '%'||#{address}||'%' ORDER BY fno ASC )) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<FoodVO> foodFindData(Map map);
}
