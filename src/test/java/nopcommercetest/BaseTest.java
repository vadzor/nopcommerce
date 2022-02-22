package nopcommercetest;

import navigationtiming.Performance;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import pages.ComputersPage;
import pages.DesktopsPage;
import pages.HomePage;

import static io.github.bonigarcia.wdm.WebDriverManager.chromedriver;

public class BaseTest {
    private WebDriver driver;
    private String NOPCOMMERCE_URL = "https://demo.nopcommerce.com/desktops";

    @BeforeTest
    public void profileSetup(){
        chromedriver().setup();
    }

    @BeforeTest
    public void testsSetUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(NOPCOMMERCE_URL);
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }

    public WebDriver getDriver() {
        return driver;
    }

    public Performance performance() {
        return new Performance(getDriver());
    }

    public HomePage getHomePage() {
        return  new HomePage(getDriver());
    }

    public ComputersPage getComputersPage() {
        return new ComputersPage(getDriver());
    }

    public DesktopsPage getDesktopsPage() {
        return new DesktopsPage(getDriver());
    }

}
