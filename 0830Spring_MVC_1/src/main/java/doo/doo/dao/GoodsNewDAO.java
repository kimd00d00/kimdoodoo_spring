package doo.doo.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import doo.doo.mapper.GoodsNewMapper;

@Repository
public class GoodsNewDAO {
	@Autowired
	GoodsNewMapper mapper;
	
	public List<GoodsVO> goodsNewListData(Map map){
		return mapper.goodsNewListData(map);
	}
	
	public GoodsVO goodsNewDetailData(int no) {
		return mapper.goodsNewDetailData(no);
	}
	
	public int goodsNewTotalPage() {
		return mapper.goodsNewTotalPage();
	}
}