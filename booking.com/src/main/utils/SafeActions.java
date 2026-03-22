package main.utils;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import main.base.BasePage;

public class SafeActions extends BasePage {
	
	
	WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(10));
	
	
	public WebElement safeFindElement(By locator) {
		
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		return driver.findElement(locator);
		
	}
	
	
     public List<WebElement>  safeFindElements(By locator) {
		
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
		List<WebElement> list =driver.findElements(locator);
		return list;
		
	}
	
     
     public void safeClick(By locator) {
 		
    	wait.until(ExpectedConditions.presenceOfElementLocated(locator));
 		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
 		wait.until(ExpectedConditions.elementToBeClickable(locator));
 		driver.findElement(locator).click();
 		
 	}
	
	
	

}
