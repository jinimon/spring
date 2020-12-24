package co.company.spring.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EmpDAOMybatis {
	@Autowired SqlSession sqlSession;
	
	// 조회
	public List<Emp> getEmpList() {
		// emp_mapper.xml의 ("namespace.실행할쿼리문 id")
		// namespace가 다르면 중복된 id 이름 사용 가능
		return sqlSession.selectList("EmpDAO.getEmpList");
	}
}
