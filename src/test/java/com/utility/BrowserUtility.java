package com.utility;

import com.constants.Browser;
import com.ui.tests.LoginTest;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import java.time.Duration;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class BrowserUtility {

    Logger logger = LoggerUtility.getLogger(LoginTest.class);

    public static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();

    public WebDriver getDriver() {
        return driver.get();
    }

    public BrowserUtility(WebDriver driver) {
        super();
        this.driver.set(driver);
    }

    public BrowserUtility(Browser browserName) {
        logger.info("Trying to launch the browser: " + browserName);
        if (browserName == Browser.CHROME) {
            driver.set(new ChromeDriver());
        }

        else if (browserName == Browser.EDGE) {
            driver.set(new EdgeDriver());
        }

        else if (browserName == Browser.FIREFOX) {
            driver.set(new FirefoxDriver());
        }

        else {
            logger.error("Invalid Browser Name....Please select Chrome or Edge or Firefox only");
        }
    }

    public BrowserUtility(Browser browserName, boolean isHeadless) {
        logger.info("Trying to launch the browser: " + browserName);

        if (browserName == Browser.CHROME) {

            if (isHeadless) {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--headless");
                options.addArguments("--window-size=1920,1080");
                driver.set(new ChromeDriver(options));
            }
            else {
                driver.set(new ChromeDriver());
            }
        }

        else if (browserName == Browser.EDGE) {

            if (isHeadless) {
                EdgeOptions options = new EdgeOptions();
                options.addArguments("--headless");
                options.addArguments("--window-size=1920,1080");
                options.addArguments("--disable-gpu");
                driver.set(new EdgeDriver(options));
            }
            else {
                driver.set(new EdgeDriver());
            }
        }

        else if (browserName == Browser.FIREFOX) {

            if (isHeadless) {
                FirefoxOptions options = new FirefoxOptions();
                options.addArguments("-headless");
                options.addArguments("--width=1920");
                options.addArguments("--height=1080");
                driver.set(new FirefoxDriver(options));
            }
            else {
                driver.set(new FirefoxDriver());
            }
        }

        else {
            logger.error("Invalid Browser Name....Please select Chrome or Edge or Firefox only");
        }
    }


    public void goToWebsite(String url) {
        logger.info("Trying to load the website with URL: " + url);
        driver.get().get(url);
    }

    public void maximizeWindow() {
        driver.get().manage().window().maximize();
    }

    public void waitForElementToBeVisible(WebElement element, int timeoutInSeconds) {
        try {
            new WebDriverWait(driver.get(), Duration.ofSeconds(timeoutInSeconds))
                    .until(ExpectedConditions.visibilityOf(element));
        } catch (TimeoutException e) {
            logger.error("Element not visible after waiting for " + timeoutInSeconds + " seconds: " + e.getMessage());
        }
    }


    public void clickOn(By locator) {
        WebElement webElement=driver.get().findElement(locator);
        waitForElementToBeVisible(webElement, 20);
        webElement.click();
    }

    public void enterText(By locator, String text) {
        WebElement webElement=driver.get().findElement(locator);
        waitForElementToBeVisible(webElement, 20);
        webElement.sendKeys(text);
    }

    public String getText(By locator) {
        WebElement webElement=driver.get().findElement(locator);
        waitForElementToBeVisible(webElement, 20);
        return webElement.getText();
    }

    public List<String> getTexts(By locator) {
        List<WebElement> webElements = driver.get().findElements(locator);
        waitForElementToBeVisible(webElements.get(0), 20);
        List<String> texts = new ArrayList<>();
        for (WebElement element : webElements) {
            texts.add(element.getText());
        }
        return texts;
    }

    public String takeScreenshot(String name) {
        WebDriver webDriver = driver.get();

        if (webDriver == null) {
            throw new IllegalStateException(
                    "No WebDriver found for the current thread"
            );
        }

        TakesScreenshot screenshot = (TakesScreenshot) webDriver;

        // Capture screenshot once as Base64
        String base64Screenshot =
                screenshot.getScreenshotAs(OutputType.BASE64);

        // Decode Base64 and save the PNG file
        byte[] screenshotBytes =
                java.util.Base64.getDecoder().decode(base64Screenshot);

        String timestamp = new SimpleDateFormat(
                "dd-MM-yyyy_HH-mm-ss-SSS"
        ).format(new Date());

        File screenshotDirectory = new File(
                System.getProperty("user.dir"), "screenshots"
        );

        if (!screenshotDirectory.exists()
                && !screenshotDirectory.mkdirs()) {
            throw new RuntimeException(
                    "Could not create screenshots directory"
            );
        }

        File screenshotFile = new File(
                screenshotDirectory, name + "_" + timestamp + ".png"
        );

        try {
            FileUtils.writeByteArrayToFile(
                    screenshotFile, screenshotBytes
            );
        } catch (IOException e) {
            throw new RuntimeException(
                    "Failed to save screenshot", e
            );
        }

        return base64Screenshot;
    }

    public void quit() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
