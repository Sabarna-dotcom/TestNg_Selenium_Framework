package com.ui.pages;

import com.ui.tests.LoginTest;
import com.utility.BrowserUtility;
import com.utility.LoggerUtility;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderPage extends BrowserUtility {

    Logger logger = LoggerUtility.getLogger(LoginTest.class);

    private static final By GO_TO_CHECKOUT_BUTTON_SUMMARY_SECTION = By.xpath("(//a[@title='Proceed to checkout'])[2]");
    private static final By GO_TO_CHECKOUT_BUTTON_ADDRESS_SECTION = By.xpath("//button[@name='processAddress']//span[contains(text(),'Proceed to checkout')]");
    private static final By GO_TO_CHECKOUT_BUTTON_SHIPPONG_SECTION = By.xpath("//button[@name='processCarrier']//span[contains(text(),'Proceed to checkout')]");
    private static final By ADD_NEW_ADDRESS = By.xpath("//p[@class=\"address_add submit\"]/a");
    private static final By CHECKBOX_SHIPPING_SECTION = By.id("uniform-cgv");


    public OrderPage(WebDriver driver) {
        super(driver);
    }

    public OrderPage clickOnProceedToCheckoutSummary(){
        logger.info("Clicking on Proceed to checkout at summary section of order page");
        clickOn(GO_TO_CHECKOUT_BUTTON_SUMMARY_SECTION);
        logger.info("Go to checkout button at summary section of order page is clicked");
        return new OrderPage(getDriver());
    }

    public OrderPage clickOnProceedToCheckoutAddress(){
        logger.info("Clicking on Proceed to checkout at addresss section of order page");
        clickOn(GO_TO_CHECKOUT_BUTTON_ADDRESS_SECTION);
        logger.info("Go to checkout button at address section of order page is clicked");
        return new OrderPage(getDriver());
    }

    public OrderPage clickOnProceedToCheckoutShipping(){
        logger.info("Clicking on Proceed to checkout at shipping section of order page");
        clickOn(GO_TO_CHECKOUT_BUTTON_SHIPPONG_SECTION);
        logger.info("Go to checkout button at shipping section of order page is clicked");
        return new OrderPage(getDriver());
    }

    public AddressPage clickOnAddNewAddress() {
        clickOn(ADD_NEW_ADDRESS);
        logger.info("Clicked on Add new Address");
        return new AddressPage(getDriver());
    }

    public OrderPage clickOnCheckbox() {
        logger.info("Clicking on checkbox at shipping section of order page");
        clickOn(CHECKBOX_SHIPPING_SECTION);
        return new OrderPage(getDriver());
    }
}
