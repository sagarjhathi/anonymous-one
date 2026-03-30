package pages;

import org.openqa.selenium.By;  

import base.BasePage;
import utils.SafeActions;

public class LoginPage extends BasePage {

	
	
	SafeActions safeAct= new SafeActions();
	
	By userNameLoginPage=By.xpath("//div[@class='orangehrm-login-error']//p[contains(.,'Username')]");
	
	By passwordLoginPage=By.xpath("//div[@class='orangehrm-login-error']//p[contains(.,'Password')]");
	
	By usernameInputLoginPage=By.xpath("//input[@name='username']");
	
	By passwordInputLoginPage=By.xpath("//input[@name='password']");
	
	By submitLoginPage=By.xpath("//button[@type='submit' and text()=' Login ']");
	
	
	public void openLoginPage() {
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
	}
	
	public String fetchUserNameLoginPage() {
		String userName=safeAct.safeFindElement(userNameLoginPage).getText();
		String fetchUserName[]=userName.split(":");
		return fetchUserName[1].trim();
	}
	
	public String fetchPasswordLoginPage() {
		String userName=safeAct.safeFindElement(passwordLoginPage).getText();
		String fetchPassword[]=userName.split(":");
		return fetchPassword[1].trim();
	}
	
	public void givingUserNameInput() {
		safeAct.safeFindElement(usernameInputLoginPage).sendKeys(fetchUserNameLoginPage());
	}
	
	public void givingPasswordInput() {		
		safeAct.safeFindElement(passwordInputLoginPage).sendKeys(fetchPasswordLoginPage());
	}
	
	public void clickingSubmitLoginButton() {		
		safeAct.safeClick(submitLoginPage);
	}
	
	
}
	
