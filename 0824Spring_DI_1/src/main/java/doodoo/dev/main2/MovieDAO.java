package doodoo.dev.main2;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("dao")
public class MovieDAO extends SqlSessionDaoSupport{
	
	@Autowired
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}
	public List<MovieVO> movieListData(){
		return getSqlSession().selectList("movieListData");
	}
	public List<MovieVO> movieFindData(Map map){
		return getSqlSession().selectList("movieFindData",map);
	}
}
