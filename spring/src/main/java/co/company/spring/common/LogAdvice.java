package co.company.spring.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component // 빈 등록
@Aspect // <aop:aspect>
public class LogAdvice {
	@Pointcut("execution(* co.company.spring..*Impl.*(..))")
	// servlet-context.xml에서 aop:pointcut 태그에 id 지정해준게 메소드명이된다.
	public void allpointcut() {

	}

	@Before("allpointcut()") // pointcut 명
	public void printLog(JoinPoint jp) {
		String method = jp.getSignature().getName(); // 메소드명
		Object[] param = jp.getArgs();
		System.out.println("[공통 로그] before " + method + "\n" + ((param != null && param.length>0) ? param[0] : ""));
	}
}
