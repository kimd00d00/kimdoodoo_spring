package doo.doo.dao;

import java.util.*;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class ReplyVO {
	private int no, bno, group_id, group_step, group_tab, root, depth, type;
	private String id, name, msg, dbday;
	private Date regdate;
}
