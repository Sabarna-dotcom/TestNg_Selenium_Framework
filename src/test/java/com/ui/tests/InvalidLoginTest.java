package com.ui.tests;


import com.ui.pojo.User;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

@Listeners({com.ui.listeners.TestListener.class})
public class InvalidLoginTest extends TestBase{

    @Test(description = "Verify that user is not able to login with invalid credentials and get the correct error message displayed on the Login page",
            groups = {"e2e"},
            dataProviderClass = com.ui.dataprovidors.InvalidLoginDataProvider.class,
            dataProvider = "InvalidLoginTestDataProvider",
            retryAnalyzer = com.ui.listeners.MyRetryAnalyzer.class
    )
    public void invalidLoginTest(User user) {
        assertEquals(homePage.goToLoginInPage().doLoginWithInvalidCredentials(user.getEmailAddress(),user.getPassword()),user.getErrorMessage());
    }
}
