package doodoo.dev.anno;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class MovieDAO extends SqlSessionDaoSupport{
	@Autowired //MySqlSessionFactoryBean���� �޾ƿ� ���� ���⿡ ä���־�޶�
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}

	public List<MovieVO> movieListData(Map map){
		//��û ����~~~~~ 
		return getSqlSession().selectList("movieListData",map);
	}
	
}
