package tests.UiPositiveTests;

import baseEntities.BaseTest;
import core.ReadProperties;
import models.Project;
import models.User;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AddProjectPage;
import pages.DashboardPage;
import pages.LoginPage;
import pages.MainProjectsPage;
import utils.Randomization;

public class DialogWindowTest extends BaseTest {
    Project createProject;
    
    public void createProject(){
        createProject = Project.builder()
                .name(Randomization.getRandomString(6))
                .announcement(Randomization.getRandomString(12))
                .typeOfProject(Randomization.getRandomType())
                .isShowAnnouncement(true)
                .build();
    }

    @Test(description = "Check dialog window test")
    public void addProjectTest(){
        User user = User.builder()
                .email(ReadProperties.getUsername())
                .password(ReadProperties.getPassword())
                .build();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(user);
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.getAddProjectButton().click();
        createProject();
        AddProjectPage addProjectPage = new AddProjectPage(driver);
        addProjectPage.addProject(createProject);
        MainProjectsPage mainProjectsPage = new MainProjectsPage(driver);
        mainProjectsPage.dialogWindowForm(createProject);

        Assert.assertTrue(mainProjectsPage.getDialogWindow().isDisplayed());
    }
}
