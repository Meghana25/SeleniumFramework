package webautomation.TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
//call this from Annotation @Test where u suspect testcase might be flaky and needs rerun
// IRetry Analyzer - To rerun the flaky failed testcases Selenium tests in Framework
public class ReTry implements IRetryAnalyzer {
    int count=0;
    int maxTry =1;
    @Override
    public boolean retry(ITestResult iTestResult) {
        if(count<maxTry)
        {
            count++;
            return true;
        }
        return false;
    }
}

