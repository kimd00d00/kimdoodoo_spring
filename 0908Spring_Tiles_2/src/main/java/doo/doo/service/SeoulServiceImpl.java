package doo.doo.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import doo.doo.vo.SeoulVO;
import doo.doo.dao.SeoulDAO;

@Service
public class SeoulServiceImpl implements SeoulService{
	@Autowired
	private SeoulDAO dao;
	
	@Override
	public List<SeoulVO> seoulListData(Map map) {
		return dao.seoulListData(map);
	}

	@Override
	public SeoulVO seoulDetailData(Map map) {
		return dao.seoulDetailData(map);
	}

	@Override
	public int seoulTotalPage(Map map) {
		return dao.seoulTotalPage(map);
	}

	@Override
	public List<SeoulVO> seoulTop5(Map map) {
		return dao.seoulTop5(map);
	}
	
}
