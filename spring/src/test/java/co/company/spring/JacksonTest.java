package co.company.spring;

import java.net.URL;

import org.junit.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.company.spring.controller.SlipVO;

public class JacksonTest {

	@Test
	public void test1() {
		ObjectMapper mapper = new ObjectMapper();	// 구글 gson과 유사
		// readTree : jsonNode로, readValue : json 객체로 변환
		// writeTree, writeValue | writeValueAsString : object를 string으로
		String str = "[{\"slipAmount\":15972,\"salDt\":\"20210107\",\"customer\":\"114\",\"bankAcct\":\"sdfs\"}]";
		
		try {
			JsonNode node = mapper.readTree(str);
			// JsonNode에서 값을 가져올때는 get이나 path
			System.out.println(node.get(0).path("slipAmount"));
			
			SlipVO[] list = mapper.readValue(str, SlipVO[].class);
			System.out.println(list[0].getSlipAmount());

			URL url = new URL("	http://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key=f5eef3421c602c6cb7ea224104795888&targetDt=20120101");
			JsonNode movie = mapper.readTree(url);
			System.out.println(movie);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
