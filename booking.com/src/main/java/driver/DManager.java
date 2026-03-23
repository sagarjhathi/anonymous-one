package driver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.openqa.selenium.WebDriver; 
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DManager{

	private static final Logger log = LogManager.getLogger(DManager.class);
	
	private static ThreadLocal<WebDriver> driver= new ThreadLocal<WebDriver>();
	
	
	public static WebDriver getDrivers() {
		//log.info("In the get driver method under the driver manager", ThreadContext.get("testName"));
		return driver.get();
	}
	
	public static void setDriver() {
		
		ChromeOptions options =new ChromeOptions();
	//	log.info("In the set driver method under the driver manager", ThreadContext.get("testName"));
		options.addArguments("--start-maximized");
		//options.addArguments("--headless=new");
		options.addArguments("--no-sandbox");
		options.addArguments("--disable-dev-shm-usage");
		WebDriver driverInstance = new ChromeDriver(options);
		driver.set(driverInstance);
	}
	
	public static void removeDriver() {
		//log.info("In the remove driver method under the driver manager", ThreadContext.get("testName"));
		if(driver!=null) {
			driver.get().quit();
		}
		driver.remove();
	}
	
}