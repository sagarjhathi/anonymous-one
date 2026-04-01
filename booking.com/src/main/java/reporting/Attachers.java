package reporting;

import java.io.File;

import com.aventstack.extentreports.MediaEntityBuilder;

import pathManager.PathManager;

public class Attachers {

	
	
	public static void attachAllLogFolders(String testName) {
		File logFolder = new File(PathManager.getLogPath(testName)!=null ? PathManager.getLogPath(testName):"Default");
	      
	      File[] allFilesLogs = logFolder.listFiles();

	      if (allFilesLogs != null && allFilesLogs.length > 0) {

	          ReportManager.getTest().info("📂 Logs:");


	          for (File log : allFilesLogs) {

	              if (log.getName().endsWith(".log")) {

	                  String relativePath =
	                          "../logs/" + testName + "/" + log.getName();

	                  ReportManager.getTest().info(
	                      "📄 <a href='" + relativePath + "'>" + log.getName() + "</a>"
	                  );
	              }
	          }
	      }

	}
	
	
	
	
	public static void attachAllImageFolders(String testName) {
		 File screenshotFolder = new File(PathManager.getScreenshotPath(testName)!=null ?PathManager.getScreenshotPath(testName): "Default");

	      File[] allFilesImages = screenshotFolder.listFiles();

	      if (allFilesImages != null && allFilesImages.length > 0) {

	          ReportManager.getTest().info("📸 Screenshots:");

	          for (File img : allFilesImages) {

	              if (img.getName().endsWith(".png")) {

	                  String relativeImgPath =
	                          "../screenshots/" + testName + "/" + img.getName();

	                  ReportManager.getTest().info(
	                      MediaEntityBuilder
	                          .createScreenCaptureFromPath(relativeImgPath)
	                          .build()
	                  );
	              }
	          }
	      }	
	}
	
	
	}

