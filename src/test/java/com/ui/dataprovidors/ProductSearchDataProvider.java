package com.ui.dataprovidors;

import com.google.gson.Gson;
import com.ui.pojo.LoginTestData;
import com.ui.pojo.ProductSearch;
import com.ui.pojo.ProductSearchTestData;
import com.ui.pojo.User;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ProductSearchDataProvider {

    @DataProvider(name="ProductSearchTestDataProvider")
    public Iterator<Object[]> productSearchDataProvider() {
        Gson gson = new Gson();
        File testDataFile = new File(System.getProperty("user.dir") + "/src/test/resources/testdata/ProductSearchData.json");

        FileReader fileReader = null;
        try {
            fileReader = new FileReader(testDataFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        ProductSearchTestData data = gson.fromJson(fileReader, ProductSearchTestData.class);

        List<Object[]> dataToReturn = new ArrayList<Object[]>();
        for(ProductSearch user : data.getData()) {
            dataToReturn.add(new Object[]{user});
        }

        return dataToReturn.iterator();
    }
}
