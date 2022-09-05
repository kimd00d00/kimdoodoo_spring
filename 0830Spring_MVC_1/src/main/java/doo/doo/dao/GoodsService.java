package doo.doo.dao;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service //¿©·¯°³ÀÇ DAO¸¦ ¹­¾î¼­ ¸¸µë
public class GoodsService {
	@Autowired
	private GoodsAllDAO aDao;
	@Autowired
	private GoodsBestDAO bDao;
	@Autowired
	private GoodsNewDAO nDao;
	@Autowired
	private GoodsSpecialDAO sDao;
	
	public List<GoodsVO> goodsAllListData(Map map){
		return aDao.goodsAllListData(map);
	}
	
	public GoodsVO goodsAllDetailData(int no) {
		return aDao.goodsAllDetailData(no);
	}
	
	public int goodsAllTotalPage() {
		return aDao.goodsAllTotalPage();
	}
	
	public List<GoodsVO> goodsBestListData(Map map){
		return bDao.goodsBestListData(map);
	}
	
	public GoodsVO goodsBestDetailData(int no) {
		return bDao.goodsBestDetailData(no);
	}
	
	public List<GoodsVO> goodsNewListData(Map map){
		return nDao.goodsNewListData(map);
	}
	
	public GoodsVO goodsNewDetailData(int no) {
		return nDao.goodsNewDetailData(no);
	}
	
	public int goodsNewTotalPage() {
		return nDao.goodsNewTotalPage();
	}
	
	public List<GoodsVO> goodsSpecialListData(Map map){
		return sDao.goodsSpecialListData(map);
	}
	
	public GoodsVO goodsSpecialDetailData(int no) {
		return sDao.goodsSpecialDetailData(no);
	}
	
}
