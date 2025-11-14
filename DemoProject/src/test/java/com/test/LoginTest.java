package com.test;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginTest {
    WebDriver driver;
    LoginPage loginPage;

    @BeforeClass
    public void setup() {
        driver = new FirefoxDriver();
        driver.get("https://www.facebook.com/");
        loginPage = new LoginPage(driver);
    }

    @DataProvider(name = "loginData")
    public Object[][] getLoginData() throws IOException {
        String filePath = System.getProperty("user.dir") + "./src/test/TestDataFolder/dummy_data.xlsx";
        String sheetName = "Sheet1";
        return ExcelUtil.getExcelData(filePath, sheetName);
    }

    @Test(dataProvider = "loginData")
    public void verifyLoginWithData(String email, String password, String extraParam) {

        loginPage.enterUsername(email);
        loginPage.enterPassword(password);
       // loginPage.clickLogin();

        // Wait 2 seconds for error message
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        boolean errorDisplayed = wait
                .until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[contains(text(), 'The email')]")
                ))
                .isDisplayed();

        Assert.assertTrue(errorDisplayed, "❌ Error message not displayed for invalid login!");
    }


    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}

