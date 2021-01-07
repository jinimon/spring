package co.company.spring.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Slipcontroller {

	@RequestMapping("/slip")
	@ResponseBody
	public Map<String, Object> slip(@RequestBody List<SlipVO> list) {
		System.out.println("list : " + list);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("proecss", true);
		map.put("tcount", list.size());
		map.put("pcount", list.size());
		return map;
	}

	@RequestMapping("/slip2")
	// ResponseEntity로 return type 지정하면 @ResponseBody 없어도 된다. 자동으로 지정해줌
	public ResponseEntity slip2(@RequestBody List<SlipVO> list) {
		System.out.println("list : " + list);
		if (list.size() < 1) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); // 401
		} else {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("proecss", true);
			map.put("tcount", list.size());
			map.put("pcount", list.size());
			return ResponseEntity.status(HttpStatus.OK).body(map); // 200
		}
	}

	@RequestMapping("/slip3")
	public void slip3() {
		// HttpClient
		// HttpUrlConnection
		// 스프링에서는 RestTemplate 지원
		
		// java 객체 -> json string : Jackson
	}
}
