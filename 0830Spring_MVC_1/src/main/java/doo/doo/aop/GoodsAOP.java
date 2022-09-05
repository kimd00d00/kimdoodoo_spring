package doo.doo.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect //���� ��� ���� == �޸� �Ҵ��� ���ϹǷ� @Component�� �� �ٿ� ��� �Ѵ�
@Component
public class GoodsAOP {
	//doo.doo.web�� ��¼����Ʈ�ѷ��� "���" �޼��尡 ȣ��� �� �۵��Ѵ�.
	@Around("execution(* doo.doo.web.*Controller.*(..))")
	public Object around(ProceedingJoinPoint jp) throws Throwable{
		Object obj = null;
		try {
			long start = System.currentTimeMillis();
			obj = jp.proceed(); //�޼��� ȣ��
			long end = System.currentTimeMillis();
			System.out.println("=================================");
			System.out.println("����� ��û �޼���:"+jp.getSignature().getName());
			System.out.println("���� �ð�:"+(end-start)+"�и���");
			System.out.println("=================================");
		}catch(Exception ex) {}
		return obj;
	}
	@AfterReturning(value="execution(* doo.doo.web.*Controller.*(..))",returning="obj")
	public void afterReturning(Object obj) throws Throwable{
		System.out.println("============ȭ�� �̵�=============");
		System.out.println(obj);
		System.out.println("===============================");
	}
}
