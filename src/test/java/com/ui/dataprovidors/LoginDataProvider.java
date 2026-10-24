package com.ui.dataprovidors;

import com.google.gson.Gson;
import com.ui.pojo.LoginTestData;
import com.ui.pojo.User;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LoginDataProvider {

    @DataProvider(name="LoginTestDataProvider")
    public Iterator<Object[]> loginDataProvider() {
        Gson gson = new Gson();
        File testDataFile = new File(System.getProperty("user.dir") + "/src/test/resources/testdata/LoginTestData.json");

        FileReader fileReader = null;
        try {
            fileReader = new FileReader(testDataFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        LoginTestData data = gson.fromJson(fileReader, LoginTestData.class);

        List<Object[]> dataToReturn = new ArrayList<Object[]>();
        for(User user : data.getData()) {
            dataToReturn.add(new Object[]{user});
        }

        return dataToReturn.iterator();
    }

//    @DataProvider(name = "LoginTestCSVDataProvider")
//    public Iterator<User> loginCSVDataProvider() {
//        return CSVReaderUtility.readCSVFile("loginData.csv");
//    }
//    @DataProvider(name = "LoginTestExcelDataProvider")
//    public Iterator<User> loginExcelDataProvider() {
//        return ExcelReaderUtility.readExcelFile("loginData.xlsx");
//    }
}
