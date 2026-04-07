package driver;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.openqa.selenium.WebDriver; 
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DManager{

	private static final Logger log = LogManager.getLogger(DManager.class);
	
	private static ThreadLocal<WebDriver> driver= new ThreadLocal<WebDriver>();
	
	
	public static WebDriver getDrivers() {
		return driver.get();
	}
	
	public static void setDriver(){
		
		ChromeOptions options =new ChromeOptions();
		options.addArguments("--start-maximized");
		//options.addArguments("--headless=new");
		options.addArguments("--no-sandbox");
		options.addArguments("--disable-dev-shm-usage");
		WebDriver driverInstance = new ChromeDriver(options);
//		WebDriver driverInstance = null;
//		try {
//			driverInstance = new RemoteWebDriver(
//			        URI.create("http://localhost:4444").toURL(),
//			        options
//			);
//		} catch (MalformedURLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

	    driver.set(driverInstance);
	}
	
	public static void removeDriver() {
		if(driver!=null) {
			driver.get().quit();
		}
		driver.remove();
	}
	
}