package doo.doo.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class BoardAspect {
	//���� -> �޼��带 �����ϴ� ��ġ�� �����Ѵ�. == JoinPoint
	//� �޼��带 ȣ���� ����? == PointCut
	//JoinPoint + PointCut = Weaving
	@Before("execution(* doo.doo.web.*Controller.*(..))") //�޼��忡 ������ ��
	public void before(JoinPoint jp) {
		//ProceedingJoinPoint �� around������ ����Ѵ�.
		//���� Controller�� ������ ��� Ŭ���� ���� �޼��忡 �ش���.
		String name = jp.getSignature().getName();//�޼����̸� ������
		System.out.println(name+"/����...");
	}
	@After("execution(* doo.doo.web.*Controller.*(..))") //�޼����� finally ��ġ == ����Ʈ���� �������� ����ϴ� �κ�
	public void after(JoinPoint jp) {
		String name = jp.getSignature().getName();//�޼����̸� ������
		System.out.println(name+"/���������� ���� �Ϸ�...");
	}
	@AfterReturning(value="execution(* doo.doo.web.*Controller.*(..))",
			returning="obj") //�޼��尡 �������Ϸ�Ǿ��� �� return���� �޾Ƽ� ó���Ѵ�.
	public void afterReturning(String obj) {
		System.out.println(obj+".jsp���� �̵��� �Ϸ�...");
	}
}
