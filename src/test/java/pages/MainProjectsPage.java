package pages;

import baseEntities.BasePage;
import models.Project;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainProjectsPage extends BasePage {
    private static String ENDPOINT = "/admin/projects/overview";
    private static final By PAGE_OPENED_IDENTIFIER = By.xpath("//*[contains(text(),'Add Project')]");

    protected By messageAddProject_Selector = By.xpath("//*[text() = 'Successfully added the new project.']");
    protected By checkboxDelete_Selector = By.xpath("//strong [. = 'Yes, delete this project (cannot be undone)']/following::input");
    protected By buttonOk_Selector = By.xpath("//span[@id='ui-dialog-title-deleteDialog']/following::div/a[1]");
    protected By messageDeleteProject_Selector = By.className("message-success");
    protected By DialogWindow_Selector = By.id("deleteDialog");
    protected By dashboardButton_Selector = By.id("navigation-dashboard");

    public MainProjectsPage(WebDriver driver) {
        super(driver);
    }

    public MainProjectsPage(WebDriver driver, boolean openPageByUrl) {
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

    public WebElement getMessageAddProject() {return driver.findElement(messageAddProject_Selector);}

    public WebElement getMessageDeleteProject(){return driver.findElement(messageDeleteProject_Selector);}

    public WebElement getDialogWindow(){return driver.findElement(DialogWindow_Selector);}

    public WebElement findAnyProjectInProjects(Project project){
        return driver.findElement(By.xpath("//*[. = '"+project.getName()+"']/following::td[2]"));}

    public WebElement getCheckBoxForConfirmationDelete() {return driver.findElement(checkboxDelete_Selector);}

    public WebElement getButtonOk() {return driver.findElement(buttonOk_Selector);}

    public WebElement getDashboardButton(){return driver.findElement(dashboardButton_Selector);}

    public void deleteProject (Project project) {
        findAnyProjectInProjects(project).click();
        getCheckBoxForConfirmationDelete().click();
        getButtonOk().click();
    }

    public void dialogWindowForm(Project project){
        driver.findElement(By.xpath("//*[. = '"+project.getName()+"']/following::td[2]")).click();
    }
}
