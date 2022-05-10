package tests.UiNegativeTests;

import baseEntities.BaseTest;
import core.ReadProperties;
import models.User;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;

public class AnyDefectScreenShotTest extends BaseTest {

    @Test
    public void anyDefectScreenShotTest(){
        User user = User.builder()
                .email(ReadProperties.getUsername())
                .password(ReadProperties.getPassword())
                .build();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(user);
        DashboardPage dashboardPage = new DashboardPage(driver);

        Assert.assertFalse(dashboardPage.getAddProjectButton().isDisplayed());
    }
}
