package doo.doo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import doo.doo.dao.*;
import doo.doo.vo.*;

@Service
public class FoodServiceImpl implements FoodService{
	@Autowired
	private FoodDAO dao;
	
	@Override
	public List<CategoryVO> categoryListData(Map map) {
		return dao.categoryListData(map);
	}

	@Override
	public List<FoodVO> foodListData(int cno) {
		return dao.foodListData(cno);
	}

	@Override
	public CategoryVO categoryInfoData(int cno) {
		return dao.categoryInfoData(cno);
	}

	@Override
	public FoodVO foodDetailData(int fno) {
		return dao.foodDetailData(fno);
	}

	@Override
	public List<FoodVO> foodFindData(Map map) {
		return dao.foodFindData(map);
	}

	@Override
	public int foodLocationTotalPage(String address) {
		return dao.foodLocationTotalPage(address);
	}

	@Override
	public FoodVO foodDetailVueData(int fno) {
		return dao.foodDetailVueData(fno);
	}

}
