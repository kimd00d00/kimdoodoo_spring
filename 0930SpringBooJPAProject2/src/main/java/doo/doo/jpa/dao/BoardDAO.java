package doo.doo.jpa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import doo.doo.jpa.entity.*;
import java.util.*;

@Repository
public interface BoardDAO extends JpaRepository<BoardEntity, Integer>{
	public BoardEntity findByNo(@Param("no") Integer no);
	
	@Query(value="SELECT no, name, subject, content, hit, regdate, pwd "
			+ "FROM board ORDER BY no DESC LIMIT :start,10", nativeQuery=true)
	public List<BoardEntity> boardListData(@Param("start") Integer start);
	
	@Query(value="SELECT CEIL(COUNT(*)/10.0) FROM board")
	public int boardTotalPage();
}
