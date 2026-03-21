package reporting;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Trial {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		
		 String inputUrl = "https://www.youtube.com/watch?v=bm0OyhwFDuY&list=PLsyeobzWxl7pe_IiTfNyr55kwJPWbgxB5";

		    // 🔥 Step 1: Get all video URLs
		    List<String> videoUrls = processUrl(inputUrl);

		    System.out.println("Total videos: " + videoUrls.size());

		    // 🔥 Step 2: Split list into 2 parts
//		    int mid = videoUrls.size() / 2;
//
//		    List<String> list1 = videoUrls.subList(0, mid);
//		    List<String> list2 = videoUrls.subList(mid, videoUrls.size());
//
//		    // 🔥 Step 3: Create threads
//		    Thread t1 = new Thread(() -> runProcess(list1));
//		    Thread t2 = new Thread(() -> runProcess(list2));
//
//		    // 🔥 Step 4: Start timer
//		    long start = System.currentTimeMillis();
//
//		    // 🔥 Step 5: Start threads
//		    t1.start();
//		    t2.start();
//
//		    // 🔥 Step 6: Wait for both to finish
//		    t1.join();
//		    t2.join();
		    
		    
		    int size = videoUrls.size();

		 // divide into 3 parts
		 int part = size / 3;

		 List<String> list1 = videoUrls.subList(0, part);
		 List<String> list2 = videoUrls.subList(part, 2 * part);
		 List<String> list3 = videoUrls.subList(2 * part, size);

		 // 🔥 Create threads
		 Thread t1 = new Thread(() -> runProcess(list1));
		 Thread t2 = new Thread(() -> runProcess(list2));
		 Thread t3 = new Thread(() -> runProcess(list3));

	//	 🔥 Step 4: Start timer
		    long start = System.currentTimeMillis();
		 // 🔥 Start threads
		 t1.start();
		 t2.start();
		 t3.start();

		 // 🔥 Wait for all to finish
		 t1.join();
		 t2.join();
		 t3.join();

		    // 🔥 Step 7: End timer
		    long end = System.currentTimeMillis();

		    System.out.println("\nTotal Time: " + (end - start) / 1000.0 + " seconds");
    		    
		  
	}
	
	
	
	public static void runProcess(List<String> urls) {
	    WebDriver d = new ChromeDriver();
	    d.manage().window().maximize();

	    for (String e : urls) {
	        try {
	            System.out.println(Thread.currentThread().getName() + " processing: " + e);

	            d.get("https://notegpt.io/youtube-transcript-generator");

	            Thread.sleep(3000);

	            WebElement input = d.findElement(By.xpath("//input[@placeholder]"));
	            input.clear();
	            input.sendKeys(e);

	            d.findElement(By.xpath("//button[@type='submit']")).click();
	            
	            Thread.sleep(5000);
	            
	        try {
	        //	String path=System.getProperty("user.dir")+"/Sagar/Transcripts";
	        	String path = Paths.get(System.getProperty("user.dir"), "Sagar", "Transcripts").toString();
	        	
	        	
	            List<WebElement> ele= d.findElements(By.xpath("//div[contains(@id,'youTube_transcript_item')]//div[@class='overflow-hidden text-ellipsis leading-relaxed line-clamp-4 max-h-[6.8em]']"));
			      StringBuilder sb= new StringBuilder();
			      
			       for(int j=0;j<ele.size();j++) {
			    	   
			    	   System.out.println(ele.get(j).getText());
			    	   System.out.println();
			    	   
			       }
	        }catch(Exception ee) {
	        	System.out.println("Could not get the data");
	        	}
	            	
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	    }

	    d.quit();
	}
	
	
	
	
	public static void saveTranscript(String videoId, String transcript) {
	    try {
	        String fileName = "transcript_" + videoId + ".txt";

	        Files.write(Paths.get(fileName), transcript.getBytes());

	        System.out.println("Saved: " + fileName);

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
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

	
	

public static String getTranscriptPage(String videoId) throws Exception {

    String apiUrl = "https://notegpt.io/detail?id=" + videoId + "&type=1";

    URL url = new URL(apiUrl);
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();

    conn.setRequestMethod("GET");
    conn.setRequestProperty("User-Agent", "Mozilla/5.0");

    BufferedReader in = new BufferedReader(
            new InputStreamReader(conn.getInputStream())
    );

    String inputLine;
    StringBuilder response = new StringBuilder();

    while ((inputLine = in.readLine()) != null) {
        response.append(inputLine);
    }

    in.close();

    return response.toString();
}



public static void printTranscript(String html) {

    Document doc = Jsoup.parse(html);

    // 🔍 Inspect page once → adjust selector if needed
    Elements elements = doc.select("div[id*=youTube_transcript_item]");

    for (Element e : elements) {
        System.out.println(e.text());
    }
}


public static String getTranscript(WebDriver driver, String videoId) throws InterruptedException {

    String url = "https://notegpt.io/detail?id=" + videoId + "&type=1";

    driver.get(url);

    // wait for JS to load transcript
    Thread.sleep(4000);

    List<WebElement> elements = driver.findElements(
            By.xpath("//div[contains(@id,'youTube_transcript_item')]")
    );

    StringBuilder transcript = new StringBuilder();

    for (WebElement e : elements) {
        transcript.append(e.getText()).append(" ");
    }

    return transcript.toString();
}


public static String getVideoId(String url) {

    if (url.contains("v=")) {
        return url.split("v=")[1].split("&")[0];
    }

    if (url.contains("youtu.be/")) {
        return url.split("youtu.be/")[1].split("\\?")[0];
    }

    return "";
}
}
