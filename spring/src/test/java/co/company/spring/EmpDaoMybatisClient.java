package co.company.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import co.company.spring.config.DBConfiguration;
import co.company.spring.config.MybatisConfiguration;
import co.company.spring.dao.EmpDAOMybatis;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { 
		DBConfiguration.class, 
		MybatisConfiguration.class } )
public class EmpDaoMybatisClient {
	
	@Autowired EmpDAOMybatis empDAO;
	
	@Test
	public void empdaotest() {
		System.out.println(empDAO.getEmpList());
	}
}
