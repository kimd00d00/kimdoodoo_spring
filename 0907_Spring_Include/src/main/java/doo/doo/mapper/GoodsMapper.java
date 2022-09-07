package doo.doo.mapper;

import java.util.*;

import org.apache.ibatis.annotations.Select;

import doo.doo.dao.*;
public interface GoodsMapper {
   @Select("SELECT no,goods_name,goods_price,goods_poster,num "
		  +"FROM (SELECT no,goods_name,goods_price,goods_poster,rownum as num "
		  +"FROM (SELECT no,goods_name,goods_price,goods_poster "
		  +"FROM ${table_name} ORDER BY no ASC)) "
		  +"WHERE num BETWEEN #{start} AND #{end}")
   public List<GoodsVO> goodsListData(Map map);
   
   @Select("SELECT CEIL(COUNT(*)/5.0) FROM ${table_name}")
   public int goodsTotalPage(Map map);
   /*
    *   $ => ���̺�,�÷� => ��ȯ ''(X)
    *   # => �Ϲ� ������ => ��ȯ  ''
    */
   @Select("SELECT * FROM ${table_name} "
		  +"WHERE no=#{no}")
   public GoodsVO goodsDetailData(Map map);
   
   // �α��� ó�� 
   //1. ID üũ
   @Select("SELECT COUNT(*) FROM project_member "
		  +"WHERE id=#{id}")
   public int idCount(String id);
   //2. ��й�ȣ �� 
   @Select("SELECT pwd,name FROM project_member "
		 +"WHERE id=#{id}")
   public MemberVO memberGetPassword(String id);
   
   
   @Select("SELECT * FROM ${table_name} "
		  +"WHERE REGEXP_LIKE(goods_name,#{ss})")
   public List<GoodsVO> goodsFindData(Map map);
}
