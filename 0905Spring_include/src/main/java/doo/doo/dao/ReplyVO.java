package doo.doo.dao;

import lombok.Getter;
import lombok.Setter;
import java.util.*;
@Getter
@Setter
/*
 * �̸�         ��?       ����           
---------- -------- ------------ 
NO         NOT NULL NUMBER     ����� ���� ��ȣ
BNO                 NUMBER     ��ǰ ��ȣ  
ID                  VARCHAR2(20) ��� �� ����� ID
NAME       NOT NULL VARCHAR2(34) ��� �� ����� �̸�
MSG                 CLOB       ��� ����
REGDATE             DATE       
GROUP_ID            NUMBER     �׷�  
GROUP_STEP          NUMBER     �׷� ��� ����
GROUP_TAB           NUMBER     Depth
ROOT                NUMBER     ������ ���� �亯���� Ȯ��
DEPTH               NUMBER     ����� �����
TYPE                NUMBER     ��� ��ǰ�� �޸� �������
 */
public class ReplyVO {
	private int no, bno;
	private int group_id, group_step, group_tab, root, depth, type;
	private String id, name, msg;
	private Date regdate;
}