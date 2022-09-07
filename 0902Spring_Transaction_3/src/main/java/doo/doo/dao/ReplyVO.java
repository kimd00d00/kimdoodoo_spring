package doo.doo.dao;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReplyVO {
	private int no, bno, group_id, group_step, group_tab, root, depth;
	private Date regdate;
	private String id, name, msg, dbday;
}
