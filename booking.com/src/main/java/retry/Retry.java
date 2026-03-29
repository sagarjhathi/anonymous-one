package retry;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer{

	private int count = 0;
    private static final int maxRetry = 2;

    @Override
    public boolean retry(ITestResult result) {
    	 Throwable cause = result.getThrowable();

         // ❌ Do NOT retry assertion failures
         if (cause instanceof AssertionError) {
             return false;
         }

         // ✅ Retry for other failures
         if (count < maxRetry) {
             count++;
             System.out.println("Retrying " + result.getName() + " attempt " + count);
             return true;
         }

         return false;
    }

}
