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

//		    System.out.println("Total videos: " + videoUrls.size());
//
//		    // 🔥 Step 2: Split list into 2 parts
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
//
//		    // 🔥 Step 7: End timer
//		    long end = System.currentTimeMillis();
//
//		    System.out.println("\nTotal Time: " + (end - start) / 1000.0 + " seconds");
//		
		
		
//		for (String url : videoUrls) {
//
//		    String videoId = url.split("v=")[1].split("&")[0];
//
//		    String html = getTranscriptPage(videoId);
//
//		    System.out.println("Fetched for: " + videoId);
//
//		    // Later → parse transcript from HTML
//		}
		    
		    
		    for (String url : videoUrls) {

		        String videoId = url.split("v=")[1].split("&")[0];

		        String html = getTranscriptPage(videoId);

		        System.out.println("\n===== TRANSCRIPT for " + videoId + " =====");

		        printTranscript(html);
		    }
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

	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	    }

	    d.quit();
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
}
