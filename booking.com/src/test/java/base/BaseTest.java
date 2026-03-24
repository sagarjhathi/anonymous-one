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
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class BaseTest {

	//private static ThreadLocal<String> testFolderPath = new ThreadLocal<>();

	public WebDriver driver;
	
//	@BeforeMethod(alwaysRun=true)
//	public void setup() {
//		
//		
//		DManager.setDriver();
//		driver= DManager.getDrivers();
//	
//		
//	}
//	
//	  @BeforeMethod(alwaysRun = true)
//	    public void setTestName(ITestResult result) {
//	        String testName = result.getMethod().getMethodName();
//	        ThreadContext.put("testName", testName);
//	        
//	    }
	  
	  
	  

	  
	//    private static String runFolderPath;

//	    @BeforeSuite(alwaysRun = true)
//	    public void createRunFolder() {
//
//	    	 
//
//	        // Step 1: Create timestamp
//	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
//	        String timestamp = LocalDateTime.now().format(formatter);
//
//	        // Step 2: Build folder path
//	        runFolderPath = System.getProperty("user.dir") 
//	                        + File.separator + "logs" 
//	                        + File.separator + "run_" + timestamp;
//
//	        // Step 3: Create folder
//	        File folder = new File(runFolderPath);
//
//	        if (!folder.exists()) {
//	            boolean created = folder.mkdirs();
//
//	            if (created) {
//	                System.out.println("Run folder created: " + runFolderPath);
//	            } else {
//	                System.out.println("Failed to create run folder!");
//	            }
//	        }
//	    }
	    
	  
	  @BeforeSuite(alwaysRun = true)
	  public void createRunFolder() {

	      String timestamp = LocalDateTime.now()
	          .format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));

	      String path = System.getProperty("user.dir")
	              + File.separator + "logs"
	              + File.separator + "run_" + timestamp;

	      File folder = new File(path);
	      folder.mkdirs();

	      PathManager.setRunFolderPath(path);  // ✅ CRITICAL
	  }

	    
	    
//	    @BeforeMethod(alwaysRun = true)
//	    public void createTestFolder(ITestResult result) {
//
//	        // Step 1: get test name
//	        String testName = result.getMethod().getMethodName();
//
//	        // Step 2: build path
//	        String path = getRunFolderPath() 
//	                      + File.separator + testName;
//
//	        // Step 3: create folder
//	        File folder = new File(path);
//
//	        if (!folder.exists()) {
//	            folder.mkdirs();
//	        }
//
//	        // Step 4: store in ThreadLocal
//	        
//	        testFolderPath.set(path);
//	        // 🔥 Generate timestamp
//	        String timestamp = LocalDateTime.now()
//	                .format(DateTimeFormatter.ofPattern("HH-mm-ss"));
//
//	        // 🔥 Build unique log file name
//	        String logFileName = testName + "_" + timestamp;
//	       
//
//	        // 🔥 Push into ThreadContext
//	        ThreadContext.put("logFileName", logFileName);
//	        ThreadContext.put("logPath", path);
//	        ThreadContext.put("testName", testName);
//	        
//
//	    }
	    
	    
//	    public static String getScreenshotRunPath() {
//
//	        String runName = new File(runFolderPath).getName();
//
//	        return System.getProperty("user.dir")
//	                + File.separator + "screenshots"
//	                + File.separator + runName;
//	    }
//	    
//	    
//	    public static String getScreenshotTestPath(String testName) {
//	        return getScreenshotRunPath() + File.separator + testName;
//	    }
//	    
//	    // Getter (we will use this later)
//	    public static String getRunFolderPath() {
//	        return runFolderPath;
//	    }
//	
//	    public static String getTestFolderPath() {
//	        return testFolderPath.get();
//	    }
//	    
	    
	    
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
	  }
	    
//	    @AfterMethod(alwaysRun = true)
//	    public void clearTestFolder() {
//	        testFolderPath.remove();
//	    }
//	    
//	    
//	    
//	    @AfterMethod(alwaysRun = true)
//	    public void clearContext() {
//	        ThreadContext.clearAll();
//	        testFolderPath.remove();
//	    }
//	    
//	@AfterMethod(alwaysRun=true)
//	public void tearDown() {
//		
//		DManager.removeDriver();
//	}
	  
	  @AfterMethod(alwaysRun = true)
	  public void afterTest() {
	      ThreadContext.clearAll();
	      PathManager.clearTestFolder();
	      DManager.removeDriver();
	  }
}
