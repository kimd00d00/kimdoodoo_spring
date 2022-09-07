package doo.doo.dao;

import java.util.*;
import doo.doo.dao.GoodsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import doo.doo.mapper.GoodsAllMapper;

@Repository
public class GoodsAllDAO {
	@Autowired
	GoodsAllMapper mapper;
	
	public List<GoodsVO> goodsAllListData(Map map){
		return mapper.goodsAllListData(map);
	}
	
	public GoodsVO goodsAllDetailData(int no) {
		return mapper.goodsAllDetailData(no);
	}
	
	public int goodsAllTotalPage() {
		return mapper.goodsAllTotalPage();
	}
}
