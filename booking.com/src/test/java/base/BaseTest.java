package base;


import java.io.File;
import java.sql.DriverManager;   
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import driver.DManager;
import pathManager.PathManager;
import reporting.ReportManager;

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
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class BaseTest {

	public WebDriver driver;
	  
	  @BeforeSuite(alwaysRun = true)
	  public void createRunFolder() {
		  
	      String timestamp = LocalDateTime.now()
          .format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
	      
	      String runPath= System.getProperty("user.dir")
	              + File.separator + "run_" + timestamp;
	      
	      System.out.println(runPath+"    RUN PATH HERE FOR TESTING");
	      
	      
		  PathManager.setRunFolderPath(runPath);
	      ReportManager.initReport(runPath);
	 
	      File runFolder = new File(runPath);
	      runFolder.mkdirs();
	  }

	    	    
	    
	  
	  @BeforeMethod(alwaysRun = true)
	  public void beforeTest(ITestResult result) {

	      // 1. Driver
	      DManager.setDriver();
	      driver = DManager.getDrivers();
	     

	      String baseName = result.getMethod().getMethodName();

	      String params = Arrays.toString(result.getParameters())
	              .replaceAll("[\\[\\] ]", "")
	              .replace(",", "_");

	      String timestamp = LocalDateTime.now()
	              .format(DateTimeFormatter.ofPattern("HH-mm-ss"));

	      String testName = baseName 
	              + (params.isEmpty() ? "" : "_" + params)
	              + "_" + timestamp;
	      
	      // 3. Build test folder
	      String path = PathManager.getRunFolderPath()
	              + File.separator + testName;
	    		  
	      String logPathName = PathManager.getLogPath(testName);
	    		  System.out.println(logPathName+"   here is the   logPathName");
	    		  System.out.println(testName+"   here is the   testName");
	      PathManager.setTestFolderPath(path);
	      
	      ThreadContext.put("logFileName", testName);
	      ThreadContext.put("logPath", logPathName);
	      ThreadContext.put("testName", testName);
	      	      
	  }
	    
	  
	  
	  
	  
	  @AfterMethod(alwaysRun = true)
	  public void afterTest(ITestResult result) {
		  
	      String testName = ThreadContext.get("testName");
	      System.out.println(testName+"    checking the test name being null");
	      
	      File logFolder = new File(PathManager.getLogPath(testName)!=null ? PathManager.getLogPath(testName):"Default");
	      
	      File[] allFilesLogs = logFolder.listFiles();

	      if (allFilesLogs != null && allFilesLogs.length > 0) {

	          ReportManager.getTest().info("📂 Logs:");


	          for (File log : allFilesLogs) {

	              if (log.getName().endsWith(".log")) {

	                  String relativePath =
	                          "../logs/" + testName + "/" + log.getName();

	                  ReportManager.getTest().info(
	                      "📄 <a href='" + relativePath + "'>" + log.getName() + "</a>"
	                  );
	              }
	          }
	      }
	      
	      
	      File screenshotFolder = new File(PathManager.getScreenshotPath(testName)!=null ?PathManager.getScreenshotPath(testName): "Default");

	      File[] allFilesImages = screenshotFolder.listFiles();

	      if (allFilesImages != null && allFilesImages.length > 0) {

	          ReportManager.getTest().info("📸 Screenshots:");

	          for (File img : allFilesImages) {

	              if (img.getName().endsWith(".png")) {

	                  String relativeImgPath =
	                          "../screenshots/" + testName + "/" + img.getName();

	                  ReportManager.getTest().info(
	                      MediaEntityBuilder
	                          .createScreenCaptureFromPath(relativeImgPath)
	                          .build()
	                  );
	              }
	          }
	      }
	      
	      
	      System.out.println("Looking logs in: " + PathManager.getLogPath(testName));
	      System.out.println("Looking screenshots in: " + PathManager.getScreenshotPath(testName));
	     
	      
	      ThreadContext.clearAll();
	      PathManager.clearTestFolder();
	      DManager.removeDriver();
	      
	  }
	  
	  
	  
	  
	  @AfterSuite
	  public void flushReport() {
		  ReportManager.flush();
	  }
}
