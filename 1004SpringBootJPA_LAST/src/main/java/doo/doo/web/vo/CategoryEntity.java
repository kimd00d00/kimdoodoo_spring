package doo.doo.web.vo;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="food_category") //DB상 테이블명 명시
public class CategoryEntity {
	@Id
	private int cno;//PRIMARY KEY니까
	private String title, subject, poster, link;
}
