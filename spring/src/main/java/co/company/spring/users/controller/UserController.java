package co.company.spring.users.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import co.company.spring.users.service.UserService;
import co.company.spring.users.service.UserVO;

@Controller
public class UserController {

	@Autowired
	UserService service;

	// jsp에 준 action이랑 이름 같아야함
	// 등록 페이지로 이동
	@GetMapping("/userInsert")
	public String userInsertForm() {
		return "user/insert"; // jsp 폴더.파일명 확인하기
	}

	// 등록 처리
	@PostMapping("/userInsert")
	public String userInsert(HttpServletRequest request, UserVO user) throws IllegalStateException, IOException {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		// 이미지파일
		// request를 multipart로 casting
		MultipartFile multipartFile = multipartRequest.getFile("uploadFile"); // name 속성 입력
		if (!multipartFile.isEmpty() && multipartFile.getSize() > 0) {
			String path = request.getSession().getServletContext().getRealPath("/images"); // 업로드할 경로
			System.out.println("이미지 경로 : " + path);
			// getOriginalFilename : 업로드 후 파일명
			multipartFile.transferTo(new File(path, multipartFile.getOriginalFilename()));
			user.setProfile(multipartFile.getOriginalFilename());
		}

		// insert 호출
		service.insertUser(user);
		return "user/insert"; // 등록 후 이동 페이지
	}
	
	// 단건 조회
	@RequestMapping("/getUser")
	public String getUser(UserVO user, Model model) {
		model.addAttribute("user", service.getUser(user));
		return "user/user";
	}

	// 파일 다운로드
	@RequestMapping("/filedown")
	public void filedown(HttpServletResponse response, HttpServletRequest request, @RequestParam String uFile)
			throws IOException {
		response.setContentType("application/octet-stream;charset=UTF-8"); // 이게 들어가야 브라우저 타입을 보고 뭐.. 처리한다.
		String fn = URLEncoder.encode(uFile, "utf-8");	// 다운받을 파일 명
		response.setHeader("Content-Disposition", "attachment;filename=\"" + fn + "\"");
		BufferedInputStream in = null;
		BufferedOutputStream out = null;
		try {
			String path = request.getSession().getServletContext().getRealPath("/images");
			in = new BufferedInputStream(new FileInputStream(path + "/" + uFile)); // 원본 파일 읽어서
			out = new BufferedOutputStream(response.getOutputStream()); // 카피 한다. 그게 다운로드
			FileCopyUtils.copy(in, out);
			out.flush();
		} catch (IOException ex) {
		} finally {
			in.close();
			response.getOutputStream().flush();
			response.getOutputStream().close();
		}
	}
}
