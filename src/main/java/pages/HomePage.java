package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{

    @FindBy(xpath = "//ul[@class='top-menu notmobile']//a[text()='Computers ']")
    private WebElement computersButton;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public WebElement getComputersButton(){
        return computersButton;
    }
}
