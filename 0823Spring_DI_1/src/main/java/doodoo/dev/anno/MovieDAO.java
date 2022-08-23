package doodoo.dev.anno;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class MovieDAO extends SqlSessionDaoSupport{
	@Autowired //MySqlSessionFactoryBean에서 받아온 값을 여기에 채워넣어달라
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}

	public List<MovieVO> movieListData(Map map){
		//엄청 간단~~~~~ 
		return getSqlSession().selectList("movieListData",map);
	}
	
}
