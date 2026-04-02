package tests;
import java.io.File;
import java.time.Duration; 

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseTest;
import dataProviderUtils.DataProviderUtil;
import driver.DManager;
import excelUtils.ExcelUtil;
import pages.LoginPage;
import utils.SafeActions;
import utils.ScreenshotUtil;
import retry.Retry;


public class AllTests extends BaseTest {

	
	private static final Logger log = LogManager.getLogger(AllTests.class);
	
	
	@DataProvider(name = "loginData")
	public Object[][] getData() {
	    return new Object[][] {
	        {"user1"},
	        {"user2"}
	    };
	}
	
	
	
	@DataProvider(name = "Data")
	public Object[][] Data() {
		String excelPath= System.getProperty("user.dir")+File.separator+"src"+File.separator+"TestData201.xlsx";

		return DataProviderUtil.getDataAsArray(excelPath, "Sheet1");
	}
	
	
	
	@Test(
		    dataProvider = "Data"
		    
		)
	public void verifyLoginTest(String str) throws InterruptedException {
		
		String excelPath= System.getProperty("user.dir")+File.separator+"src"+File.separator+"TestData201.xlsx";
				
		//ExcelUtil excel= new ExcelUtil("TestData101.xlsx");
		ExcelUtil excel= new ExcelUtil(excelPath);
		excel.setSheet("Sheet1");
		System.out.println(		excel.getPhysicalRowCount()+"    ==== EXCELLLLL");
		System.out.println(		excel.getLastRowNumCount()+"    ==== EXCELLLLL");
		System.out.println(		excel.getCellData(1, 0)+"    ==== EXCELLLLL");
		System.out.println(		excel.getLastCellColCount()+"    ==== EXCELLLLL");
		
		
		ScreenshotUtil.capture(driver, "TEST SCREENSHOT NAME");
		log.info("In the remove driver method under the driver manager", ThreadContext.get("testName"));

	//	System.out.println("🔥 CI TEST EXECUTED 🔥   "+str);

		LoginPage login=new LoginPage();
		driver.manage().window().maximize();
		
		
		login.openLoginPage();
		String userName=login.fetchUserNameLoginPage();
		String password =login.fetchPasswordLoginPage();
		ScreenshotUtil.capture(driver, "TEST SCREENSHOT NAME", ThreadContext.get("testName"));

	
		
		System.out.println(userName+"    "+password);
		
		login.givingUserNameInput();
		login.givingPasswordInput();
		ScreenshotUtil.capture(driver, "TEST SCREENSHOT NAME", ThreadContext.get("testName"));
	
		login.clickingSubmitLoginButton();
		ScreenshotUtil.capture(driver, "TEST SCREENSHOT NAME", ThreadContext.get("testName"));

	
		
	}
	
	
	@Test(
		    dataProvider = "loginData"
		    
		)
	public void sampleTest(String str) throws InterruptedException {
		System.out.println("🔥 CI TEST EXECUTED 🔥");
		log.info("In the remove driver method under the driver manager", ThreadContext.get("testName"));

		LoginPage login=new LoginPage();
	    
		login.openLoginPage();
		driver.manage().window().maximize();
		SafeActions safeAct= new SafeActions();
		String userName=login.fetchUserNameLoginPage();
		String password =login.fetchPasswordLoginPage();
		log.info("In the remove driver method under the driver manager", ThreadContext.get("testName"));

		
		ScreenshotUtil.capture(driver, "TEST SCREENSHOT NAME", ThreadContext.get("testName"));

		System.out.println(userName+"    "+password);
		
		login.givingUserNameInput();
		login.givingPasswordInput();
		login.clickingSubmitLoginButton();
		ScreenshotUtil.capture(driver, "TEST SCREENSHOT NAME", ThreadContext.get("testName"));
		log.info("In the remove driver method under the driver manager", ThreadContext.get("testName"));

		safeAct.safeFindElement(By.xpath("//span[contains(@class,'oxd-main-menu-item--name') and text()='Admin']"));
		safeAct.safeClick(By.xpath("//span[contains(@class,'oxd-main-menu-item--name') and text()='Admin']"));
		
	}
	
	
	
