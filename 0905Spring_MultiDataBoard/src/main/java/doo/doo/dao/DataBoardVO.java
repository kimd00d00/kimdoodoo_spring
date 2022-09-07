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
	private List<MultipartFile> files; //�갡 �����Ǿ� �־�� ���ε�� ������ ���� �� �ִ�(������ ���ÿ� ���� �� ����)
}
