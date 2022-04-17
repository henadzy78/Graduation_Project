package tests.UiPositiveTests;

import baseEntities.BaseTest;
import core.ReadProperties;
import models.Project;
import models.User;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;
import utils.Randomization;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

public class UploadFileTest extends BaseTest {
    Project createProject;

    public void createProject(){
        createProject = Project.builder()
                .name(Randomization.getRandomString(8))
                .announcement(Randomization.getRandomString(16))
                .typeOfProject(Randomization.getRandomType())
                .isShowAnnouncement(true)
                .build();
    }

    @Test
    public void uploadFileTest() throws InterruptedException, AWTException {
        User user = User.builder()
                .email(ReadProperties.getUsername())
                .password(ReadProperties.getPassword())
                .build();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(user);

        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.getAddProjectButton().click();

        AddProjectPage addProjectPage = new AddProjectPage(driver);
        createProject();
        addProjectPage.addProject(createProject);

        MainProjectsPage mainProjectsPage = new MainProjectsPage(driver);
        mainProjectsPage.getDashboardButton().click();
        dashboardPage.getNameProjectButton(createProject);
        Thread.sleep(8000);

        OverviewProjectPage overviewProjectPage = new OverviewProjectPage(driver);
        overviewProjectPage.getAddTestCasesButton().click();

        AddTestCasePage addTestCasePage = new AddTestCasePage(driver);
        addTestCasePage.getFileUploadButton().click();
        addTestCasePage.getChooseFileUploadButton().click();
        Thread.sleep(8000);

        StringSelection stringSelection = new StringSelection("C:\\Users\\Геннадий\\Desktop\\Scania\\Пульт.jpg");
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);

        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        addTestCasePage.getFileUploadAttach().isDisplayed();
        /*addTestCasePage.getAttachButton().click();
        Thread.sleep(12000);

        /*Assert.assertTrue(addTestCasePage.getFileUploadImage().isDisplayed());
        Thread.sleep(12000);

         */
    }
}



