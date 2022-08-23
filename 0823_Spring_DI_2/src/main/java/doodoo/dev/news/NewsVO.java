package doodoo.dev.news;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewsVO {
	private String title;
	private String description;
	private String author;
	private String regdate;
}
