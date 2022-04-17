package pages;

import baseEntities.BasePage;
import enums.ProjectType;
import models.Project;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AddProjectPage extends BasePage {
    private static String ENDPOINT = "/admin/projects/add/1";
    private static final By PAGE_OPENED_IDENTIFIER = By.id("accept");
    private static String typeRadioButton_Selector = "//*[@name = 'suite_mode' and @value='replace']";

    protected By nameProject_Selector = By.id("name");
    protected By announcement_Selector = By.id("announcement");
    protected By isShowAnnouncement_Selector = By.id("show_announcement");
    protected By addProjectButton_Selector = By.id("accept");

    public AddProjectPage(WebDriver driver) {
        super(driver);
    }

    public AddProjectPage(WebDriver driver, boolean openPageByUrl) {
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

    public WebElement getNameProjectField(){return driver.findElement(nameProject_Selector);}
    public WebElement getAnnouncementField(){ return driver.findElement(announcement_Selector);}
    public WebElement getIsShowAnnouncementField(){return driver.findElement(isShowAnnouncement_Selector);}
    public WebElement getAddProjectButton(){return driver.findElement(addProjectButton_Selector);}

    public void setType(ProjectType type) {
        driver.findElement(By.xpath(typeRadioButton_Selector.replace("replace", String.valueOf(type))));
    }

    public void addProject(Project project){
        getNameProjectField().sendKeys(project.getName());
        getAnnouncementField().sendKeys(project.getAnnouncement());
        getIsShowAnnouncementField().click();
        setType(project.getTypeOfProject());
        getAddProjectButton().click();
    }
}
