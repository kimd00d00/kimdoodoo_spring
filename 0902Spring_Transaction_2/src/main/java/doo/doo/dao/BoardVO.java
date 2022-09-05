package doo.doo.dao;

import java.util.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardVO {
	private int no, hit;
	private int group_id, group_step, group_tab, root, depth;
	private String name, subject, content, pwd, dbday;
	private Date regdate;
}
