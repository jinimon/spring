package co.company.spring.dao;

import java.util.List;
import java.util.Map;

// 인터페이스 파일명이 emp_mapper.xml 파일의 namespace와 일치해야한다.(패키지명.인터페이스파일명)
public interface EmpMapper {
	// List : 단건 조회
	// reusltType(List<Emp>)과 returnType 일치해야 한다.
	// 넘겨주는값(Emp emp)과 parameterType 일치해야 한다.
	// getCount는 getEmpList 할 때 필요한거니까 where 조건절이 같아야함
	public List<Emp> getEmpList(EmpSearch emp);
	public int getCount(EmpSearch emp);
	
	public int updateEmp(Emp emp);
	public Emp getEmp(Emp emp);
	public int insertEmp(Emp emp);
	public void insertEmpProc(Emp emp);
	public int deleteEmp(Emp emp);
	public int deleteMultiEmp(EmpSearch emp);
	public List<Map<String, Object>>  getStatDept();
	public List<Jobs> jobSelect();
	public List<Departments> deptSelect();
}
