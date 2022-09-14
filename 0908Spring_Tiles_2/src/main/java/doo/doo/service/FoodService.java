package doo.doo.service;

import java.util.*;
import doo.doo.vo.*;

public interface FoodService {
	public List<CategoryVO> categoryListData(Map map);
	public List<FoodVO> foodListData(int cno);
	public CategoryVO categoryInfoData(int cno);
	public FoodVO foodDetailData(int fno);
	public List<FoodVO> foodFindData(Map map);
	public int foodLocationTotalPage(String address);
}
