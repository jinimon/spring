package co.company.spring.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component	// bean 등록
public class EmpDAO {
	@Autowired DataSource ds;
	
	Connection conn;
	PreparedStatement pstmt;
	
	final String SELECT = "SELECT * FROM EMPLOYEES";
	final String INSERT = "INSERT INTO EMPLOYEES(EMPLOYEE_ID,"
			+ " LAST_NAME, EMAIL, HIRE_DATE, JOB_ID)"
			+ " VALUES(?,?,?,SYSDATE,'IT_PROG')";
	
	public void insert(Emp emp) {
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(INSERT);
			
			pstmt.setString(1, emp.getEmployeeId());
			pstmt.setString(2, emp.getLastName());
			pstmt.setString(3, emp.getEmail());
			
			pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();	// 반납
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public ArrayList<Emp> getList() {
		ArrayList<Emp> list = new ArrayList<Emp>();
		try {
			// DataSource로부터 받아올거다.
			
			// conn 초기화
			conn = ds.getConnection();
			// pstmt 초기화
			pstmt = conn.prepareStatement(SELECT);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Emp emp = new Emp();
				emp.setFirstName(rs.getString("first_name"));
				emp.setLastName(rs.getString("last_name"));
				emp.setEmployeeId(rs.getString("employee_id"));
				list.add(emp);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();	// 반납
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
}
