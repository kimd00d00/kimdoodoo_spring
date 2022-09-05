package doo.doo.dao;

import java.util.*;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class DataBoardVO {
	private int no, hit, filecount;
	private Date regdate;
	private String name, subject, content, pwd, filename, filesize, dbday;
	private List<MultipartFile> files; //얘가 설정되어 있어야 업로드된 파일을 받을 수 있다(여러개 동시에 받을 수 있음)
}
