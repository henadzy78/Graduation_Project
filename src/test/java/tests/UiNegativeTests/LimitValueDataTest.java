package tests.UiNegativeTests;

import baseEntities.BaseTest;
import models.User;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.Randomization;

public class LimitValueDataTest extends BaseTest {

    @Test(description = "Test for input data exceeding the allowable")
    public void limitValueDataTest(){
        User user = User.builder()
                .email(Randomization.getRandomString(400))
                .password(Randomization.getRandomString(12))
                .build();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(user);

        Assert.assertTrue(loginPage.getNegativeMessage().isDisplayed());
        Assert.assertEquals(loginPage.getLimitValueMessage().getText(),
                "Field Email/User is too long (250 characters at most).");
    }
}
