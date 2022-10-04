package doo.doo.web.dao;

import doo.doo.web.vo.*;
import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FoodDAO extends JpaRepository<FoodEntity, Integer>{ //FoodEntity에 결과값을 담아 주세요. PK는 Integer타입입니다.
	@Query(value="SELECT * FROM food_house "
			+ "WHERE cno=:cno ", nativeQuery=true)
	public List<FoodEntity> categoryFoodListData(@Param("cno") Integer cno);
	
	public FoodEntity findByFno(int fno);
}
