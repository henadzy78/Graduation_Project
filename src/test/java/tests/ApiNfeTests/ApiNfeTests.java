package tests.ApiNfeTests;

import baseEntities.BaseApiTest;
import com.google.gson.Gson;
import enums.ProjectType;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import models.*;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.Randomization;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static utils.Endpoints.*;

public class ApiNfeTests extends BaseApiTest {
    ProjectBuilder newProject;
    MilestoneBuilder newMilestone;
    SectionBuilder newSection;
    CaseBuilder newCase;
    ProjectBuilder projectUpd;
    int projectID;
    int milestoneID;
    int sectionID;
    int caseID;

    @Test
    public void addProjectTest() {

        newProject = ProjectBuilder.builder()
                .name(Randomization.getRandomString(8))
                .announcement(Randomization.getRandomString(18))
                .typeOfProject(ProjectType.SINGLE.getProjectType())
                .build();

        projectID = given()
                .body(newProject, ObjectMapperType.GSON)
                .log().body()
                .when()
                .post(ADD_PROJECT)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .body("announcement", is(newProject.getAnnouncement()))
                .body("suite_mode", equalTo(newProject.getTypeOfProject()))
                .extract().jsonPath().get("id");

        System.out.println("ID of project: " + projectID);
    }

    @Test(dependsOnMethods = "addProjectTest")
    public void getProjectTest() {

        Response response = given()
                .pathParam("project_id", projectID)
                .when()
                .get(GET_PROJECT)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .assertThat()
                .extract()
                .response();

        Assert.assertEquals(response.getBody().jsonPath().get("announcement"),
                newProject.getAnnouncement());
    }

    @Test(dependsOnMethods = "getProjectTest")
    public void addMilestoneTest() {

        newMilestone = MilestoneBuilder.builder()
                .name(Randomization.getRandomString(8))
                .description(Randomization.getRandomString(16))
                .build();

        milestoneID = given()
                .pathParam("project_id", projectID)
                .body(newMilestone, ObjectMapperType.GSON)
                .when()
                .post(ADD_MILESTONE)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .body("name", is(newMilestone.getName()))
                .body("description", is(newMilestone.getDescription()))
                .extract().jsonPath().get("id");
    }

    @Test(dependsOnMethods = "addMilestoneTest")
    public void getMilestoneTest() {
        Gson gson = new Gson();

        Response response = given()
                .pathParam("milestone_id", milestoneID)
                .when()
                .get(GET_MILESTONE);

        MilestoneBuilder actualMilestone = gson.fromJson(response.getBody().asString(), MilestoneBuilder.class);
        Assert.assertEquals(actualMilestone, newMilestone);
    }


    @Test(dependsOnMethods = "getMilestoneTest")
    public void addSectionTest() {

        newSection = SectionBuilder.builder()
                .name(Randomization.getRandomString(5))
                .description(Randomization.getRandomString(12))
                .build();

        sectionID = given()
                .pathParam("project_id", projectID)
                .body(newSection, ObjectMapperType.GSON)
                .log().body()
                .when()
                .post(ADD_SECTION)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .body("name", is(newSection.getName()))
                .body("description", is(newSection.getDescription()))
                .extract().jsonPath().get("id");
    }

    @Test(dependsOnMethods = "addSectionTest")
    public void addCaseTest() {

        newCase = CaseBuilder.builder()
                .title(Randomization.getRandomString(12))
                .typeID(4)
                .build();

        caseID = given()
                .pathParam("section_id", sectionID)
                .body(newCase, ObjectMapperType.GSON)
                .log().body()
                .when()
                .post(ADD_CASE)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .extract().jsonPath().get("id");
    }

    @Test(dependsOnMethods = "addCaseTest")
    public void getCaseTest() {

        given()
                .pathParam("case_id", caseID)
                .when()
                .get(GET_CASE)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .body("title", is(newCase.getTitle()));
    }

    @Test(dependsOnMethods = "getCaseTest")
    public void updateProject() {

        projectUpd = ProjectBuilder.builder()
                .name("Update Project")
                .announcement("Update Announcement")
                .isCompleted(true)
                .build();

        Response response = given()
                .pathParam("project_id", projectID)
                .body(projectUpd, ObjectMapperType.GSON)
                .when()
                .post(UPDATE_PROJECT)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .extract().response();

        Assert.assertEquals(response.getBody().jsonPath().get("name"),
                projectUpd.getName());
    }

    @Test(dependsOnMethods = "updateProject")
    public void deleteProject() {

        given()
                .pathParam("project_id", projectID)
                .when()
                .post(DELETE_PROJECT)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .extract().response();
    }
}
