package co.company.spring.dao;

import lombok.Data;

@Data
public class EmpSearch extends Emp {
	Integer minSalary;
	Integer maxSalary;
	Integer first;
	Integer last;
	String[] list;	// List
}
