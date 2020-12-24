package co.company.spring.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

@Configuration // 알아서 스캔해서 자동으로 읽어올수있도록
public class EmpDAOJdbcTemplate {

	final String SELECT = "SELECT * FROM EMPLOYEES";
	final String INSERT = "INSERT INTO EMPLOYEES(EMPLOYEE_ID," + " LAST_NAME, EMAIL, HIRE_DATE, JOB_ID)"
			+ " VALUES(?,?,?,SYSDATE,'IT_PROG')";

	@Autowired
	private JdbcTemplate jdbcTemplate; // 이거 사용하기 위해 아까 빈 등록해줬음

	// 목록 조회
	public List<Map<String, Object>> getListMap() {
		return jdbcTemplate.queryForList(SELECT);
	}

	// 조회
	public List<Emp> getList() {
		// query : rowMapper가 필요하다. 익명객체로 받아옴
		return jdbcTemplate.query(SELECT, new RowMapper() {

			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				Emp emp = new Emp();
				emp.setFirstName(rs.getString("first_name"));
				emp.setLastName(rs.getString("last_name"));
				emp.setEmployeeId(rs.getString("employee_id"));
				return emp;
			}
		});
	}

	// 등록
	public void insert(Emp emp) {
		// Object 객체의 배열 형태로 넣어주거나
		// 아님 그냥 , , , 해서 넣어줘도됨 ex.jdbcTemplate.update(INSERT, x, x, x);)
		Object[] param = new Object[] { emp.getEmployeeId(), emp.getLastName(), emp.getEmail() };

		jdbcTemplate.update(INSERT, param);
	}
}
