package doo.doo.dao;

import lombok.Getter;
import lombok.Setter;
import java.util.*;
@Getter
@Setter
/*
 * 이름         널?       유형           
---------- -------- ------------ 
NO         NOT NULL NUMBER     댓글의 고유 번호
BNO                 NUMBER     상품 번호  
ID                  VARCHAR2(20) 댓글 단 사람의 ID
NAME       NOT NULL VARCHAR2(34) 댓글 단 사람의 이름
MSG                 CLOB       댓글 내용
REGDATE             DATE       
GROUP_ID            NUMBER     그룹  
GROUP_STEP          NUMBER     그룹 출력 순서
GROUP_TAB           NUMBER     Depth
ROOT                NUMBER     누구에 대한 답변인지 확인
DEPTH               NUMBER     댓글이 몇개인지
TYPE                NUMBER     어느 상품에 달린 댓글인지
 */
public class ReplyVO {
	private int no, bno;
	private int group_id, group_step, group_tab, root, depth, type;
	private String id, name, msg;
	private Date regdate;
}