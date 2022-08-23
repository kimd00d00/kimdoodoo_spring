package doodoo.dev.xmlanno;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class MovieDAO extends SqlSessionDaoSupport{
	public List<MovieVO> movieListData(Map map){
		//엄청 간단~~~~~ 
		return getSqlSession().selectList("movieListData",map);
	}

	@Autowired //ssf을 받아와서 DAO가 작동할 수 있도록 해 준다
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}
}
