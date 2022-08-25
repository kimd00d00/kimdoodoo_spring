package doo.doo.aop;

import org.aspectj.lang.ProceedingJoinPoint;

import doo.doo.dao.MovieVO;

import java.util.*;
/*
 * ���� ��� 
 * -getConnection() -> Before
 * -disConnection() -> After
 * -��� ����� �ҿ�ð� ���() -> Around => �α������̳� Ʈ�����, ���� ���ÿ� Ȱ��ȴ�.
 */
public class MovieAspect {
	public Object around(ProceedingJoinPoint jp) throws Throwable{
		Object obj = null;
		long start = 0;
		long end = 0;
		try {
			start = System.currentTimeMillis(); //���� �ð�
			System.out.println("Client ��û �޼���: "+jp.getSignature().getName());
			obj = jp.proceed();
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			end = System.currentTimeMillis();
			System.out.println("���� �ð�:"+(end-start));
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
