package co.company.spring.common;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {

	@Scheduled(fixedRate = 5000)
	public void cronTest2() {
		System.out.println("5초마다 호출이 됩니다.");
	}
}
