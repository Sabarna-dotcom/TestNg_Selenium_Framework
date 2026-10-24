package com.ui.tests;

import com.ui.pojo.ProductSearch;
import com.ui.pojo.User;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Listeners({com.ui.listeners.TestListener.class})
public class AddToCartTest extends TestBase{

    @Test(description = "Verify that user is able to search for a product and get the correct product details displayed on the Product Search page",
            groups = {"e2e"},
            dataProviderClass = com.ui.dataprovidors.ProductSearchDataProvider.class,
            dataProvider = "ProductSearchTestDataProvider",
            retryAnalyzer = com.ui.listeners.MyRetryAnalyzer.class
    )
    public void ProductSearchTest(ProductSearch data) {

        assertEquals(
                homePage
                        .goToLoginInPage()
                        .doLoginWith(data.getEmailAddress(),data.getPassword())
                        .searchProduct(data.getSearchKeyword())
                        .addProductToCart(data.getSearchKeyword()),
                data.getAddToCartMessage()
        );

    }

}
