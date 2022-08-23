package doodoo.dev.dao;
import java.util.*;
public class EmpDAO {
	private EmpMapper mapper;
	public void setMapper(EmpMapper mapper) {
		this.mapper = mapper;
	}
	
	public List<EmpVO> empListData(){
		return mapper.empListData();
	}
	
	public EmpVO empDetailData(int empno) {
		return mapper.empDetailData(empno);//getConnection, disConnection이 이미 포함돼있다.
	}
	public void init() {
		System.out.println("====사원목록====");
	}
	public void destroy() {
		System.out.println("==============");
	}
}