	@Test(
		    dataProvider = "loginData"
		    
		)
	public void verifyLoginTest1(String str) throws InterruptedException {
		System.out.println("🔥 CI TEST EXECUTED 🔥");

		LoginPage login=new LoginPage();		
		login.openLoginPage();
		Thread.sleep(1000);
		ScreenshotUtil.capture(driver, "TEST SCREENSHOT NAME", ThreadContext.get("testName"));

		String userName=login.fetchUserNameLoginPage();
		String password =login.fetchPasswordLoginPage();
		log.info("In the remove driver method under the driver manager", ThreadContext.get("testName"));

		
		System.out.println(userName+"    "+password);
		ScreenshotUtil.capture(driver, "TEST SCREENSHOT NAME", ThreadContext.get("testName"));
		
		login.givingUserNameInput();
		login.givingPasswordInput();
		login.clickingSubmitLoginButton();		
		log.info("In the remove driver method under the driver manager", ThreadContext.get("testName"));

	}
	
	@Test(
		    dataProvider = "loginData"
		    
		)
	public void sampleTest1(String str) throws InterruptedException {
		
		System.out.println("🔥 CI TEST EXECUTED 🔥");
		LoginPage login=new LoginPage();

		log.info("In the remove driver method under the driver manager", ThreadContext.get("testName"));

		login.openLoginPage();
		driver.manage().window().maximize();
		SafeActions safeAct= new SafeActions();
		String userName=login.fetchUserNameLoginPage();
		String password =login.fetchPasswordLoginPage();
		ScreenshotUtil.capture(driver, "TEST SCREENSHOT NAME", ThreadContext.get("testName"));

		log.info("In the remove driver method under the driver manager", ThreadContext.get("testName"));

		System.out.println(userName+"    "+password);
		
		login.givingUserNameInput();
		login.givingPasswordInput();
		login.clickingSubmitLoginButton();
		ScreenshotUtil.capture(driver, "TEST SCREENSHOT NAME", ThreadContext.get("testName"));

		safeAct.safeFindElement(By.xpath("//span[contains(@class,'oxd-main-menu-item--name') and text()='Admin']"));
		safeAct.safeClick(By.xpath("//span[contains(@class,'oxd-main-menu-item--name') and text()='Admin']"));
		
	}
	
	
	
	@Test(
		    dataProvider = "loginData"
		    
		)
	public void verifyLoginTest2(String str) throws InterruptedException {
		System.out.println("🔥 CI TEST EXECUTED 🔥");

		LoginPage login=new LoginPage();
		login.openLoginPage();
		log.info("In the remove driver method under the driver manager", ThreadContext.get("testName"));

		String userName=login.fetchUserNameLoginPage();
		String password =login.fetchPasswordLoginPage();
		ScreenshotUtil.capture(driver, "TEST SCREENSHOT NAME", ThreadContext.get("testName"));

		
		System.out.println(userName+"    "+password);
		
		login.givingUserNameInput();
		login.givingPasswordInput();
		login.clickingSubmitLoginButton();
		log.info("In the remove driver method under the driver manager", ThreadContext.get("testName"));

		ScreenshotUtil.capture(driver, "TEST SCREENSHOT NAME", ThreadContext.get("testName"));

	}
	
	@Test(
		    dataProvider = "loginData"
		    
		)
	public void sampleTest2(String str) throws InterruptedException {
		System.out.println("🔥 CI TEST EXECUTED 🔥");
		
		LoginPage login=new LoginPage();		
		login.openLoginPage();
		driver.manage().window().maximize();
		SafeActions safeAct= new SafeActions();
		String userName=login.fetchUserNameLoginPage();
		String password =login.fetchPasswordLoginPage();
		ScreenshotUtil.capture(driver, "TEST SCREENSHOT NAME", ThreadContext.get("testName"));
		log.info("In the remove driver method under the driver manager", ThreadContext.get("testName"));

		
		System.out.println(userName+"    "+password);
		
		login.givingUserNameInput();
		login.givingPasswordInput();
		log.info("In the remove driver method under the driver manager", ThreadContext.get("testName"));

		login.clickingSubmitLoginButton();
		safeAct.safeFindElement(By.xpath("//span[contains(@class,'oxd-main-menu-item--name') and text()='Admin']"));
		safeAct.safeClick(By.xpath("//span[contains(@class,'oxd-main-menu-item--name') and text()='Admin']"));
		ScreenshotUtil.capture(driver, "TEST SCREENSHOT NAME", ThreadContext.get("testName"));

	}
	
	
}


