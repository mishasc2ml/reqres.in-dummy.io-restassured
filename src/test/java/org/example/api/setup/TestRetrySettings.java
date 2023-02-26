package org.example.api.setup;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class TestRetrySettings implements IRetryAnalyzer {

    private int currentRetryCounter = 0;
    private final int maxRetryNumber = 3;

    @Override
    public boolean retry(ITestResult result) {
        if (!result.isSuccess() && currentRetryCounter < maxRetryNumber) {
            System.out.println(result.getThrowable());
            return true;
        }
        return false;
    }
}
