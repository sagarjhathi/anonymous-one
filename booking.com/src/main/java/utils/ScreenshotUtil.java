package utils;
import pathManager.PathManager;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.apache.logging.log4j.ThreadContext;

import java.io.File;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScreenshotUtil {

    public static String capture(WebDriver driver, String... names) {

        try {
            // Step 1: build name
            String baseName;

            if (names == null || names.length == 0) {
                baseName = "screenshot";
            } else {
                baseName = String.join("_", names);
            }

            // Step 2: timestamp
            String timestamp = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("HH-mm-ss-SSS"));

            String fileName = baseName + "_" + timestamp + ".png";

            // Step 3: get paths
            String runName = new File(PathManager.getRunFolderPath()).getName();


            
            String testName = ThreadContext.get("testName");
            if (testName == null) {
                testName = "default";
            }

            String folderPath = System.getProperty("user.dir")
                    + File.separator + "screenshots"
                    + File.separator + runName
                    + File.separator + testName;

            // Step 4: create folder
            File folder = new File(folderPath);
            if (!folder.exists()) {
                folder.mkdirs();
            }

            // Step 5: final path
            String finalPath = folderPath + File.separator + fileName;

            // Step 6: capture
            File src = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.FILE);

            File dest = new File(finalPath);

            Files.copy(src.toPath(), dest.toPath());

            return finalPath;

        } catch (Exception e) {
            System.out.println("Screenshot failed: " + e.getMessage());
            return null;
        }
    }
}
