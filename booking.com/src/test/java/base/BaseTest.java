package base;


import java.io.File;
import java.sql.DriverManager;   
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import driver.DManager;
import pathManager.PathManager;

import org.apache.logging.log4j.ThreadContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class BaseTest {

	public WebDriver driver;
	ExtentReports extent;
	  
	  @BeforeSuite(alwaysRun = true)
	  public void createRunFolder() {
		  extent = new ExtentReports();
	      String timestamp = LocalDateTime.now()
	          .format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));

	      String path = System.getProperty("user.dir")
	              + File.separator + "logs"
	              + File.separator + "run_" + timestamp;
	      
	      String extentPath = System.getProperty("user.dir")
	              + File.separator + "reports"
	              + File.separator + "run_" + timestamp+File.separator+"ExtentReport.html";
	      
	      ExtentSparkReporter reporter =
	    		    new ExtentSparkReporter(extentPath + File.separator + "report.html");

	    		extent.attachReporter(reporter);

	      File folder = new File(path);
	      folder.mkdirs();

	      PathManager.setRunFolderPath(path);  // ✅ CRITICAL
	  }

	    	    
	    
	  @BeforeMethod(alwaysRun = true)
	  public void beforeTest(ITestResult result) {

	      // 1. Driver
	      DManager.setDriver();
	      driver = DManager.getDrivers();

	      // 2. Test name
	      String testName = result.getMethod().getMethodName();

	      // 3. Build test folder
	      String path = PathManager.getRunFolderPath()
	              + File.separator + testName;

	      new File(path).mkdirs();

	      PathManager.setTestFolderPath(path);

	      // 4. Logging context
	      String timestamp = LocalDateTime.now()
	              .format(DateTimeFormatter.ofPattern("HH-mm-ss"));
	      
	      ThreadContext.put("logFileName", testName + "_" + timestamp);
	      ThreadContext.put("logPath", path);
	      ThreadContext.put("testName", testName);
	      ExtentTest test = extent.createTest(testName);
	      
	  }
	    

	  
	  @AfterMethod(alwaysRun = true)
	  public void afterTest() {
		  
		  
	      ThreadContext.clearAll();
	      PathManager.clearTestFolder();
	      DManager.removeDriver();
	  }
	  
	  
	  @AfterSuite
	  public void flushReport() {
		  extent.flush();
	  }
}
