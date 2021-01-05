package co.company.test.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { IOCConfiguration.class })
public class TVclient {
	// @Autowired : 컨테이너에 있는 빈 객체 가져오는것
	// 두개를 이름 다르게 가져와도 결국 같은 객체임
	@Autowired
	TV tv;
	@Autowired
	TV tv2;

	@Test
	public void tvtest() {
		System.out.println(tv == tv2);
		tv.volumeup();
	}
}
