package api.utils;

import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager implements ITestListener{

	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	private static ThreadLocal<ExtentTest> test=new ThreadLocal<>();
	
	@Override
	public void onStart(ITestContext context) {
		
			String timeStamp = new java.text.SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
            String reportName = "TestReport-" + timeStamp + ".html";

            ExtentSparkReporter spark = new ExtentSparkReporter(".\\reports\\" + reportName);
            spark.config().setDocumentTitle("RestAssuredAutomationProject");
            spark.config().setReportName("Store API");
            
            extent = new ExtentReports();
            extent.attachReporter(spark);
            
            extent.setSystemInfo("Application", "Pet Store Users API");
//            extent.setSystemInfo("User Name", System.getProperty("user.name"));
            extent.setSystemInfo("user", "Versha");
            extent.setSystemInfo("Environment","QA");
            
	}
	@Override
	public void onTestStart(ITestResult result) {
		ExtentTest extentTest=extent.createTest(result.getName());
        extentTest.assignCategory(result.getMethod().getGroups());
        test.set(extentTest);
		
	}
	@Override
	public void onTestSuccess(ITestResult result) {
			if (test != null) {
				test.get().log(Status.PASS, "Test passed");
			}
    }
	@Override
	public void onTestFailure(ITestResult result) {
			test.get().log(Status.FAIL, "Test failed");
			
	}
	@Override
	public void onTestSkipped(ITestResult result) {
			test.get().log(Status.SKIP, "Test Skipped");
	}
	@Override
	public void onFinish(ITestContext context) {
			extent.flush();
	}
}
