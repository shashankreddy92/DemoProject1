**Selenium TestNG Data-Driven Framework (Excel DataProvider)**
This project demonstrates a data-driven testing framework using:
1) Selenium WebDriver
2) TestNG
3) Page Object Model (POM)
4) Excel (Apache POI) as a DataProvider
The example automates the Facebook login page and reads multiple sets of username/password data from an Excel file.

**Features**

1) Page Object Model (POM)
->LoginPage.java implements a clean and reusable page structure:
enterUsername()
enterPassword()
clickLogin()
2) TestNG DataProvider
Reads input data (email, password, extraParam) from Excel using @DataProvider.
3) Excel Utility (Apache POI)
ExcelUtil.java reads data dynamically from Excel without hardcoding row/column count.
4) Scalable Test Structure
Easily add more tests, pages, or excel sheets.

**Prerequisites**
Ensure you have the following installed:
1) Java 8+
2) Maven
3) TestNG
4) Selenium WebDriver
5) Apache POI
6) Chrome Browser + ChromeDriver (or any other browser/driver)

**Dependencies (Add in pom.xml)**

<dependencies>
    <dependency>
        <groupId>org.seleniumhq.selenium</groupId>
        <artifactId>selenium-java</artifactId>
        <version>4.22.0</version>
    </dependency>

    <dependency>
        <groupId>org.testng</groupId>
        <artifactId>testng</artifactId>
        <version>7.9.0</version>
        <scope>test</scope>
    </dependency>

    <dependency>
        <groupId>org.apache.poi</groupId>
        <artifactId>poi-ooxml</artifactId>
        <version>5.2.5</version>
    </dependency>
</dependencies>

**How Data-Driven Test Works**
1. Excel File (dummy_data.xlsx)
   Keep this file inside:
  /src/test/TestDataFolder/dummy_data.xlsx

2. ExcelUtil.java Reads Test Data
  a) Opens workbook
  b) Reads row/column count dynamically
  c) Converts each cell into a String
  d) Returns a 2D object array for TestNG

3. TestNG DataProvider Sends Data to Test
   @Test(dataProvider = "loginData")
public void verifyLoginWithData(String email, String password, String extraParam) {
    loginPage.enterUsername(email);
    loginPage.enterPassword(password);
    // loginPage.clickLogin();
}
Each row in Excel = one test execution.

**Running the Test**
Run using:

**Option 1: TestNG XML**
Create a testng.xml:
<!DOCTYPE suite SYSTEM "https://testng.org/testng-1.0.dtd">
<suite name="Suite">
    <test name="Login Test">
        <classes>
            <class name="com.test.LoginTest"/>
        </classes>
    </test>
</suite>
Run:
mvn test
              
**Option 2: Run Test Class Directly**
Right-click → Run LoginTest.java

**Page Object Model Example (LoginPage.java)**
public void enterUsername(String uname) {
    username.clear();
    username.sendKeys(uname);
}

public void enterPassword(String pwd) {
    password.clear();
    password.sendKeys(pwd);
}

public void clickLogin() {
    loginBtn.click();
}

**Sample Test Execution Output**
Every row in Excel triggers one test:
Test 1 → test1@gmail.com / pass123 / data1
Test 2 → test2@gmail.com / test456 / data2
...

**Notes**
Click login is disabled to avoid account lock:
// loginPage.clickLogin();

**Contributing**
Feel free to fork this repo and add:
More test scenarios
More Excel sheets
Parallel execution
Reporting (Extent, Allure)

**Support**
If this project helped you, please ⭐ star the repository!













