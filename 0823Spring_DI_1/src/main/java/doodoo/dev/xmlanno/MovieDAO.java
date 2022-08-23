package doodoo.dev.xmlanno;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class MovieDAO extends SqlSessionDaoSupport{
	public List<MovieVO> movieListData(Map map){
		//��û ����~~~~~ 
		return getSqlSession().selectList("movieListData",map);
	}

	@Autowired //ssf�� �޾ƿͼ� DAO�� �۵��� �� �ֵ��� �� �ش�
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}
}
