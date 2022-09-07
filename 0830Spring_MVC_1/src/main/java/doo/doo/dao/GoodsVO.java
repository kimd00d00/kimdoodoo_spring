package doo.doo.dao;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GoodsVO {
	private int no, discount;
	private String name, sub, price, first_price, delivery, poster;
}
