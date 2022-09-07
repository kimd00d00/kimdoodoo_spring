package doo.doo.mapper;
import java.util.*;
import doo.doo.dao.*;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface GoodsMapper {
	@Select("SELECT no, goods_name, goods_price, goods_poster, num "
			+ "FROM (SELECT no, goods_name, goods_price, goods_poster, rownum as num "
			+ "FROM (SELECT /*+ INDEX_ASC(goods_all ga_no_pk)*/ no, goods_name, goods_price, goods_poster "
			+ "FROM goods_all)) "
			+ "WHERE num BETWEEN #{start} AND #{end} ")
	public List<GoodsVO> goodsListData(Map map);
	
//	@Results({
//		@Result(property="name",column="goods_name"),
//		@Result(property="sub",column="goods_sub"),
//		@Result(property="price",column="goods_price"),
//		@Result(property="discount",column="goods_discount"),
//		@Result(property="first_price",column="goods_first_price"),
//		@Result(property="delivery",column="goods_delivery"),
//		@Result(property="poster",column="goods_poster"),
//	})
	
	@Select("SELECT CEIL(COUNT(*)/12.0) FROM goods_all")
	public int goodsTotalPage();
	
	@Select("SELECT * FROM goods_all WHERE no=#{no}")
	public GoodsVO goodsDetailData(int no);
}
