package doo.doo.mapper;

import org.apache.ibatis.annotations.Select;
import java.util.*;
import doo.doo.dao.*;

public interface GoodsMapper {
	@Select("SELECT no, goods_name, goods_price, goods_poster, num "
			+ "FROM (SELECT no, goods_name, goods_price, goods_poster, rownum as num "
			+ "FROM (SELECT no, goods_name, goods_price, goods_poster "
			+ "FROM ${table_name} ORDER BY no ASC)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<GoodsVO> goodsListData(Map map);
	
	@Select("SELECT CEIL(COUNT(*)/5.0) FROM ${table_name}")
	public int goodsTotalPage(Map map);
	
	@Select("SELECT * FROM ${table_name} WHERE no=#{no}")
	public GoodsVO goodsDetailData(Map map);
	
	//로그인 처리
	//ID가 있는지 체크
	@Select("SELECT COUNT(*) FROM project_member "
			+ "WHERE id=#{id}")
	public int idCount(String id);
	//PWD 체크
	@Select("SELECT pwd, name FROM project_member WHERE id=#{id}")
	public MemberVO memberGetPassword(String id);
	
	@Select("SELECT * FROM ${table_name} "
			+ "WHERE REGEXP_LIKE(goods_name,#{ss})")
	public List<GoodsVO> goodsFindData(Map map);
}
