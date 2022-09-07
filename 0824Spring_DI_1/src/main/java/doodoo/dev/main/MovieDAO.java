package doodoo.dev.main;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

public class MovieDAO extends SqlSessionDaoSupport{
	public List<MovieVO> movieListData(){
		return getSqlSession().selectList("movieListData");
	}
	public List<MovieVO> movieFindData(Map map){
		return getSqlSession().selectList("movieFindData",map);
	}
}
