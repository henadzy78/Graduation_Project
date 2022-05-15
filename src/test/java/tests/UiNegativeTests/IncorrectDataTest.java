package tests.UiNegativeTests;

import baseEntities.BaseTest;
import models.User;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

public class IncorrectDataTest extends BaseTest {

    @Test(description = "Incorrect data entry test")
    public void incorrectDataTest() {
        User user = User.builder()
                .email("genabox78@gmail.com")
                .password("jhnkkljsdaga")
                .build();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(user);

        Assert.assertEquals(loginPage.getNegativeMessage().getText(),
                "Email/Login or Password is incorrect. Please try again.");

        Assert.assertTrue(loginPage.getErrorMessage().isDisplayed());
    }
}
