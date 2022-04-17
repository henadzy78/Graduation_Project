package pages;

import baseEntities.BasePage;
import models.Project;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DashboardPage extends BasePage {
    private static String ENDPOINT = "/dashboard";
    private static final By PAGE_OPENED_IDENTIFIER = By.id("sidebar-projects-add");
    private static String nameProjectButton = "//a[. = 'replace']";

    protected By addProjectButton_Selector = By.id("sidebar-projects-add");
    protected By popUp_Selector = By.className("icon-display-large");
    protected By popUpMessage = By.xpath("//*[. = 'Displays the active projects with many details.']");

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public DashboardPage(WebDriver driver, boolean openPageByUrl) {
        super(driver, openPageByUrl);
    }

    @Override
    protected void openPage() {
        driver.get(BASE_URL + ENDPOINT);
    }

    @Override
    protected boolean isPageOpened() {
        return waits.waitForVisibility(PAGE_OPENED_IDENTIFIER).isDisplayed();
    }

    public WebElement getAddProjectButton(){return driver.findElement(addProjectButton_Selector);}

    public WebElement getPopUp() {return driver.findElement(popUp_Selector);}

    public WebElement getPopUpMessage(){waits.waitForVisibility(popUpMessage);
        return driver.findElement(popUpMessage);}

    public void getNameProjectButton (Project project) {
        driver.findElement(By.xpath(nameProjectButton.replace("replace", String.valueOf(project.getName())))).click();
    }




}
