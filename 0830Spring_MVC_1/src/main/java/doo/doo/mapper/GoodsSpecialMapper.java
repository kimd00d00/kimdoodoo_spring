package doo.doo.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import doo.doo.dao.GoodsVO;

public interface GoodsSpecialMapper {
	@Select("SELECT no, goods_name name, goods_poster poster, goods_price price, num "
			+ "FROM (SELECT no, goods_name, goods_poster, goods_price, rownum as num "
			+ "FROM (SELECT /*+ INDEX_ASC(goods_all ga_no_pk)*/no, goods_name, goods_poster, goods_price "
			+ "FROM goods_special)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<GoodsVO> goodsSpecialListData(Map map);
	
	@Results({
		@Result(property="name",column="goods_name"),
		@Result(property="sub",column="goods_sub"),
		@Result(property="price",column="goods_price"),
		@Result(property="discount",column="goods_discount"),
		@Result(property="first_price",column="goods_first_price"),
		@Result(property="delivery",column="goods_delivery"),
		@Result(property="poster",column="goods_poster"),
	})
	@Select("SELECT * FROM goods_special "
			+ "WHERE no=#{no}")
	public GoodsVO goodsSpecialDetailData(int no);
}
