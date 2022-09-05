package doo.doo.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect //공통 모듈 관리 == 메모리 할당을 못하므로 @Component도 꼭 붙여 줘야 한다
@Component
public class GoodsAOP {
	//doo.doo.web의 어쩌구컨트롤러의 "모든" 메서드가 호출될 때 작동한다.
	@Around("execution(* doo.doo.web.*Controller.*(..))")
	public Object around(ProceedingJoinPoint jp) throws Throwable{
		Object obj = null;
		try {
			long start = System.currentTimeMillis();
			obj = jp.proceed(); //메서드 호출
			long end = System.currentTimeMillis();
			System.out.println("=================================");
			System.out.println("사용자 요청 메서드:"+jp.getSignature().getName());
			System.out.println("수행 시간:"+(end-start)+"밀리초");
			System.out.println("=================================");
		}catch(Exception ex) {}
		return obj;
	}
	@AfterReturning(value="execution(* doo.doo.web.*Controller.*(..))",returning="obj")
	public void afterReturning(Object obj) throws Throwable{
		System.out.println("============화면 이동=============");
		System.out.println(obj);
		System.out.println("===============================");
	}
}
