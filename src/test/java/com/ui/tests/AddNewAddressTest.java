package com.ui.tests;

import com.ui.pages.SearchPage;
import com.ui.pojo.AddressPOJO;
import com.ui.pojo.ProductSearch;
import com.ui.pojo.User;
import com.utility.FakeAddressUtility;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class AddNewAddressTest extends TestBase {

    private AddressPOJO address;

    @BeforeMethod(description = "Fake address data generation")
    public void generateFakeAddressData() {
        address = FakeAddressUtility.getFakeAddress();
    }

    @Test(description = "Verify that user is able to add a new address to their account",
            groups = {"e2e"},
            dataProviderClass = com.ui.dataprovidors.ProductSearchDataProvider.class,
            dataProvider = "ProductSearchTestDataProvider",
            retryAnalyzer = com.ui.listeners.MyRetryAnalyzer.class
    )
    public void AddNewAddress(ProductSearch data) {

        address = FakeAddressUtility.getFakeAddress();
        SearchPage searchPage = new SearchPage(homePage.getDriver());

        homePage.goToLoginInPage()
                .doLoginWith(data.getEmailAddress(),data.getPassword())
                .searchProduct(data.getSearchKeyword())
                .addProductToCart(data.getSearchKeyword());

        searchPage.goToOrderPage()
                  .clickOnProceedToCheckoutSummary()
                  .clickOnAddNewAddress()
                  .saveAddress(address)
                  .clickOnProceedToCheckoutAddress()
                  .clickOnCheckbox()
                  .clickOnProceedToCheckoutShipping();

    }
}
