package doo.doo.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
/*
 * aspect : ������(����� �κ��� �����ϴ� Ŭ����)
 * JoinPoint : ���� ȣ���� �� ������ ����
 *  = Before
 *  = After
 *  = AfterThrowing
 *  = AfterReturning
 *  = Around
 *  
 *  public String movieListData(){
 *  	==========Before
 *  	try{
 *  	==========Around(setAutoCommit(false))
 *  	//source
 *  	==========Around(commit())
 *  	}catch(Exception ex){
 *  	==========AfterThrowing(����Ȯ��, rollback()��)
 *  	}finally{
 *  	==========After(�����Ǽ���, setAutoCommit(true)) ��
 *  	}
 *  	return ��;
 *  	==========AfterReturning
 *  }
 *  
 * PointCut : � �޼��忡 ������ ���� ����Ѵ�.
 * 	= execution("* doo.doo.dev.MovieDAO.*(..)") : Ŭ���� ������ �ش� Ŭ���� �ȿ� �ִ� ��� �޼��带 ����
 * 		�տ� �ִ� ���� �������� ��Ÿ����,(=� �������̵� �������) �ڿ� �ִ� ���� ��� �޼���/��� �Ű�������� ��. 
 * 	= within("doo.doo.dev.*) : ��Ű�� ������ �ش� ��Ű���� �ִ� ��� Ŭ������ ����
 * Advice : JointPoint+PointCut , Advice�� �𿩼� Aspect�� �ȴ�
 * Weaving : ���� => Proxy ����(�븮��)
 */
public class MainClass {
	public static void main(String[] args) {
		ApplicationContext app = new ClassPathXmlApplicationContext("app1.xml");
		MovieDAO dao = (MovieDAO)app.getBean("dao");
		dao.movieDetailData();
	}
}
