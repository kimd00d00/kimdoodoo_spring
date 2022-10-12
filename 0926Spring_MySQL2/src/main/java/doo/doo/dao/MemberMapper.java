package doo.doo.dao;
import java.util.*;

import org.apache.ibatis.annotations.Select;

import doo.doo.dao.*;

public interface MemberMapper {
	@Select("SELECT no, name, sex, content FROM member")
	public List<MemberVO> memberListData();
}
