package reporting;


import org.apache.logging.log4j.ThreadContext;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import reporting.ReportManager;
import utils.ScreenshotUtil;

public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {

        ReportManager.createTest(ThreadContext.get("testName"));
    }
    

    @Override
    public void onTestSuccess(ITestResult result) {

        ReportManager.getTest().pass("Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        Object isRetry = result.getAttribute("retry");

        if (isRetry != null) {
            ReportManager.getTest().warning("Test Failed → Retrying...");
        } else {
            ReportManager.getTest().fail(result.getThrowable());

            // optional screenshot
            String path = ScreenshotUtil.capture(null); // pass driver if needed
            if (path != null) {
                ReportManager.getTest().addScreenCaptureFromPath(path);
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {

        Object isRetry = result.getAttribute("retry");

        if (isRetry != null) {
            ReportManager.getTest().warning("Retry Attempt");
            ReportManager.getTest().warning(result.getThrowable());
        } else {
            ReportManager.getTest().skip("Test Skipped");
            ReportManager.getTest().fail(result.getThrowable());
        }
    }
    
    @Override
    public void onFinish(ITestContext context) {
        ReportManager.flush();
    }
}
