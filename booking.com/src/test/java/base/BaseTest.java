package base;


import java.sql.DriverManager;   
import java.sql.SQLException;
import driver.DManager;

import org.apache.logging.log4j.ThreadContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {


	public WebDriver driver;
	
	@BeforeMethod(alwaysRun=true)
	public void setup() {
		
		
		DManager.setDriver();
		driver= DManager.getDrivers();
	
		
	}
	
	  @BeforeMethod(alwaysRun = true)
	    public void setTestName(ITestResult result) {
	        String testName = result.getMethod().getMethodName();
	        ThreadContext.put("testName", testName);
	    }
	
	@AfterMethod(alwaysRun=true)
	public void tearDown() {
		
		DManager.removeDriver();
	}
}
