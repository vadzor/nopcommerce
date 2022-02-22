package nopcommercetest;

import org.testng.annotations.Test;


public class PagesTest extends BaseTest{

    private final long TIME_TO_WAIT = 30;

    @Test(priority = 1)
    public void homePageTest() {
        getHomePage().waitForPageLoadComplete(TIME_TO_WAIT);
        getHomePage().waitForPageLoadCompleteJQuery(TIME_TO_WAIT);
        getHomePage().waitVisibilityOfElement(TIME_TO_WAIT, getHomePage().getComputersButton());
        getHomePage().implicitWait(TIME_TO_WAIT);
        performance().writeToInflux("Home Page");
    }

    @Test(priority = 2)
    public void computersPageTest() {
        getHomePage().getComputersButton().click();
        getComputersPage().waitForPageLoadCompleteJQuery(TIME_TO_WAIT);
        getComputersPage().waitVisibilityOfElement(TIME_TO_WAIT, getComputersPage().getDesktopsButton());
        getComputersPage().implicitWait(TIME_TO_WAIT);
        performance().writeToInflux("Computers Page");
    }

    @Test(priority = 3)
    public void desktopsPageTest() {
        getComputersPage().getDesktopsButton().click();
        getDesktopsPage().waitForPageLoadCompleteJQuery(TIME_TO_WAIT);
        getDesktopsPage().waitVisibilityOfElement(TIME_TO_WAIT, getDesktopsPage().getDesktopButtonList().get(0));
        getDesktopsPage().implicitWait(TIME_TO_WAIT);
        performance().writeToInflux("Desktops Page");
    }

    @Test(priority = 4)
    public void randomDesktopPageTest() {
        getDesktopsPage().clickRandomDesktopButton();
        getDesktopsPage().waitForPageLoadCompleteJQuery(TIME_TO_WAIT);
        getDesktopsPage().implicitWait(TIME_TO_WAIT);
        performance().writeToInflux("Random Desktop Page");
    }

}
