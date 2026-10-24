package com.ui.pages;

import com.ui.tests.LoginTest;
import com.utility.BrowserUtility;
import com.utility.LoggerUtility;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public final class MyAccountPage extends BrowserUtility {
    Logger logger = LoggerUtility.getLogger(LoginTest.class);

    private static final By USER_NAME_LOCATOR = By.xpath("//a[@title=\"View my customer account\"]/span");
    private static final By SEARCH_BOX_LOCATOR = By.id("search_query_top");
    private static final By SEARCH_BUTTON_LOCATOR = By.name("submit_search");

    public MyAccountPage(WebDriver driver) {
        super(driver);
    }

    public String  getUserName() {
        logger.info("Getting the user name from My Account page");
        return getText(USER_NAME_LOCATOR);
    }

    public SearchPage searchProduct(String productName) {
        logger.info("Searching for product: " + productName);
        enterText(SEARCH_BOX_LOCATOR, productName);
        clickOn(SEARCH_BUTTON_LOCATOR);
        return new SearchPage(getDriver());
    }
}
