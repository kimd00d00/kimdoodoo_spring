package doo.doo.dao;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GoodsVO {
	private int no, goods_discount;
	private String goods_name, goods_sub, goods_price, goods_first_price,
		goods_delivery, goods_poster;
}
