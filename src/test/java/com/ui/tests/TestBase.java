package com.ui.tests;

import com.constants.Browser;
import com.ui.pages.HomePage;
import com.utility.BrowserUtility;
import com.utility.LambdaTestUtility;
import com.utility.LoggerUtility;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class TestBase {

    protected HomePage homePage;
    Logger logger = LoggerUtility.getLogger(LoginTest.class);

    private boolean isLambdaTest;

    @Parameters({"browser", "isHeadless", "isLambdaTest"})
    @BeforeMethod(description = "Load the home page of the website")
    public void setup
            (
                @Optional("chrome") String browser,
                @Optional("true") boolean isHeadless,
                @Optional("false") boolean isLambdaTest,
                ITestResult result
            )
    {

        this.isLambdaTest=isLambdaTest;
        WebDriver lambdaDriver;

        System.out.println(isLambdaTest);

        if(isLambdaTest) {
            logger.info("Trying to launch the browser in LambdaTest");
            lambdaDriver = LambdaTestUtility.initializeLambdaTestSession(browser, result.getMethod().getMethodName());
            homePage = new HomePage(lambdaDriver);
        } else {
            logger.info("Trying to launch the browser in local machine");
            homePage = new HomePage(Browser.valueOf(browser.toUpperCase()), isHeadless);
        }

    }

    public BrowserUtility getInstance() {
        return homePage;
    }

    @AfterMethod(description = "Close the browser after test execution")
    public void tearDown() {
        logger.info("Closing the browser after test execution");
        if(isLambdaTest) {
            LambdaTestUtility.quitSession();
        }
        else {
            homePage.quit();
        }
    }
}
