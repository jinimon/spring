package co.company.test.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"co.company.test"})	// 내가 만든 클래스면 자동 스캔 가능
public class IOCConfiguration {

	/*
	 * @Bean(initMethod = "init", destroyMethod = "destory") // prototype : 요청 할때마다
	 * 객체 새로 생성
	 * 
	 * @Scope("prototype") // singletone(default), request, session, prototype
	 * public TV tv() { return new TV(); }
	 */
}
