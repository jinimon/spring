package co.company.spring.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AfterAdvice {
	@Pointcut("execution(* co.company.spring..*Impl.get*(..))")
	public void getpointcut() {

	}

	@AfterReturning(pointcut = "getpointcut()", returning = "returnObj")
	public void afterLog(JoinPoint jp, Object returnObj) {
		String method = jp.getSignature().getName(); // 메소드명
		System.out.println("[공통로그] after" + method + "\n" + returnObj);
	}
}
