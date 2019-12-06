package sample01;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

//공통모듈
public class LoggingAdvice {
	
	public void beforeTrace() {
		System.out.println("메서드 전=before trace...");
	}
	
	public void AfterTrace() {
		System.out.println("메서드 후=After trace...");
	}
	public void beforedis() {
		System.out.println("메서드 전=before display...");
	}
	
	public void trace(ProceedingJoinPoint joinPoint) throws Throwable {
		String methodName = joinPoint.getSignature().toShortString();
		System.out.println("메소드(methodName)="+methodName);
		
		StopWatch sw = new StopWatch();
		sw.start(methodName);
		System.out.println("호출 전 = "+ methodName);
		
		Object ob = joinPoint.proceed();//핵심관심사항 호출
		System.out.println("공통(ob) = "+ob);
		
		sw.stop();
		System.out.println("호출 후 = "+ methodName);
		System.out.println("시간 = "+sw.getTotalTimeMillis()/1000+"초");
	}

}
