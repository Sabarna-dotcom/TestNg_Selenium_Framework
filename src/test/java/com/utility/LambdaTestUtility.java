
package com.utility;

import io.github.cdimascio.dotenv.Dotenv;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class LambdaTestUtility {

    private static final String HUB_URL =
            "https://hub.lambdatest.com/wd/hub";

    private static final Dotenv dotenv = Dotenv.configure()
            .ignoreIfMissing()
            .load();

    private static final String LT_USERNAME =
            getEnv("LT_USERNAME");

    private static final String LT_ACCESS_KEY =
            getEnv("LT_ACCESS_KEY");

    private static final ThreadLocal<WebDriver> driverLocal =
            new ThreadLocal<>();

    private static final ThreadLocal<DesiredCapabilities> capabilitiesLocal =
            new ThreadLocal<>();

    private static String getEnv(String key) {
        String value = System.getenv(key);

        if (value == null || value.isBlank()) {
            value = dotenv.get(key);
        }

        if (value == null || value.isBlank()) {
            throw new IllegalStateException(
                    "Missing required environment variable: " + key);
        }

        return value;
    }

    public static WebDriver initializeLambdaTestSession(
            String browserName, String testName) {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", browserName);
        capabilities.setCapability("browserVersion", "127");

        Map<String, Object> ltOptions = new HashMap<>();

        ltOptions.put("user", LT_USERNAME);
        ltOptions.put("accessKey", LT_ACCESS_KEY);
        ltOptions.put("build", "Selenium 4");
        ltOptions.put("name", testName);
        ltOptions.put("platformName", "Windows 10");
        ltOptions.put("seCdp", true);
        ltOptions.put("selenium_version", "4.23.0");

        capabilities.setCapability("LT:Options", ltOptions);
        capabilitiesLocal.set(capabilities);

        try {
            WebDriver driver = new RemoteWebDriver(
                    new URL(HUB_URL),
                    capabilitiesLocal.get());

            driverLocal.set(driver);
            return driver;
        } catch (MalformedURLException e) {
            throw new RuntimeException(
                    "Invalid LambdaTest Hub URL", e);
        }
    }

    public static void quitSession() {
        WebDriver driver = driverLocal.get();

        try {
            if (driver != null) {
                driver.quit();
            }
        } finally {
            driverLocal.remove();
            capabilitiesLocal.remove();
        }
    }
}
