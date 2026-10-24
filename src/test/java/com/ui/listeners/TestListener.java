package com.ui.listeners;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.ui.tests.LoginTest;
import com.ui.tests.TestBase;
import com.utility.BrowserUtility;
import com.utility.ExtentReporterUtility;
import com.utility.LoggerUtility;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.Arrays;

public class TestListener implements ITestListener {

    Logger logger = LoggerUtility.getLogger(LoginTest.class);

    public void onTestStart(ITestResult result) {
        logger.info(result.getMethod().getMethodName());
        logger.info(result.getMethod().getDescription());
        logger.info(Arrays.toString(result.getMethod().getGroups()));
        ExtentReporterUtility.createExtentTest(result.getMethod().getMethodName());
    }

    public void onTestSuccess(ITestResult result) {
        logger.info(result.getMethod().getMethodName() + " " + "PASSED");
        ExtentReporterUtility.getExtentTest().log(Status.PASS, result.getMethod().getMethodName() + " " + "PASSED");
    }

    public void onTestFailure(ITestResult result) {
        logger.error(result.getMethod().getMethodName() + " FAILED");
        logger.error(result.getThrowable().getMessage());

        ExtentTest extentTest = ExtentReporterUtility.getExtentTest();

        extentTest.log(
                Status.FAIL,
                result.getMethod().getMethodName() + " FAILED"
        );

        extentTest.log(Status.FAIL, result.getThrowable());

        try {
            Object testClass = result.getInstance();

            BrowserUtility browserUtility =
                    ((TestBase) testClass).getInstance();

            // Capture screenshot, save PNG, and return Base64
            String base64Screenshot = browserUtility.takeScreenshot(
                    result.getMethod().getMethodName()
            );

            // Attach the same screenshot to the Extent Report
            extentTest.addScreenCaptureFromBase64String(
                    base64Screenshot,
                    "Failure Screenshot"
            );

        } catch (Exception e) {
            logger.error("Failed to capture or attach screenshot", e);
        }
    }

    public void onTestSkipped(ITestResult result) {
        logger.warn(result.getMethod().getMethodName() + " " + "SKIPPED");
        ExtentReporterUtility.getExtentTest().log(Status.SKIP, result.getMethod().getMethodName() + " " + "SKIPPED");
    }

    public void onStart(ITestContext context) {
        logger.info("Test Suite Started");
        ExtentReporterUtility.setupExtentReporter("report.html");
    }

    public void onFinish(ITestContext context) {
        logger.info("Test Suite Completed");
        ExtentReporterUtility.flushExtentReports();
    }
}
