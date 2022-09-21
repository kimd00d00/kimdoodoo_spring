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
}
