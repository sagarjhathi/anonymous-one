package base;


import org.openqa.selenium.WebDriver; 
import org.openqa.selenium.chrome.ChromeDriver;

import driver.DManager;



public class BasePage {

	public WebDriver driver;
	
    public BasePage(){
    	
    	driver=DManager.getDrivers();
    	
    }
    
    
}

