package reporting;


import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import pathManager.PathManager;

public class ReportManager {

    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    // 🔹 Initialize report (run-level)
    public static void initReport(String runPath) {    	
    	
    	 String extentPath = PathManager.getReportFilePath();

    	    new File(PathManager.getReportPath()).mkdirs(); // ensure folder

    	    ExtentSparkReporter reporter = new ExtentSparkReporter(extentPath);
    	    reporter.config().setTheme(Theme.DARK);

    	    extent = new ExtentReports();
    	    extent.attachReporter(reporter);
    }

    // 🔹 Create test (per test method)
    public static void createTest(String testName) {
        ExtentTest extentTest = extent.createTest(testName);
        test.set(extentTest);
    }

    // 🔹 Get current test (used everywhere)
    public static ExtentTest getTest() {
        return test.get();
    }

    // 🔹 Flush report (write to file)
    public static void flush() {
        extent.flush();
    }
}
