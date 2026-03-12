package reporting;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Sample {

	public static void main(String args[]) throws InterruptedException {
		 String customUserAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36 CustomUserAgent";

	        // 2. Create an instance of ChromeOptions
	        ChromeOptions options = new ChromeOptions();

	        // 3. Add the --user-agent argument to the options
	        options.addArguments("--user-agent=" + customUserAgent);
		WebDriver driver= new ChromeDriver(options);
		
		driver.get("https://www.booking.com/");
		driver.manage().window().maximize();
		WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@aria-label='Dismiss sign-in info.']")));
		driver.findElement(By.xpath("//button[@aria-label='Dismiss sign-in info.']")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@id='flights']")));
		driver.findElement(By.xpath("//a[@id='flights']")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@data-ui-name='search_type_oneway']")));
		driver.findElement(By.xpath("//div[@data-ui-name='search_type_oneway']")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[data-ui-name='input_location_from_segment_0']")));
		driver.findElement(By.cssSelector("button[data-ui-name='input_location_from_segment_0']")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div[aria-label='Departure airport or city'] span[class='Tags-module__text___90E7G']")));
		driver.findElement(By.cssSelector("div[aria-label='Departure airport or city'] span[class='Tags-module__text___90E7G']")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[data-ui-name='input_text_autocomplete']")));
		Thread.sleep(2000);
		
//		String str="bengal";
//		for(int i=0;i<str.length();i++) {
//			char ch=str.charAt(i);
//			String s=String.valueOf(ch);
//			driver.findElement(By.cssSelector("input[data-ui-name='input_text_autocomplete']")).sendKeys(s);
//			try {
//				Thread.sleep(2000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//		}
		
		Actions actions = new Actions(driver);

	//	actions.click(fromInput).perform();

		String city = "bangalore";

		for (char c : city.toCharArray()) {
		    actions.sendKeys(String.valueOf(c)).pause(Duration.ofMillis(200)).perform();
		}
		
	}
}
