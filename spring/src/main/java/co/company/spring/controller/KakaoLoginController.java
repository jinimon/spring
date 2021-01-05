package co.company.spring.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.JsonNode;

@Controller
public class KakaoLoginController {

	@RequestMapping(value = "/loginform", method = RequestMethod.GET)
	public ModelAndView memberLoginForm(HttpSession session) {
		ModelAndView mav = new ModelAndView(); /* 네아로 인증 URL을 생성하기 위하여 getAuthorizationUrl을 호출 */
		String kakaoUrl = KakaoAPI.getAuthorizationUrl(); /* 생성한 인증 URL을 View로 전달 */
		mav.setViewName("user/login"); // 네이버 로그인. user폴더 안에 있음
		// mav.addObject("naver_url", naverAuthUrl); // 카카오 로그인
		mav.addObject("kakao_url", kakaoUrl);
		return mav;
	}

	@RequestMapping(value = "/kakaologin", produces = "application/json")
	// @RequestParam("code") : 인가코드
	public ModelAndView kakaoLogin(@RequestParam("code") String code, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {
		ModelAndView mav = new ModelAndView(); // 결과값을 node에 담아줌
		// getAccessToken : access token 값을 읽어낸다.
		JsonNode node = KakaoAPI.getAccessToken(code); // accessToken에 사용자의 로그인한 모든 정보가 들어있음
		JsonNode accessToken = node.get("access_token"); // 사용자의 정보
		JsonNode userInfo = KakaoAPI.getKakaoUserInfo(accessToken);
		// userInfo : 유저 정보를 받아온다.
		String kemail = null;
		String kname = null;
		String kgender = null;
		String kbirthday = null;
		String kage = null;
		String kimage = null; // 유저정보 카카오에서 가져오기 Get properties
		JsonNode properties = userInfo.path("properties");
		JsonNode kakao_account = userInfo.path("kakao_account");
		kemail = kakao_account.path("email").asText();
		kname = properties.path("nickname").asText();
		kimage = properties.path("profile_image").asText();
		kgender = kakao_account.path("gender").asText();
		kbirthday = kakao_account.path("birthday").asText();
		kage = kakao_account.path("age_range").asText();
		session.setAttribute("kemail", kemail);
		session.setAttribute("kname", kname);
		session.setAttribute("kimage", kimage);
		session.setAttribute("kgender", kgender);
		session.setAttribute("kbirthday", kbirthday);
		session.setAttribute("kage", kage);
		mav.setViewName("home");	// home.jsp로 넘긴다.
		return mav;
	}
}
