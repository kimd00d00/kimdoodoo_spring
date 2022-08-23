package doodoo.dev.dao;
//XML���� �������̽��θ� ������

import java.util.List;
import org.apache.ibatis.annotations.Select;

public interface EmpMapper {
	@Select("SELECT empno, ename, job, hiredate, sal, deptno "
			+ "FROM emp")
	public List<EmpVO> empListData();
	@Select("SELECT * FROM emp "
			+ "WHERE empno=#{empno}")
	public EmpVO empDetailData(int empno);
}
