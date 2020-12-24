package co.company.spring;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import co.company.spring.config.DBConfiguration;
import co.company.spring.config.MybatisConfiguration;
import co.company.spring.dao.Emp;
import co.company.spring.dao.EmpMapper;
import co.company.spring.dao.EmpSearch;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { 
		DBConfiguration.class, 
		MybatisConfiguration.class } )
public class EmpDaoMapperClient {
	
	@Autowired EmpMapper empDAO;
	
	// 부서별 사원 조회
	@Test
	public void deptcnt() {
		// 다건이면 list<map>으로. 단건이면 list 없이 map으로만 받으면 된다.
		List<Map<String, Object>> list = empDAO.getStatDept();
		System.out.println(list.get(0));
		System.out.println(list.get(0).get("deptName"));
	}
	
	// 건수 조회
	//@Test
	public void selcnttest() {
		EmpSearch empvo = new EmpSearch();
		System.out.println(empDAO.getCount(empvo)+"건");
	}
	
	//@Test
	// 전체 조회
	public void empdaotest() {
		EmpSearch empvo = new EmpSearch();
//		empvo.setFirstName("den");	// 검색어 넘겨줌
//		empvo.setDepartmentId("60");
//		empvo.setSalary(10000);
		empvo.setMinSalary(3000);
		empvo.setMaxSalary(10000);
		List<Emp> list = empDAO.getEmpList(empvo);
		for(Emp emp : list) {
			System.out.println(emp.getEmployeeId() + " : " 
							 + emp.getFirstName() + " | " 
							 + emp.getDepartmentId() + " | "
							 + emp.getSalary() + " | "
							 + emp.getJobId());
		}
	}
	
	//@Test
	// 수정
	public void updateTest() {
		Emp emp = new Emp();
		// 단건 조회
		emp.setEmployeeId("102");
		System.out.println(empDAO.getEmp(emp));
		// 수정
		emp.setFirstName("hong");
		empDAO.updateEmp(emp);
		// 수정 후 단건 조회
		System.out.println(empDAO.getEmp(emp));
	}
	
	// 등록
	//@Test
	public void InsertTest() {
		Emp emp = new Emp();
//		emp.setEmployeeId("1001");
		// selectKey 추가해서 이거 해줄 필요 없다.
		emp.setFirstName("mongmong");
		emp.setLastName("choi");
		emp.setEmail("a@a.a1");
		emp.setJobId("IT_PROG");
		emp.setHireDate(new Date(System.currentTimeMillis()));
		
		empDAO.insertEmp(emp);
		System.out.println(emp.getEmployeeId()); // 등록되는 번호 확인
	}
	
	// 프로시저 등록
	//@Test
	public void InsertProcTest() {
		Emp emp = new Emp();
		emp.setLastName("choi");
		emp.setJobId("IT_PROG");
		emp.setEmail("a@a.ee");
		
		empDAO.insertEmpProc(emp);
		System.out.println(emp.getEmployeeId()
						 + " : " + emp.getMsg()); // 등록되는 번호 확인
	}
	
	// 다건 삭제
	//@Test
	public void deleteMultiTest() {
		EmpSearch emp = new EmpSearch();
		emp.setList(new String[] {"1000", "1001"});
		empDAO.deleteMultiEmp(emp);
	}
}
