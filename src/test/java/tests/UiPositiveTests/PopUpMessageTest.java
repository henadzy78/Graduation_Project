package tests.UiPositiveTests;

import baseEntities.BaseTest;
import core.ReadProperties;
import models.User;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;

public class PopUpMessageTest extends BaseTest {

    @Test(description = "Pop up window test")
    public void PopUpMessage(){
        User user = User.builder()
                .email(ReadProperties.getUsername())
                .password(ReadProperties.getPassword())
                .build();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(user);
        DashboardPage dashboardPage = new DashboardPage(driver);

        Actions action = new Actions(driver);
        action
                .moveToElement(dashboardPage.getPopUp())
                .build()
                .perform();

        Assert.assertTrue(dashboardPage.getPopUpMessage().isDisplayed());
    }
}
