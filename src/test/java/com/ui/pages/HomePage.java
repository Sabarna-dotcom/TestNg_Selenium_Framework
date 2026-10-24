package com.ui.pages;

import com.constants.Browser;
import static com.constants.Env.*;

import com.ui.tests.LoginTest;
import com.utility.BrowserUtility;

import static com.utility.JSONUtility.*;
import static com.utility.PropertiesUtil.*;

import com.utility.LoggerUtility;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public final class HomePage extends BrowserUtility {
    Logger logger = LoggerUtility.getLogger(LoginTest.class);

    private static final By SIGN_IN_LINK_LOCATOR=By.xpath("//a[contains(text(),\"Sign\")]");

    public HomePage(Browser browserName, boolean isHeadless) {
        super(browserName,isHeadless);
        //super(browserName);
        goToWebsite(readProperties(QA, "URL"));
    }

    public HomePage(WebDriver driver) {
        super(driver);
        goToWebsite(readJSON(QA).getUrl());
    }

    public LoginPage goToLoginInPage() {
        logger.info("Trying to perform click on Sign In page");
        clickOn(SIGN_IN_LINK_LOCATOR);
        return new LoginPage(getDriver());
    }
}
