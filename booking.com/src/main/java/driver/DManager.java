package driver;

import org.openqa.selenium.WebDriver; 
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DManager{

	private static ThreadLocal<WebDriver> driver= new ThreadLocal<WebDriver>();
	
	
	public static WebDriver getDrivers() {
		return driver.get();
	}
	
	public static void setDriver() {
		ChromeOptions options =new ChromeOptions();
		
		options.addArguments("--start-maximized");
		//options.addArguments("--headless=new");
		options.addArguments("--no-sandbox");
		options.addArguments("--disable-dev-shm-usage");
		WebDriver driverInstance = new ChromeDriver(options);
		driver.set(driverInstance);
	}
	
	public static void removeDriver() {
		
		if(driver!=null) {
			driver.get().quit();
		}
		driver.remove();
	}
	
}