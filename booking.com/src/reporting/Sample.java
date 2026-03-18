package reporting;

import java.io.IOException;

import org.openqa.selenium.devtools.DevTools;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.time.Duration;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.*;
public class Sample {

	public static void main(String args[]) throws InterruptedException, IOException {

				
	

	

		        String inputUrl = "https://www.youtube.com/watch?v=bm0OyhwFDuY&list=PLsyeobzWxl7pe_IiTfNyr55kwJPWbgxB5";

		        List<String> videoUrls = processUrl(inputUrl);

		        System.out.println("\n===== FINAL VIDEO URLS =====");
		        int c=0;
		        for (String url : videoUrls) {
		            System.out.println(url);
		            c++;
		            
		            
		        }
		        System.out.println(c+"Count of url");
		        
		        
		        for(String e:videoUrls) {
		        	System.out.println(e+"   url sent to generator");
		        	//String e=videoUrls.get(i);
		        	
		        	  WebDriver d= new ChromeDriver();
				        d.get("https://notegpt.io/youtube-transcript-generator");
				        d.manage().window().maximize();
				        
				        d.findElement(By.xpath("//div[@data-slot='root']//input[@placeholder='Paste the YouTube video link, for example: https://www.youtube.com/watch?v=example']")).click();
				        
				        Thread.sleep(2000);
				      //  String url="https://www.youtube.com/watch?v=bm0OyhwFDuY&list=PLsyeobzWxl7pe_IiTfNyr55kwJPWbgxB5";

				        d.findElement(By.xpath("//div[@data-slot='root']//input[@placeholder='Paste the YouTube video link, for example: https://www.youtube.com/watch?v=example']")).sendKeys(e);

				        if(d.findElement(By.xpath("//div[@data-slot='root']//input[@placeholder='Paste the YouTube video link, for example: https://www.youtube.com/watch?v=example']")).getText().contains("Paste the YouTube video link, for example")) {
				        	System.out.println("In the if");
				        	
					        d.findElement(By.xpath("//div[@data-slot='root']//input[@placeholder='Paste the YouTube video link, for example: https://www.youtube.com/watch?v=example']")).sendKeys(e);
				        }
				        Thread.sleep(4000);
				        
				        d.findElement(By.xpath("//button[@type='submit']")).click();
				        Thread.sleep(5000);
				        
				       List<WebElement> ele= d.findElements(By.xpath("//div[contains(@id,'youTube_transcript_item')]//div[@class='overflow-hidden text-ellipsis leading-relaxed line-clamp-4 max-h-[6.8em]']"));
				       
				       for(int j=0;j<ele.size();j++) {
				    	   
				    	   System.out.println(ele.get(j).getText());
				    	   System.out.println();
				    	   
				       }
				       d.quit();
		        }
		        
//		        WebDriver d= new ChromeDriver();
//		        d.get("https://notegpt.io/youtube-transcript-generator");
//		        d.manage().window().maximize();
//		        
//		        d.findElement(By.xpath("//div[@data-slot='root']//input[@placeholder='Paste the YouTube video link, for example: https://www.youtube.com/watch?v=example']")).click();
//		        
//		        Thread.sleep(2000);
//		        String url="https://www.youtube.com/watch?v=bm0OyhwFDuY&list=PLsyeobzWxl7pe_IiTfNyr55kwJPWbgxB5";
//
//		        d.findElement(By.xpath("//div[@data-slot='root']//input[@placeholder='Paste the YouTube video link, for example: https://www.youtube.com/watch?v=example']")).sendKeys(url);
//
//		        if(d.findElement(By.xpath("//div[@data-slot='root']//input[@placeholder='Paste the YouTube video link, for example: https://www.youtube.com/watch?v=example']")).getText().contains("Paste the YouTube video link, for example")) {
//		        	System.out.println("In the if");
//		        	
//			        d.findElement(By.xpath("//div[@data-slot='root']//input[@placeholder='Paste the YouTube video link, for example: https://www.youtube.com/watch?v=example']")).sendKeys(url);
//		        }
////		        for(int i=0;i<url.length();i++) {
////			        d.findElement(By.xpath("//div[@data-slot='root']//input[@placeholder='Paste the YouTube video link, for example: https://www.youtube.com/watch?v=example']")).sendKeys(String.valueOf(url.charAt(i)));
////			        	Thread.sleep(200);
////			        	
////		        }
//
//		        //d.findElement(By.xpath("//div[@data-slot='root']//input[@placeholder='Paste the YouTube video link, for example: https://www.youtube.com/watch?v=example']")).sendKeys("https://www.youtube.com/watch?v=bm0OyhwFDuY&list=PLsyeobzWxl7pe_IiTfNyr55kwJPWbgxB5");
//		        
//		        Thread.sleep(4000);
//		        
//		        d.findElement(By.xpath("//button[@type='submit']")).click();
//		        Thread.sleep(5000);
//		        
//		       List<WebElement> ele= d.findElements(By.xpath("//div[contains(@id,'youTube_transcript_item')]//div[@class='overflow-hidden text-ellipsis leading-relaxed line-clamp-4 max-h-[6.8em]']"));
//		       
//		       for(int i=0;i<ele.size();i++) {
//		    	   
//		    	   System.out.println(ele.get(i).getText());
//		    	   System.out.println();
//		    	   
//		       }
		        
		        
		       
		       
		    }

		   
		
	


// 🔥 MAIN PROCESSOR
public static List<String> processUrl(String url) throws InterruptedException {

    if (url.contains("list=")) {
        System.out.println("📚 Playlist detected");
        String playlistId = extractPlaylistId(url);
        return extractPlaylistVideos(playlistId);
    } else {
        System.out.println("🎥 Single video detected");
        return Collections.singletonList(normalizeVideoUrl(url));
    }
}

// 🔹 Extract Playlist ID
private static String extractPlaylistId(String url) {
    return url.split("list=")[1].split("&")[0];
}

// 🔹 Normalize Video URL
private static String normalizeVideoUrl(String url) {
    if (url.contains("youtu.be")) {
        String id = url.split("youtu.be/")[1].split("\\?")[0];
        return "https://www.youtube.com/watch?v=" + id;
    }
    return url.split("&")[0]; // remove extra params
}

// 🔥 CORE: Extract all videos from playlist
private static List<String> extractPlaylistVideos(String playlistId) throws InterruptedException {

    WebDriver driver = new ChromeDriver();
    driver.manage().window().maximize();

    String playlistUrl = "https://www.youtube.com/playlist?list=" + playlistId;
    driver.get(playlistUrl);

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    JavascriptExecutor js = (JavascriptExecutor) driver;

    // 🔥 Wait initial load
    Thread.sleep(4000);

    // 🔥 Scroll until all videos load
    long lastHeight = (long) js.executeScript("return document.documentElement.scrollHeight");

    while (true) {
        js.executeScript("window.scrollTo(0, document.documentElement.scrollHeight);");
        Thread.sleep(2000);

        long newHeight = (long) js.executeScript("return document.documentElement.scrollHeight");

        if (newHeight == lastHeight) break;
        lastHeight = newHeight;
    }

    // 🔥 Extract video URLs
    List<WebElement> videoElements = driver.findElements(By.cssSelector("a#video-title"));

    Set<String> uniqueUrls = new LinkedHashSet<>();

    for (WebElement video : videoElements) {
        String href = video.getAttribute("href");

        if (href != null && href.contains("watch")) {
            href = href.split("&")[0]; // clean params
            uniqueUrls.add(href);
        }
    }

    driver.quit();

    return new ArrayList<>(uniqueUrls);
}
}

