package co.company.spring.controller;

import lombok.Data;

@Data
public class SlipVO {
	// 넘겨주는 필드명과 vo 필드명 일치해야함

	String slipAmount;
	String salDt;
	String customer;
	String bankAcct;
}
