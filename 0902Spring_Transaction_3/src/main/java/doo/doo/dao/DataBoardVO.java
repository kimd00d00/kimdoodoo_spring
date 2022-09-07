package doo.doo.dao;
import java.util.*;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DataBoardVO {
	private int no, hit;
	private String name, subject, content, pwd, filename, filesize;
	private Date regdate;
	private List<MultipartFile> files;//파일 업로드용
}
