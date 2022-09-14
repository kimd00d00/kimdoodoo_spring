package doo.doo.aop;

import java.util.*;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import doo.doo.vo.*;
import doo.doo.manager.MusicManager;
import doo.doo.service.*;

@Aspect //공통으로 사용될 것
@Component //메모리 할당하세요
public class CommonsAOP {
	@Autowired
	private SeoulService service;
	@Autowired
	private MusicManager mgr;
	
	@After("execution(* doo.doo.web.*Controller.*(..))")
   public void after(){
	try {
		// 현재 사용중인 request를 얻어 온다 
		   HttpServletRequest request=
				   ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		   Map map=new HashMap();
		   map.put("table_name", "seoul_location");
		   List<SeoulVO> sList=service.seoulTop5(map);
		   map.put("table_name", "seoul_nature");
		   List<SeoulVO> nList=service.seoulTop5(map);
		   request.setAttribute("sList", sList);
		   request.setAttribute("nList", nList);
		   
		   List<MusicVO> mList = mgr.musicTop5();
		   request.setAttribute("mList", mList);
	}catch(Exception ex) {
		ex.printStackTrace();
	}finally {
	}
   }
}
