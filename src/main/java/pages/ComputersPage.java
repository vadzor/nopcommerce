package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ComputersPage extends BasePage{

    @FindBy(xpath = "//a[text()=' Desktops ']")
    private WebElement desktopsButton;

    public ComputersPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getDesktopsButton() {
        return desktopsButton;
    }

}
