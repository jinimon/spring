package co.company.spring.controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/*
@Setter
@Getter
// 하위 두개는 생성자 만들어주는거
@NoArgsConstructor	// default 생성자
@AllArgsConstructor	// 전체필드를 가진 생성자
*/

@Data	// 얘가 위에꺼 통합해서 해준다.
@Builder // 생성자 만들지 않고 객체 생성 가능. 얘를 추가하면 default 생성자 안되서 별도로 추가해줘야함(@AllArgsConstructor)
@AllArgsConstructor
public class Member {
	String id;
	String name;
	String pw;
}
