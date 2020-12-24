package co.company.spring.users.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

	// 로그인 페이지로 이동
	@GetMapping("/login")
	public String loginForm() {
		return "user/login";
	}

	// 로그인 처리
	@PostMapping("/login")
	public String login(HttpSession session) {
		// 로그인 id, pwd 맞는지 확인 후 세션에 저장
		session.setAttribute("loginId", "user");
		return "redirect:/empSelect";	// 사원 목록으로 이동
	}

	// 로그 아웃
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		// 세션 객체 받아와서 처리
		session.invalidate();
		
		return "user/login";
	}
}
