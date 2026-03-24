package tests;
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
import driver.DManager;
import pages.LoginPage;
import utils.SafeActions;
import utils.ScreenshotUtil;


public class AllTests extends BaseTest {

	
	private static final Logger log = LogManager.getLogger(AllTests.class);
	
	
	@DataProvider(name = "loginData")
	public Object[][] getData() {
	    return new Object[][] {
	        {"user1"},
	        {"user2"}
	    };
	}
	
	
	
	@Test(dataProvider = "loginData")
	public void verifyLoginTest(String str) throws InterruptedException {
		
		ScreenshotUtil.capture(driver, "TEST SCREENSHOT NAME", ThreadContext.get("testName"));
		log.info("In the remove driver method under the driver manager", ThreadContext.get("testName"));

		System.out.println("🔥 CI TEST EXECUTED 🔥   "+str);

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
	
	@Test
	public void sampleTest() throws InterruptedException {
		System.out.println("🔥 CI TEST EXECUTED 🔥");
		
		LoginPage login=new LoginPage();
	    
		
		login.openLoginPage();
		driver.manage().window().maximize();
		SafeActions safeAct= new SafeActions();
		String userName=login.fetchUserNameLoginPage();
		String password =login.fetchPasswordLoginPage();
		
		ScreenshotUtil.capture(driver, "TEST SCREENSHOT NAME", ThreadContext.get("testName"));

		System.out.println(userName+"    "+password);
		
		login.givingUserNameInput();
		login.givingPasswordInput();
		login.clickingSubmitLoginButton();
		ScreenshotUtil.capture(driver, "TEST SCREENSHOT NAME", ThreadContext.get("testName"));

		safeAct.safeFindElement(By.xpath("//span[contains(@class,'oxd-main-menu-item--name') and text()='Admin']"));
		safeAct.safeClick(By.xpath("//span[contains(@class,'oxd-main-menu-item--name') and text()='Admin']"));
		
	}
	
	
	
	@Test
	public void verifyLoginTest1() throws InterruptedException {
		System.out.println("🔥 CI TEST EXECUTED 🔥");

		LoginPage login=new LoginPage();		
		login.openLoginPage();
		ScreenshotUtil.capture(driver, "TEST SCREENSHOT NAME", ThreadContext.get("testName"));

		String userName=login.fetchUserNameLoginPage();
		String password =login.fetchPasswordLoginPage();
		
		
		System.out.println(userName+"    "+password);
		ScreenshotUtil.capture(driver, "TEST SCREENSHOT NAME", ThreadContext.get("testName"));

		login.givingUserNameInput();
		login.givingPasswordInput();
		login.clickingSubmitLoginButton();		
		
	}
	
	@Test
	public void sampleTest1() throws InterruptedException {
		
		System.out.println("🔥 CI TEST EXECUTED 🔥");
		LoginPage login=new LoginPage();

		
		login.openLoginPage();
		driver.manage().window().maximize();
		SafeActions safeAct= new SafeActions();
		String userName=login.fetchUserNameLoginPage();
		String password =login.fetchPasswordLoginPage();
		ScreenshotUtil.capture(driver, "TEST SCREENSHOT NAME", ThreadContext.get("testName"));

		
		System.out.println(userName+"    "+password);
		
		login.givingUserNameInput();
		login.givingPasswordInput();
		login.clickingSubmitLoginButton();
		ScreenshotUtil.capture(driver, "TEST SCREENSHOT NAME", ThreadContext.get("testName"));

		safeAct.safeFindElement(By.xpath("//span[contains(@class,'oxd-main-menu-item--name') and text()='Admin']"));
		safeAct.safeClick(By.xpath("//span[contains(@class,'oxd-main-menu-item--name') and text()='Admin']"));
		
	}
	
	
	
	@Test
	public void verifyLoginTest2() throws InterruptedException {
		System.out.println("🔥 CI TEST EXECUTED 🔥");

		LoginPage login=new LoginPage();
		login.openLoginPage();
		
		String userName=login.fetchUserNameLoginPage();
		String password =login.fetchPasswordLoginPage();
		ScreenshotUtil.capture(driver, "TEST SCREENSHOT NAME", ThreadContext.get("testName"));

		
		System.out.println(userName+"    "+password);
		
		login.givingUserNameInput();
		login.givingPasswordInput();
		login.clickingSubmitLoginButton();
		
		ScreenshotUtil.capture(driver, "TEST SCREENSHOT NAME", ThreadContext.get("testName"));

	}
	
	@Test
	public void sampleTest2() throws InterruptedException {
		System.out.println("🔥 CI TEST EXECUTED 🔥");
		
		LoginPage login=new LoginPage();		
		login.openLoginPage();
		driver.manage().window().maximize();
		SafeActions safeAct= new SafeActions();
		String userName=login.fetchUserNameLoginPage();
		String password =login.fetchPasswordLoginPage();
		ScreenshotUtil.capture(driver, "TEST SCREENSHOT NAME", ThreadContext.get("testName"));

		
		System.out.println(userName+"    "+password);
		
		login.givingUserNameInput();
		login.givingPasswordInput();
		login.clickingSubmitLoginButton();
		safeAct.safeFindElement(By.xpath("//span[contains(@class,'oxd-main-menu-item--name') and text()='Admin']"));
		safeAct.safeClick(By.xpath("//span[contains(@class,'oxd-main-menu-item--name') and text()='Admin']"));
		ScreenshotUtil.capture(driver, "TEST SCREENSHOT NAME", ThreadContext.get("testName"));

	}
	
	
}


