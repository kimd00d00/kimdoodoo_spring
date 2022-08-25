package doo.doo.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
/*
 * aspect : 공통모듈(공통된 부분을 관리하는 클래스)
 * JoinPoint : 언제 호출할 지 시점을 지정
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
 *  	==========AfterThrowing(에러확인, rollback()등)
 *  	}finally{
 *  	==========After(무조건수행, setAutoCommit(true)) 등
 *  	}
 *  	return 값;
 *  	==========AfterReturning
 *  }
 *  
 * PointCut : 어떤 메서드에 적용할 건지 명시한다.
 * 	= execution("* doo.doo.dev.MovieDAO.*(..)") : 클래스 단위로 해당 클래스 안에 있는 모든 메서드를 선택
 * 		앞에 있는 별은 리턴형을 나타내고,(=어떤 리턴형이든 상관없다) 뒤에 있는 별은 모든 메서드/모든 매개변수라는 뜻. 
 * 	= within("doo.doo.dev.*) : 패키지 단위로 해당 패키지에 있는 모든 클래스를 선택
 * Advice : JointPoint+PointCut , Advice가 모여서 Aspect가 된다
 * Weaving : 통합 => Proxy 패턴(대리자)
 */
public class MainClass {
	public static void main(String[] args) {
		ApplicationContext app = new ClassPathXmlApplicationContext("app1.xml");
		MovieDAO dao = (MovieDAO)app.getBean("dao");
		dao.movieDetailData();
	}
}
