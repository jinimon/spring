package co.company.spring;

import co.company.spring.controller.Member;

public class LombokTest {
	public static void main(String[] args) {
		// builder를 사용해서 생성자 없이 바로 생성해서 넣는다.
		Member member = Member.builder().name("testname").id("testid").build();
	}
}
