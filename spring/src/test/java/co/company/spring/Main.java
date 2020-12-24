package co.company.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import co.company.spring.config.MvcConfiguration;
import co.company.spring.controller.Greeter;

public class Main {
	public static void main(String[] args) {
//		Greeter g = new Greeter();
//		g.setformat("%s, 안녕하세요");
		// 컨테이너 생성. 스프링에서는 자동 생성될거라 필요없어짐
		AnnotationConfigApplicationContext ctx =
		new AnnotationConfigApplicationContext(MvcConfiguration.class);
		Greeter g = ctx.getBean(Greeter.class);
		String msg = g.greet("스프링");
		System.out.println(msg);
	}
}