package co.company.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import co.company.spring.config.DBConfiguration;
import co.company.spring.dao.Emp;
import co.company.spring.dao.EmpDAOJdbcTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { DBConfiguration.class } )
public class EmpDaoJdbcTemplateClient {
	
	@Autowired EmpDAOJdbcTemplate empDAO;
	//@Test
	public void empdaotest() {
		System.out.println(empDAO.getListMap());
	}
	
	@Test
	public void insert() {
		System.out.println("insert 확인");
		Emp emp = new Emp();
		emp.setEmployeeId("1000");
		emp.setLastName("xx");
		emp.setEmail("xx@.xx");
		empDAO.insert(emp);
	}
}
