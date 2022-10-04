package doo.doo.web.dao;
import java.util.*;
import doo.doo.web.vo.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SeoulLocationDAO extends JpaRepository<SeoulLocationEntity, Integer>{
	@Query(value="SELECT no, title, poster, msg, address, hit "
			+ "FROM seoul_location "
			+ "ORDER BY no ASC "
			+ "LIMIT :start,12 ",nativeQuery=true)
	public List<SeoulLocationEntity> seoulListData(@Param("start") Integer start);
	
	@Query(value="SELECT CEIL(COUNT(*)/12.0) FROM seoul_location", nativeQuery = true)
	public int seoulLocationTotalPage();
}
