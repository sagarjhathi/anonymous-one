package main.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import main.driver.DManager;

public class BasePage {

	public WebDriver driver;
	
    public BasePage(){
    	
    
    	driver=DManager.getDrivers();
    	
    }
    
    
}
