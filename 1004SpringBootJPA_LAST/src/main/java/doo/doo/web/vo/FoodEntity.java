package doo.doo.web.vo;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="food_house")
public class FoodEntity {
	@Id
	private int fno;
	private int cno;
	private double score;
	private String name, address, tel;
	private String id, msg, type, price, time, parking, menu, poster;
}
