package doo.doo.mapper;

import java.util.*;
import doo.doo.dao.SeoulVO;
import org.apache.ibatis.annotations.Select;

public interface SeoulMapper {
	@Select("SELECT no, title, poster, rownum "
			+ "FROM Seoul_location "
			+ "WHERE rownum<=12")
	public List<SeoulVO> locationData();
	@Select("SELECT no, title, poster, rownum "
			+ "FROM Seoul_nature "
			+ "WHERE rownum<=12")
	public List<SeoulVO> natureData();
	@Select("SELECT no, title, poster, rownum "
			+ "FROM Seoul_hotel "
			+ "WHERE rownum<=12")
	public List<SeoulVO> hotelData();
	@Select("SELECT no, title, poster, rownum "
			+ "FROM Seoul_guest "
			+ "WHERE rownum<=12")
	public List<SeoulVO> guestData();
	@Select("SELECT no, title, poster, rownum "
			+ "FROM Seoul_shop "
			+ "WHERE rownum<=12")
	public List<SeoulVO> shopData();
	
}
