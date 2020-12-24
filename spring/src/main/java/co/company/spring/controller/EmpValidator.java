package co.company.spring.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import co.company.spring.dao.Emp;

public class EmpValidator implements Validator {
	
	// 이메일 정규식(pattern match)
	public final static String emailRegExp = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$";
	private Pattern pattern;
	
	public EmpValidator() {
		pattern = Pattern.compile(emailRegExp);
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Emp.class.isAssignableFrom(clazz);
	}

	@Override
	// target : 검증할 대상. errors : 에러 메시지 담을 객체
	public void validate(Object target, Errors errors) {
		Emp emp = (Emp) target;
		// 필수 입력 체크
		// errors.rejectValue(필드, 에러코드, 에러메시지)
		if (emp.getLastName() == null || emp.getLastName().trim().isEmpty()) {
			// errorArgs : 넘겨줄 변수
			errors.rejectValue("lastName", "required", new Object[] {"lastName"}, "");
		}

		// null 체크 위에처럼 하기 번거움..
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "jobId", "required", new Object[] {"jobId"}, "");

		// 이메일 체크
		if(emp.getEmail() == null || emp.getEmail().trim().isEmpty()) {
			errors.rejectValue("email", "required", new Object[] {"email"}, "");
		} else {
			Matcher matcher = pattern.matcher(emp.getEmail());
			if (!matcher.matches()) {
				errors.rejectValue("email", "bad");
			}
		}
	}
}
