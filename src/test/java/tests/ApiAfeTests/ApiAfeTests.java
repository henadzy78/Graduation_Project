package tests.ApiAfeTests;

import baseEntities.BaseApiTest;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import static utils.Endpoints.*;
import static io.restassured.RestAssured.given;

public class ApiAfeTests extends BaseApiTest {

    @Test
    public void negativeGetUserTest(){

        given()
                .pathParam("email", "@gmail.com")
                .when()
                .get(GET_USER_EMAIL)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test(dependsOnMethods = "negativeGetUserTest")
    public void negativeGetProjectTest() {

        given()
                .pathParam("project_id", "projectId")
                .when()
                .get(GET_PROJECT)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }
}
