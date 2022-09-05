package doo.doo.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class BoardAspect {
	//시점 -> 메서드를 적용하는 위치를 지정한다. == JoinPoint
	//어떤 메서드를 호출할 건지? == PointCut
	//JoinPoint + PointCut = Weaving
	@Before("execution(* doo.doo.web.*Controller.*(..))") //메서드에 진입할 때
	public void before(JoinPoint jp) {
		//ProceedingJoinPoint 는 around에서만 사용한다.
		//무슨 Controller로 끝나는 모든 클래스 안의 메서드에 해당함.
		String name = jp.getSignature().getName();//메서드이름 가져옴
		System.out.println(name+"/진입...");
	}
	@After("execution(* doo.doo.web.*Controller.*(..))") //메서드의 finally 위치 == 사이트에서 공통으로 출력하는 부분
	public void after(JoinPoint jp) {
		String name = jp.getSignature().getName();//메서드이름 가져옴
		System.out.println(name+"/정상적으로 수행 완료...");
	}
	@AfterReturning(value="execution(* doo.doo.web.*Controller.*(..))",
			returning="obj") //메서드가 정상수행완료되었을 때 return값을 받아서 처리한다.
	public void afterReturning(String obj) {
		System.out.println(obj+".jsp으로 이동이 완료...");
	}
}
