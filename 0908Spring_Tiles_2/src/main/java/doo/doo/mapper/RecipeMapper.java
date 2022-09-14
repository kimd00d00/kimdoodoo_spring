package doo.doo.mapper;

import org.apache.ibatis.annotations.Select;
import java.util.*;
import doo.doo.vo.*;
public interface RecipeMapper {
	@Select("SELECT no, title, poster, chef, num "
			+ "FROM (SELECT no, title, poster, chef, rownum as num "
			+ "FROM (SELECT /*+INDEX_ASC(recipe recipe_no_pk) */ no, title, poster, chef "
			+ "FROM recipe)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<RecipeVO> recipeListData(Map map);
	
	@Select("SELECT CEIL(COUNT(*)/12.0) FROM recipe")
	public int recipeTotalPage();
	
	@Select("SELECT no, title, poster, rownum "
			+ "FROM recipe WHERE REGEXP_LIKE(title,#{ss}) AND rownum<=6")
	public List<RecipeVO> recipeFindData(String ss);
}
