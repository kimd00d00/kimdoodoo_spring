package doo.doo.dao;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import doo.doo.mapper.*;
@Repository
public class MovieDAO {
	@Autowired
	private MovieMapper mapper;
	
	public List<MovieVO> movieListData(){
		return mapper.movieListData();
	}
	
	public MovieVO movieDetailData(int mno) {
		return mapper.movieDetailData(mno);
	}
}
