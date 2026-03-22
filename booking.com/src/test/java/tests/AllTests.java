package tests;
import java.time.Duration; 
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import utils.SafeActions;


public class AllTests extends BaseTest {

	@Test
	public void verifyLoginTest() throws InterruptedException {
		

		LoginPage login=new LoginPage();
		driver.manage().window().maximize();
		
		
		login.openLoginPage();
		
		//Thread.sleep(9000);
		SafeActions safeAct= new SafeActions();
	//safeAct.safeFindElement(By.xpath("//div[@class='orangehrm-login-branding']"));
		String userName=login.fetchUserNameLoginPage();
		String password =login.fetchPasswordLoginPage();
		
		
		System.out.println(userName+"    "+password);
		
		login.givingUserNameInput();
		login.givingPasswordInput();
		login.clickingSubmitLoginButton();
	//	Assert.assertTrue(false);
		
		
	}
	
	@Test
	public void sampleTest() throws InterruptedException {
		
		
		LoginPage login=new LoginPage();
	    //driver.manage().window().maximize();
		
		login.openLoginPage();
		driver.manage().window().maximize();
		SafeActions safeAct= new SafeActions();
	    //safeAct.safeFindElement(By.xpath("//div[@class='orangehrm-login-branding']"));
		//Thread.sleep(9000);
		String userName=login.fetchUserNameLoginPage();
		String password =login.fetchPasswordLoginPage();
		
		
		System.out.println(userName+"    "+password);
		
		login.givingUserNameInput();
		login.givingPasswordInput();
		login.clickingSubmitLoginButton();
	//	Thread.sleep(3000);
		
		safeAct.safeFindElement(By.xpath("//span[contains(@class,'oxd-main-menu-item--name') and text()='Admin']"));
		safeAct.safeClick(By.xpath("//span[contains(@class,'oxd-main-menu-item--name') and text()='Admin']"));
	//	Thread.sleep(2000);
		
	}
	
	
	
	@Test
	public void verifyLoginTest1() throws InterruptedException {
		

		LoginPage login=new LoginPage();
	//	driver.manage().window().maximize();
		
		
		login.openLoginPage();
		
		//Thread.sleep(9000);
		SafeActions safeAct= new SafeActions();
	//	safeAct.safeFindElement(By.xpath("//div[@class='orangehrm-login-branding']"));
		String userName=login.fetchUserNameLoginPage();
		String password =login.fetchPasswordLoginPage();
		
		
		System.out.println(userName+"    "+password);
		
		login.givingUserNameInput();
		login.givingPasswordInput();
		login.clickingSubmitLoginButton();
	//	Assert.assertTrue(false);
		
		
	}
	
	@Test
	public void sampleTest1() throws InterruptedException {
		
		
		LoginPage login=new LoginPage();
	//	driver.manage().window().maximize();
		
		login.openLoginPage();
		driver.manage().window().maximize();
		SafeActions safeAct= new SafeActions();
	//	safeAct.safeFindElement(By.xpath("//div[@class='orangehrm-login-branding']"));
		//Thread.sleep(9000);
		String userName=login.fetchUserNameLoginPage();
		String password =login.fetchPasswordLoginPage();
		
		
		System.out.println(userName+"    "+password);
		
		login.givingUserNameInput();
		login.givingPasswordInput();
		login.clickingSubmitLoginButton();
	//	Thread.sleep(3000);
		
		safeAct.safeFindElement(By.xpath("//span[contains(@class,'oxd-main-menu-item--name') and text()='Admin']"));
		safeAct.safeClick(By.xpath("//span[contains(@class,'oxd-main-menu-item--name') and text()='Admin']"));
	//	Thread.sleep(2000);
		
	}
	
	
	
	@Test
	public void verifyLoginTest2() throws InterruptedException {
		

		LoginPage login=new LoginPage();
	//	driver.manage().window().maximize();
		
		
		login.openLoginPage();
		
		//Thread.sleep(9000);
		SafeActions safeAct= new SafeActions();
	//	safeAct.safeFindElement(By.xpath("//div[@class='orangehrm-login-branding']"));
		String userName=login.fetchUserNameLoginPage();
		String password =login.fetchPasswordLoginPage();
		
		
		System.out.println(userName+"    "+password);
		
		login.givingUserNameInput();
		login.givingPasswordInput();
		login.clickingSubmitLoginButton();
	//	Assert.assertTrue(false);
		
		
	}
	
	@Test
	public void sampleTest2() throws InterruptedException {
		
		
		LoginPage login=new LoginPage();
	//	driver.manage().window().maximize();
		
		login.openLoginPage();
		driver.manage().window().maximize();
		SafeActions safeAct= new SafeActions();
	//	safeAct.safeFindElement(By.xpath("//div[@class='orangehrm-login-branding']"));
		//Thread.sleep(9000);
		String userName=login.fetchUserNameLoginPage();
		String password =login.fetchPasswordLoginPage();
		
		
		System.out.println(userName+"    "+password);
		
		login.givingUserNameInput();
		login.givingPasswordInput();
		login.clickingSubmitLoginButton();
	//	Thread.sleep(3000);
		
		safeAct.safeFindElement(By.xpath("//span[contains(@class,'oxd-main-menu-item--name') and text()='Admin']"));
		safeAct.safeClick(By.xpath("//span[contains(@class,'oxd-main-menu-item--name') and text()='Admin']"));
	//	Thread.sleep(2000);
		
	}
	
	
}
