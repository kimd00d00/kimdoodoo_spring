package doo.doo.service;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import doo.doo.dao.*;
import doo.doo.vo.*;
//������(���ռ�)�� ���� ���α׷�! == �ٸ� Ŭ������ ������ �� �־�� �� => ĸ��ȭ
@Service
public class RecipeServiceImpl implements RecipeService {
	@Autowired
	private RecipeDAO dao;
	@Override
	public List<RecipeVO> recipeListData(Map map) {
		return dao.recipeListData(map);
	}
	@Override
	public int recipeTotalPage() {
		return dao.recipeTotalPage();
	}
	@Override
	public List<RecipeVO> recipeFindData(String ss) {
		return dao.recipeFindData(ss);
	}

}
