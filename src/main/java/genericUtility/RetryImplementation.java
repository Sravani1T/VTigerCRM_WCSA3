package genericUtility;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryImplementation implements IRetryAnalyzer
{
	int maxRetries = 3;    // max retries after test script failed
	int count;

	@Override
	public boolean retry(ITestResult result) {
		if(count < maxRetries)
		{
			count++;
			return true;
		}
		return false;
	}
	

}
