package utils;
import pathManager.PathManager;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.ByteArrayInputStream;
import io.qameta.allure.Allure;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.ThreadContext;

import java.io.File;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScreenshotUtil {

    public static String capture(WebDriver driver, String... names) {

        try {
//
        	String testName = ThreadContext.get("testName");

        	String screenShotPath = PathManager.getScreenshotPath(testName);

        	// create folder
        	File folder = new File(screenShotPath);
        	folder.mkdirs();

        	// unique filename
        	String fileName = testName + "_" +
        	        LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH-mm-ss-SSS")) +
        	        "_" + System.nanoTime() + ".png";

        	
        	String finalPath = screenShotPath + File.separator + fileName;

        	// capture
        	File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        	File dest = new File(finalPath);

        	Files.copy(src.toPath(), dest.toPath());

        	return finalPath;

        } catch (Exception e) {
            System.out.println("Screenshot failed: " + e.getMessage()+ThreadContext.get("testName"));
            return null;
        }
        
    }
    
    
    
    public static void captureAsBytes(WebDriver driver) {

    	  byte[] screenshot = ((TakesScreenshot) driver)
    	            .getScreenshotAs(OutputType.BYTES);

    	    Allure.addAttachment(
    	    		ThreadContext.get("testName"),
    	            "image/png",
    	            new ByteArrayInputStream(screenshot),
    	            ".png"
    	    );
       
        
    }
}
