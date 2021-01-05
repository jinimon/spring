package co.company.test.config;

import org.springframework.stereotype.Component;

@Component("apple")	// 이름 지정가능
public class AppleSpeaker implements Speaker {

	@Override
	public void volumnup() {
		System.out.println("AppleSpeaker");
	}
}
