package com.utility;

import com.constants.Env;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil {

    public static String readProperties(Env env, String propertyName)  {

        File propertiesFile = new File(System.getProperty("user.dir") + "/src/test/resources/config/"+env+".properties");
        FileReader fileReader = null;
        Properties properties = new Properties();

        try {
            fileReader = new FileReader(propertiesFile);
            properties.load(fileReader);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return properties.getProperty(propertyName.toUpperCase());
    }
}
