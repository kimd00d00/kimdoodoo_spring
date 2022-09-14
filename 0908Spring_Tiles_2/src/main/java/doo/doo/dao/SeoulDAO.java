package doo.doo.dao;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import doo.doo.mapper.SeoulMapper;
import doo.doo.vo.SeoulVO;

@Repository
public class SeoulDAO {
	@Autowired
	private SeoulMapper mapper;
	
	public List<SeoulVO> seoulListData(Map map){
		return mapper.seoulListData(map);
	}
	public SeoulVO seoulDetailData(Map map) {
		mapper.hitIncrement(map);
		return mapper.seoulDetailData(map); 
	}
	
	public int seoulTotalPage(Map map) {
		return mapper.seoulTotalPage(map);
	}
	
	public List<SeoulVO> seoulTop5(Map map){
		return mapper.seoulTop5(map);
	}
}
