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
//            dataProvider = "LoginTestExcelDataProvider"
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
            dataProvider = "LoginTestDataProvider",
            retryAnalyzer = com.ui.listeners.MyRetryAnalyzer.class
    )
    public void loginTest(User user) {
        assertEquals(homePage.goToLoginInPage().doLoginWith(user.getEmailAddress(),user.getPassword()).getUserName(),user.getName());
    }


}
