package doo.doo.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import doo.doo.mapper.GoodsMapper;

@Repository
public class GoodsDAO {
	@Autowired
	private GoodsMapper mapper;
	
	public List<GoodsVO> goodsListData(Map map){
		return mapper.goodsListData(map);
	}
	public int goodsTotalPage() {
		return mapper.goodsTotalPage();
	}
	public GoodsVO goodsDetailData(int no) {
		return mapper.goodsDetailData(no);
	}
}
