package doo.doo.vo;

import lombok.Getter;
import lombok.Setter;
import java.util.*;
@Getter
@Setter
public class MemberVO {
	private String id;
	private String pwd;
	private String name;
	private String sex;
	private String birthday;
	private String email;
	private String post;
	private String addr1;
	private String addr2;
	private String tel, tel1, tel2;
	private String content;
	private String sessionId;
	private String role;
	private Date limited;
}