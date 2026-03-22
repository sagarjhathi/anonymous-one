package main.java.base;

import java.sql.DriverManager;  
import java.sql.SQLException;
import main.java.driver.DManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {


	public WebDriver driver;
	
	@BeforeMethod(alwaysRun=true)
	public void setup() {
		
		
		DManager.setDriver();
		driver= DManager.getDrivers();
	
		
	}
	
	@AfterMethod(alwaysRun=true)
	public void tearDown() {
		
		DManager.removeDriver();
	}
}
