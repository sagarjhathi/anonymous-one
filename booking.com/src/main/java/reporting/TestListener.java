package reporting;


import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import reporting.ReportManager;
import utils.ScreenshotUtil;

public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {

        String testName = result.getMethod().getMethodName();
        ReportManager.createTest(testName);
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
        } else {
            ReportManager.getTest().skip("Test Skipped");
        }
    }
    
    @Override
    public void onFinish(ITestContext context) {
        ReportManager.flush();
    }
}
