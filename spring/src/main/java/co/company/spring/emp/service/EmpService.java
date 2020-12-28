package co.company.spring.emp.service;

import java.util.List;
import java.util.Map;

import co.company.spring.dao.Departments;
import co.company.spring.dao.Emp;
import co.company.spring.dao.EmpSearch;
import co.company.spring.dao.Jobs;

public interface EmpService {
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
