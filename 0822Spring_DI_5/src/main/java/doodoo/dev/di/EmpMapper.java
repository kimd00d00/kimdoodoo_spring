package doodoo.dev.di;

import java.util.*;

import org.apache.ibatis.annotations.Select;
public interface EmpMapper {
	@Select("SELECE empno, ename, job, hiredate, sal "
			+ "FROM emp")
	public List<EmpVO> empListData();
	
}
