package co.company.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import co.company.spring.dao.Departments;
import co.company.spring.dao.Emp;
import co.company.spring.dao.EmpMapper;
import co.company.spring.dao.EmpSearch;
import co.company.spring.dao.Jobs;

@Controller // 컨트롤러화
public class EmpController {

	@Autowired
	EmpMapper dao;

	@ModelAttribute("jobs")
	public List<Jobs> jobSelect2() {
		return dao.jobSelect();
		// 그냥 컨트롤일 경우는 model을 넘겨줬다.
	}
	

	@ModelAttribute("departments")
	public List<Departments> depts() {
		return dao.deptSelect();
	}

	@RequestMapping("/ajax/jobSelect")
	@ResponseBody // 그냥 컨트롤일 경우는 json으로 넘겨주기 위해 필요하다.
	public List<Jobs> jobSelect() {
		return dao.jobSelect();
		// 그냥 컨트롤일 경우는 model을 넘겨줬다.
	}
	
	@RequestMapping(value = "/empSelect", method = RequestMethod.GET)
	// 3. 목록 조회 페이지
	// 이거를 model&view로 수정하겠따. 307P
//	public String select(Model model, EmpSearch emp) {
	public ModelAndView select(EmpSearch emp) {
		ModelAndView mav = new ModelAndView(); // 객체 생성
		// db에서 전체 사원정보 조회
		// emp 대신 null 넣어주면 전체 조회
		mav.addObject("list", dao.getEmpList(emp));
		mav.setViewName("emp/select");

//		return "emp/select";
		return mav;
	}

	@GetMapping("/empinsertForm") // get 요청일때만 처리
	// 1. 등록 페이지로 이동
	// Model을 이용해서 view 페이지에 넘겨줄 값을 저장한다.
	public String insertForm(Model model, Emp emp) {
//		model.addAttribute("jobs", dao.jobSelect());
//		model.addAttribute("departments", dao.deptSelect());
		return "emp/insert"; // return 앞에 아무것도 없으면 forward 방식(default)
	}

	@PostMapping("/empInsert")
	// 아직 service 없으니까 dao만 불러서 처리
	// 순서 중요. 커맨드 객체보다 에러 객체가 먼저오면 안됨
	public String insert(/* @ModelAttribute("employee") */ Emp emp, Errors errors) {
		new EmpValidator().validate(emp, errors);
		if (errors.hasErrors()) {
			// 에러가 있다면 등록 페이지로 돌아감
			return "emp/insert";
		}
		
		// 에러 없으면 아래 정상 수행
		if (emp.getEmployeeId() == null)
			dao.insertEmp(emp);
		else
			dao.updateEmp(emp);
//		return "redirect:/empSelect";
//		request.setAttribute("emp", emp);	// 이거 안해줘도됨
		return "emp/insertOutput";
	}

	@GetMapping("/empUpdateForm")
	public String updateForm(Model model, Emp emp) {
		model.addAttribute("emp", dao.getEmp(emp)); // 단건 조회
//		model.addAttribute("jobs", dao.jobSelect());
//		model.addAttribute("departments", dao.deptSelect());

		return "emp/insert";
	}

}
