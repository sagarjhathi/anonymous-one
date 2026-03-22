package utils;

import base.BasePage;

public class GenericUtility extends BasePage {

	
	public void acceptAlert() {
		driver.switchTo().alert().accept();
	}
	
}
