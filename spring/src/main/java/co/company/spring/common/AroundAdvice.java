package co.company.spring.common;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
@Aspect
public class AroundAdvice {
	// pointcut 별도 지정하지 않고 다른 어드바이스에 지정된거 호출
	@Around("LogAdvice.allpointcut()")
	public Object aroundLog(ProceedingJoinPoint pjp) throws Throwable {
		// 비지니스수행전 처리내용
		StopWatch stopwatch = new StopWatch();
		stopwatch.start();
		// 비지니스 메서드 호출
		Object obj = pjp.proceed();
		// 비지니스수행후 처리내용
		stopwatch.stop();
		System.out.println("실행시간:" + stopwatch.getTotalTimeMillis());
		return obj;
	}
}