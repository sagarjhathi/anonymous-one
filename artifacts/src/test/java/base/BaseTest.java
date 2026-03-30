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
	    		  

	      PathManager.setTestFolderPath(path);
	      
	      ThreadContext.put("logFileName", testName);
	      ThreadContext.put("logPath", logPathName);
	      ThreadContext.put("testName", testName);
	      ReportManager.createTest(testName);
	      	      
	  }
	    
	  
	  
	  
	  @AfterMethod(alwaysRun = true)
	  public void afterTest(ITestResult result) {
		  
	      	      
	      String screenshotFolderPath =
	    		    PathManager.getRunFolderPath()
	    		    + File.separator + "screenshots"
	    		    + File.separator + ThreadContext.get("testName");

	      
	      
	      String testName = ThreadContext.get("testName");

	      File logFolder = new File(PathManager.getLogPath(testName));
//
//	      File[] logFiles = logFolder.listFiles((dir, name) -> name.endsWith(".log"));
//
//	      if (logFiles != null && logFiles.length > 0) {
//
//	          ReportManager.getTest().info("📂 Logs:");
//
//	          for (File log : logFiles) {
//
//	              String relativePath =
//	                      "../logs/" + testName + "/" + log.getName();
//
//	              ReportManager.getTest().info(
//	                  "📄 <a href='" + relativePath + "'>" + log.getName() + "</a>"
//	              );
//	          }
//	      }
	      
	      
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
	      
	      
	      File screenshotFolder = new File(screenshotFolderPath);

//	      File[] images = screenshotFolder.listFiles((dir, name) -> name.endsWith(".png"));
//
//	      if (images != null && images.length > 0) {
//
//	          ReportManager.getTest().info("📸 Screenshots:");
//
//	          
//	          
//
//	          for (File img : images) {
//
//	        	  String relativeImgPath =
//	        			    "../screenshots/" + testName + "/" + img.getName();
//	              ReportManager.getTest().info(
//	                  MediaEntityBuilder
//	                      .createScreenCaptureFromPath(relativeImgPath)
//	                      .build()
//	              );
//	          }
//	      }
	      
	      
	     

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
	      
	      
	      
	      if (result.getStatus() == ITestResult.SUCCESS) {

	    	    ReportManager.getTest().pass("Test Passed");

	    	} else if (result.getStatus() == ITestResult.FAILURE) {

	    	    Object isRetry = result.getAttribute("retry");

	    	    if (isRetry != null) {
	    	        // 🔁 intermediate failure → retry happening
	    	        ReportManager.getTest().warning("Test Failed → Retrying...");
	    	    } else {
	    	        // ❌ final failure
	    	        ReportManager.getTest().fail(result.getThrowable());
	    	    }

	    	} else if (result.getStatus() == ITestResult.SKIP) {

	    	    Object isRetry = result.getAttribute("retry");

	    	    if (isRetry != null) {
	    	        // 🔁 retry attempt
	    	        ReportManager.getTest().warning("Retry Attempt");
	    	    } else {
	    	        // ⚠️ actual skip
	    	        ReportManager.getTest().skip("Test Skipped: " + result.getThrowable());
	    	    }
	    	}
	      
		  
	      
	      ThreadContext.clearAll();
	      PathManager.clearTestFolder();
	      DManager.removeDriver();
	      
	  }
	  
	  
	  
	  
	  
	  @AfterSuite
	  public void flushReport() {
		  ReportManager.flush();
	  }
}
