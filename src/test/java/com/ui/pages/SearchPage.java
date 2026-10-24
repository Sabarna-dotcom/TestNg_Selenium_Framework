package com.ui.pages;

import com.ui.tests.LoginTest;
import com.utility.BrowserUtility;
import com.utility.LoggerUtility;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

public class SearchPage extends BrowserUtility {

    Logger logger = LoggerUtility.getLogger(LoginTest.class);

    private static final By SEARCH_RESULT_LOCATOR = By.xpath("//h5[@itemprop=\"name\"]");
    private static final By ADD_TO_CART_MESSAGE_LOCATOR = By.xpath("//div[contains(@class,\"layer_cart_product col-xs-12 col-md-6\")]/span[@class=\"title\"]");

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    public boolean getSearchResult(String productName) {
        logger.info("Getting search results for product: " + productName);
        List<String> productNameList;
        productNameList = getTexts(SEARCH_RESULT_LOCATOR);
        for (int i = 0; i < productNameList.size(); i++) {
            if (productNameList.get(i).toLowerCase().contains(productName.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    public String addProductToCart(String productName) {

        logger.info("Adding product to cart: " + productName);

        List<String> productNameList;
        productNameList = getTexts(SEARCH_RESULT_LOCATOR);
        int index = -1;
        for (int i = 0; i < productNameList.size(); i++) {
            if (productNameList.get(i).toLowerCase().contains(productName.toLowerCase())) {
                index = i + 1;
                break;
            }
        }

        if (index != -1) {
            By addToCartButtonLocator = By.xpath("(//a[@title=\"Add to cart\"])" + "[" + index + "]");
            clickOn(addToCartButtonLocator);
        } else {

            throw new RuntimeException("Product not found in search results: " + productName);
        }

        logger.info("Product added to cart successfully: " + productName);

        return getText(ADD_TO_CART_MESSAGE_LOCATOR);
    }

}
