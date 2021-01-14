package co.company.spring.common;

import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

@Component
public class PdfView extends AbstractView {
	@Autowired
	DataSource datasource;

	@Override
	// Map<String, Object> model : 여기의 model이 ExcelPdfController에서의 Model model이다.
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Connection conn = datasource.getConnection();
		String reportFile = (String) model.get("filename");
		// 파라미터는 공통
		HashMap<String, Object> map = (HashMap<String, Object>) model.get("param");

		// if filename == jasper / jrxml 인지를 구분해서 각각 처리
		// jasper
		InputStream jasperStream = getClass().getResourceAsStream(reportFile);
		JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);

		// jrxml. 소스파일 컴파일해서 저장:compileReportToFile
//		String jrxmlFile = getClass().getResource("/empmaster.jrxml").getFile();
//		String jasperFile = JasperCompileManager.compileReportToFile(jrxmlFile);

		// 이후 공통
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, map, conn);
		JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
	}
}