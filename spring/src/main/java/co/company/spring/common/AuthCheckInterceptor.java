package co.company.spring.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class AuthCheckInterceptor implements HandlerInterceptor {

	@Override
	// preHandle : 컨트롤러 실행 전에 무조건 실행되어서 return 값을 받는다. 로그인 체크
	// return 값에 따라서 controller로 넘어갈지 멈출지 결정
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession(false);
//		if (session != null) {
			String id = (String) session.getAttribute("login");
			// 세션에 값이 있으면 로그인 성공. 없으면 실패
			if (id != null) {
				return true;
			}
//		}
		response.sendRedirect("login");
		return false;
	}

	@Override
	// postHandle : 로그 출력
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("post 인터셉터");
	}

}
