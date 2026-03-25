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

	      String path = System.getProperty("user.dir")
	              + File.separator + "logs"
	              + File.separator + "run_" + timestamp;
      
	      
	      
	      ReportManager.initReport(path);
	    		

	      File folder = new File(path);
	      folder.mkdirs();

	      PathManager.setRunFolderPath(path);  // ✅ CRITICAL
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

	      new File(path).mkdirs();

	      PathManager.setTestFolderPath(path);
	      
	      ThreadContext.put("logFileName", testName + "_" + timestamp);
	      ThreadContext.put("logPath", path);
	      ThreadContext.put("testName", testName);
	      ReportManager.createTest(testName);
	      
	      String testFolderPath = PathManager.getTestFolderPath();

	      
	  }
	    
	  
	  
	  
	  @AfterMethod(alwaysRun = true)
	  public void afterTest(ITestResult result) {
		  
	      String runName = new File(PathManager.getRunFolderPath()).getName();
	      
	    
	      
	      String screenshotFolderPath =
	              System.getProperty("user.dir")
	              + File.separator + "screenshots"
	              + File.separator + runName
	              + File.separator + ThreadContext.get("testName");


	      
//	      File logFolder = new File(PathManager.getTestFolderPath());
//
//	      File[] logFiles = logFolder.listFiles((dir, name) -> name.endsWith(".log"));
//
//	      if (logFiles != null) {
//
//	          ReportManager.getTest().info("📂 Logs:");
//
//	          for (File log : logFiles) {
//	              ReportManager.getTest().info(
//	                  "📄 <a href='file:///" + log.getAbsolutePath() + "'>"
//	                  + log.getName() + "</a>"
//	              );
//	          }
//	      }
	      
	      
	      File logFolder = new File(PathManager.getTestFolderPath());

	      File[] logFiles = logFolder.listFiles((dir, name) -> name.endsWith(".log"));

	      if (logFiles != null) {

	          ReportManager.getTest().info("📂 Logs:");

	          String runNameRelative = new File(PathManager.getRunFolderPath()).getName();
	          String testName = ThreadContext.get("testName");

	          for (File log : logFiles) {

	              String relativePath =
	                      "../../logs/" + runNameRelative + "/" + testName + "/" + log.getName();

	              ReportManager.getTest().info(
	                  "📄 <a href='" + relativePath + "'>" + log.getName() + "</a>"
	              );
	          }
	      }
	      
	      
//	      File screenshotFolder = new File(screenshotFolderPath);
//
//	      File[] images = screenshotFolder.listFiles((dir, name) -> name.endsWith(".png"));
//
//	      if (images != null && images.length > 0) {
//
//	          ReportManager.getTest().info("📸 Screenshots:");
//
//	          for (File img : images) {
//
//	              ReportManager.getTest().info(
//	                  MediaEntityBuilder
//	                      .createScreenCaptureFromPath(img.getAbsolutePath())
//	                      .build()
//	              );
//	          }
//	      }
	      
	      File screenshotFolder = new File(screenshotFolderPath);

	      File[] images = screenshotFolder.listFiles((dir, name) -> name.endsWith(".png"));

	      if (images != null && images.length > 0) {

	          ReportManager.getTest().info("📸 Screenshots:");

	          String runNameRelative = new File(PathManager.getRunFolderPath()).getName();
	          String testName = ThreadContext.get("testName");

	          for (File img : images) {

	              String relativeImgPath =
	                      "../../screenshots/" + runNameRelative + "/" + testName + "/" + img.getName();

	              ReportManager.getTest().info(
	                  MediaEntityBuilder
	                      .createScreenCaptureFromPath(relativeImgPath)
	                      .build()
	              );
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
