package doo.doo.app;
import doo.doo.dao.*;
import java.util.*;

public class MainClass {

	public static void main(String[] args) {
		MemberDAO dao = new MemberDAO();
		List<MemberVO> list = dao.memberListData();
		
		for(MemberVO vo:list) {
			System.out.println(vo.getNo()+"."+vo.getName());
		}
	}

}
