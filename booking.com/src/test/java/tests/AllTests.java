package tests;
import java.time.Duration; 
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import utils.SafeActions;


public class AllTests extends BaseTest {

	
	@DataProvider(name = "loginData")
	public Object[][] getData() {
	    return new Object[][] {
	        {"user1"},
	        {"user2"}
	    };
	}
	
	
	@Test(dataProvider = "loginData")
	public void verifyLoginTest(String str) throws InterruptedException {
		
		System.out.println("🔥 CI TEST EXECUTED 🔥   "+str);

		LoginPage login=new LoginPage();
		driver.manage().window().maximize();
		
		
		login.openLoginPage();
		String userName=login.fetchUserNameLoginPage();
		String password =login.fetchPasswordLoginPage();
		
		
		System.out.println(userName+"    "+password);
		
		login.givingUserNameInput();
		login.givingPasswordInput();
		login.clickingSubmitLoginButton();
		
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
		
		
		System.out.println(userName+"    "+password);
		
		login.givingUserNameInput();
		login.givingPasswordInput();
		login.clickingSubmitLoginButton();
	
		safeAct.safeFindElement(By.xpath("//span[contains(@class,'oxd-main-menu-item--name') and text()='Admin']"));
		safeAct.safeClick(By.xpath("//span[contains(@class,'oxd-main-menu-item--name') and text()='Admin']"));
		
	}
	
	
	
	@Test
	public void verifyLoginTest1() throws InterruptedException {
		System.out.println("🔥 CI TEST EXECUTED 🔥");

		LoginPage login=new LoginPage();		
		login.openLoginPage();
		
		String userName=login.fetchUserNameLoginPage();
		String password =login.fetchPasswordLoginPage();
		
		
		System.out.println(userName+"    "+password);
		
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
		
		
		System.out.println(userName+"    "+password);
		
		login.givingUserNameInput();
		login.givingPasswordInput();
		login.clickingSubmitLoginButton();		
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
		
		
		System.out.println(userName+"    "+password);
		
		login.givingUserNameInput();
		login.givingPasswordInput();
		login.clickingSubmitLoginButton();
		
		
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
		
		
		System.out.println(userName+"    "+password);
		
		login.givingUserNameInput();
		login.givingPasswordInput();
		login.clickingSubmitLoginButton();
		safeAct.safeFindElement(By.xpath("//span[contains(@class,'oxd-main-menu-item--name') and text()='Admin']"));
		safeAct.safeClick(By.xpath("//span[contains(@class,'oxd-main-menu-item--name') and text()='Admin']"));
		
	}
	
	
}


