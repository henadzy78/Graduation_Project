package pages;

import baseEntities.BasePage;
import models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage {
    private static String ENDPOINT = "/auth/login";
    private static final By PAGE_OPENED_IDENTIFIER = By.id("button_primary");

    protected By email_Selector = By.id("name");
    protected By password_Selector = By.id("password");
    protected By login_Selector = By.id("button_primary");
    protected By negativeMessage_Selector = By.className("error-text");
    protected By limitValueMessage_Selector = By.className("error-text");
    protected By errorMessage_Selector = By.xpath("//span[@class='error-on-top']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected void openPage() {
        driver.get(BASE_URL + ENDPOINT);
    }

    @Override
    protected boolean isPageOpened() {
        return waits.waitForVisibility(PAGE_OPENED_IDENTIFIER).isDisplayed();
    }

    public WebElement getEmailField() {
        return driver.findElement(email_Selector);
    }

    public WebElement getPasswordField() {
        return driver.findElement(password_Selector);
    }

    public WebElement getLoginButton() {
        return driver.findElement(login_Selector);
    }

    public WebElement getNegativeMessage() {return driver.findElement(negativeMessage_Selector);}

    public WebElement getLimitValueMessage() {return driver.findElement(limitValueMessage_Selector);}

    public WebElement getErrorMessage() {return driver.findElement(errorMessage_Selector);}

    public void login(User user) {
        getEmailField().sendKeys(user.getEmail());
        getPasswordField().sendKeys(user.getPassword());
        getLoginButton().click();
    }
}
