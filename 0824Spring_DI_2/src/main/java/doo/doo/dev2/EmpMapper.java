package doo.doo.dev2;
import java.util.*;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface EmpMapper {
	@Results({
		//vo.getDvo().setDname(rs.getString("dname"));
		@Result(property="dvo.dname",column="dname"),
		@Result(property="dvo.loc",column="loc")
	})
	//property=º¯¼ö¸í, column=
	@Select("SELECT empno, ename, job, hiredate, sal, emp.deptno, dname, loc "
			+ "FROM emp, dept "
			+ "WHERE emp.deptno=dept.deptno")
	public List<EmpVO> empListData();
}
