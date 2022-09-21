package doo.doo.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Select;

import doo.doo.vo.*;
public interface FoodMapper {
	@Select("SELECT cno, title, subject, poster FROM food_category ORDER BY cno ASC")
	public List<CategoryVO> categoryAllData();
}
