package doo.doo.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import doo.doo.vo.*;

public interface MemberMapper {
	//회원가입 ==> 비밀번호 암호화
	@Select("SELECT COUNT(*) FROM spring_join WHERE id=#{id}")
	public int memberIdCheck(String id);
	@Insert("INSERT INTO spring_join VALUES("
			+ "#{id}, #{pwd}, #{name}, #{sex}, #{birthday}, #{email}, #{post},"
			+ "#{addr1}, #{addr2}, #{tel}, #{content}, '', null, 'ROLE_USER')")
	public void memberJoin(MemberVO vo);
	
	//로그인 처리 ==> 복호화 ==> 자동 로그인 처리
	@Select("SELECT pwd, name, role FROM spring_join "
			+ "WHERE id=#{id}")
	public MemberVO memberJoinInfoData(String id);
	
	//회원 탈퇴 ==> 복호화
	
	
	//회원 수정 ==> 복호화
	
	
}
