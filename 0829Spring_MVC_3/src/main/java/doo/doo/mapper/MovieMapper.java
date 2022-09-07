package doo.doo.mapper;

import java.util.*;
import doo.doo.dao.*;
import org.apache.ibatis.annotations.Select;

public interface MovieMapper {
	@Select("SELECT * FROM project_movie")
	public List<MovieVO> movieListData();
	
	@Select("SELECT * FROM project_movie WHERE mno=#{mno}")
	public MovieVO movieDetailData(int mno);
}
