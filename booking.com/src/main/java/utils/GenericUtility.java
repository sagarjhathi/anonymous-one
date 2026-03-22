package main.java.utils;

import main.java.base.BasePage;

public class GenericUtility extends BasePage {

	
	public void acceptAlert() {
		driver.switchTo().alert().accept();
	}
	
}
