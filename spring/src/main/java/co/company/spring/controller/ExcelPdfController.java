package co.company.spring.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import co.company.spring.dao.EmpMapper;

@Controller
public class ExcelPdfController {
	@Autowired EmpMapper dao;
	
	@RequestMapping("/deptExcelView")
	public ModelAndView excelView(HttpServletResponse response) throws IOException {
		List<Map<String, Object>> list = dao.getStatDept();
		HashMap<String, Object> map = new HashMap<String, Object>();
//		String[] header = { "departmentId", "departmentName", "managerId" };
//		map.put("headers", header);
		map.put("filename", "excel_dept");
		map.put("datas", list);
		return new ModelAndView("commonExcelView", map);
	}
}
