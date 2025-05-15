package rahulshettyacademy.resources;

import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReport {

	

	@BeforeTest
	public static ExtentReports makeReport() {
		String path = System.getProperty("user.dir") + "\\report\\reportfile.html";
		ExtentSparkReporter report = new ExtentSparkReporter(path);
		report.config().setReportName("New_Demo_Report");
		report.config().setDocumentTitle("Demo");

		ExtentReports repoter = new ExtentReports();
		repoter.attachReporter(report);
		repoter.setSystemInfo("Tester", "Nidhi Sahay");
		return repoter;

	}
}
