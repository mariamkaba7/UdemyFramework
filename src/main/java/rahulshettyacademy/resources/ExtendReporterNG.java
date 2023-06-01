package rahulshettyacademy.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtendReporterNG {
	
	public static  ExtentReports getReportObject() {
		
		String path = 	System.getProperty("use.dir")+"\\reports\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Web Automation Results");
		reporter.config().setDocumentTitle("Test Result");
		
		//ExtendReports is the main class
	ExtentReports	extend = new ExtentReports();

		extend.attachReporter(reporter);

		extend.setSystemInfo("Tester", "Mariam");
		return extend;
	}

}
