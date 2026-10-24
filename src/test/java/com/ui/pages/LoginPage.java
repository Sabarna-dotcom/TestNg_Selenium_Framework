package com.ui.pages;

import com.ui.tests.LoginTest;
import com.utility.BrowserUtility;
import com.utility.LoggerUtility;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public final class LoginPage extends BrowserUtility {

    Logger logger = LoggerUtility.getLogger(LoginTest.class);

    private static final By EMAIL_TEXT_BOX_LOCATOR = By.id("email");
    private static final By PASSWORD_TEXT_BOX_LOCATOR = By.id("passwd");
    private static final By SUBMIT_BUTTON_LOCATOR = By.id("SubmitLogin");
    private static final By ERROR_MESSAGE_LOCATOR = By.xpath("//div[contains(@class,'alert-danger')]/ol/li");


    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public MyAccountPage doLoginWith(String emailAddress, String password) {
        logger.info("Trying to perform login with email address: " + emailAddress);
        enterText(EMAIL_TEXT_BOX_LOCATOR,emailAddress);
        enterText(PASSWORD_TEXT_BOX_LOCATOR,password);
        clickOn(SUBMIT_BUTTON_LOCATOR);
        return new MyAccountPage(getDriver());
    }

    public String doLoginWithInvalidCredentials(String emailAddress, String password) {
        logger.info("Trying to perform login with email address: " + emailAddress);
        enterText(EMAIL_TEXT_BOX_LOCATOR,emailAddress);
        enterText(PASSWORD_TEXT_BOX_LOCATOR,password);
        clickOn(SUBMIT_BUTTON_LOCATOR);
        return getText(ERROR_MESSAGE_LOCATOR);
    }
}
