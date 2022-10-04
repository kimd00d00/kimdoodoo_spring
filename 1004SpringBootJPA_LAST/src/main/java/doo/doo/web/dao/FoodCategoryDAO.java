package doo.doo.web.dao;
import doo.doo.web.vo.*;
import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FoodCategoryDAO extends JpaRepository<CategoryEntity, Integer>{ //CategoryEntity를 다룰 거고, 그 객체의 key는 Integer형이다.
	@Query(value="SELECT cno, title, poster, link, subject "
			+ "FROM food_category "
			+ "LIMIT :start, :end ", nativeQuery=true)
	public List<CategoryEntity> categoryListData(@Param("start") Integer start, @Param("end") Integer end);
	
	public CategoryEntity findByCno(int cno);
}
