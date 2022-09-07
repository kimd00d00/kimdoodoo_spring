package doo.doo.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import doo.doo.mapper.GoodsBestMapper;

@Repository
public class GoodsBestDAO {
	@Autowired
	GoodsBestMapper mapper;
	
	public List<GoodsVO> goodsBestListData(Map map){
		return mapper.goodsBestListData(map);
	}
	
	public GoodsVO goodsBestDetailData(int no) {
		return mapper.goodsBestDetailData(no);
	}
}