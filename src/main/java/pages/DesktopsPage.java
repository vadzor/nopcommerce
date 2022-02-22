package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Random;

public class DesktopsPage extends BasePage{

    @FindBy(xpath = "//div[@class='product-item']/div[@class='picture']/a")
    private List<WebElement> desktopButtonList;

    private Random random = new Random();

    public DesktopsPage(WebDriver driver) {
        super(driver);
    }

    public List<WebElement> getDesktopButtonList(){
        return desktopButtonList;
    }

    public void clickRandomDesktopButton() {

        getDesktopButtonList().get(random.nextInt(getDesktopButtonList().size())).click();

    }

}
