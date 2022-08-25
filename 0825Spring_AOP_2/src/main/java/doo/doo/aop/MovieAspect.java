package doo.doo.aop;

import org.aspectj.lang.ProceedingJoinPoint;

import doo.doo.dao.MovieVO;

import java.util.*;
/*
 * 공통 모듈 
 * -getConnection() -> Before
 * -disConnection() -> After
 * -기능 수행시 소요시간 출력() -> Around => 로그파일이나 트랜잭션, 보안 관련에 활용된다.
 */
public class MovieAspect {
	public Object around(ProceedingJoinPoint jp) throws Throwable{
		Object obj = null;
		long start = 0;
		long end = 0;
		try {
			start = System.currentTimeMillis(); //시작 시간
			System.out.println("Client 요청 메서드: "+jp.getSignature().getName());
			obj = jp.proceed();
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			end = System.currentTimeMillis();
			System.out.println("수행 시간:"+(end-start));
		}
		return obj;
	}
	
	public void afterReturning(Object obj) throws Throwable{
		List<MovieVO> list = (List<MovieVO>)obj;
		for(MovieVO vo:list) {
			System.out.println(vo.getMno()+"|"+vo.getTitle()+"|"+vo.getGenre());
		}
	}
	
	public void afterThrowing(Throwable ex) throws Throwable{
		System.out.println(ex.getMessage());
	}
}
