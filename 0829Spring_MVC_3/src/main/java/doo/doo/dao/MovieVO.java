package doo.doo.dao;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieVO {
	private int mno, cno, hit;
	private String title, grade, reserve, genre, actor, director, regdate, time, showuser, poster, key;
	private double score;
}
