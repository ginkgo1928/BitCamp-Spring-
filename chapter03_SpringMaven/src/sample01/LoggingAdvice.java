package sample01;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

//공통모듈
@Component
@Aspect
public class LoggingAdvice {
	@Before("execution(public void sample01.MessageBeanImpl.*PrintBefore(..))")
	public void beforeTrace() {
		System.out.println("메서드 전=before trace...");
	}
	@After("execution(public * *.*.*PrintAfter())")
	public void AfterTrace() {
		System.out.println("메서드 후=After trace...");
	}
	@Before("execution(public * *.*.*display())")
	public void beforedis() {
		System.out.println("메서드 전=before display...");
	}
	
	@Around("execution(public * *.*.*Print(..))")
	public void trace(ProceedingJoinPoint joinPoint) throws Throwable {
		//앞부분 코드 추가(어라운드 방식)
		String methodName=joinPoint.getSignature().toShortString();
		System.out.println("메서드(methodName)="+methodName);
		
		StopWatch sw=new StopWatch();
		sw.start(methodName);
		System.out.println("호출 전="+methodName);
		
		//핵심관삼사항 호출
		Object ob=joinPoint.proceed(); 
		System.out.println("ob="+ob);
		
		//뒷부분 코드 추가
		sw.stop();
		System.out.println("호출 후="+methodName);
		System.out.println("처리시간="+sw.getTotalTimeMillis()/1000+"초");
		
		
	}

}
