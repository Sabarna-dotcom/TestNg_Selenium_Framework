package com.ui.tests;


import static org.testng.Assert.*;

import com.ui.pojo.User;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({com.ui.listeners.TestListener.class})
public class LoginTest extends TestBase{

//    @Test(description = "Verify that user is able to login with valid credentials and get the correct username displayed on the My Account page",
//            groups = {"e2e"},
//            dataProviderClass = com.ui.dataprovidors.LoginDataProvider.class,
//            dataProvider = "LoginTestDataProvider"
//    )
//    public void loginTest(User user) {
//        assertEquals(homePage.goToLoginInPage().doLoginWith(user.getEmailAddress(),user.getPassword()).getUserName(),"Sabarna SARKAR");
//    }
//
//    @Test(description = "Verify that user is able to login with valid credentials and get the correct username displayed on the My Account page",
//            groups = {"e2e"},
//            dataProviderClass = com.ui.dataprovidors.LoginDataProvider.class,
//            dataProvider = "LoginTestCSVDataProvider"
//    )
//    public void loginCSVTest(User user) {
//        assertEquals(homePage.goToLoginInPage().doLoginWith(user.getEmailAddress(),user.getPassword()).getUserName(),"Sabarna SARKAR");
//    }

    @Test(description = "Verify that user is able to login with valid credentials and get the correct username displayed on the My Account page",
            groups = {"e2e"},
            dataProviderClass = com.ui.dataprovidors.LoginDataProvider.class,
            dataProvider = "LoginTestExcelDataProvider",
            retryAnalyzer = com.ui.listeners.MyRetryAnalyzer.class
    )
    public void loginExcelTest(User user) {
        assertEquals(homePage.goToLoginInPage().doLoginWith(user.getEmailAddress(),user.getPassword()).getUserName(),"Sabarna SARKAR");
    }


}
