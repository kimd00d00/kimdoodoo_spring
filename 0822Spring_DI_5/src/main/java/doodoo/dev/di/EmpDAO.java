package doodoo.dev.di;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.*;
@Repository
public class EmpDAO {
	@Autowired //�ڵ� ����(�������� ���� �޸� �Ҵ�� �ڵ����� ������ �ּҰ��� �����Ѵ�.)
	private EmpMapper mapper;
	public List<EmpVO> empListData(){
		return mapper.empListData();
	}
}
