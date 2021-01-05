package co.company.test.config;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

@Component	// @Controller, @Service, @Repository의 상위
// @ComponentScan에 의해 자동 등록되는? @들임.
public class TV {
//	@Autowired Speaker speaker;
//	@Qualifier("appleSpeaker")
//	@Qualifier("apple")	// 등록한 이름
	// resource = autowired
	@Resource(name="apple")	// Autowired + Qualifier : resource(name=xx)
	Speaker speaker;
	
	public void volumeup() {
		speaker.volumnup();
	}
	
	public TV() {
		System.out.println("TV 생성됨");
	}

	public void init() {
		System.out.println("객체 생성");
	}

	public void destory() {
		System.out.println("객체 소멸");
	}
}
