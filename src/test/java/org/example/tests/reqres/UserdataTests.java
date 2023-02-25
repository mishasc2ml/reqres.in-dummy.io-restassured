package org.example.tests.reqres;

import org.example.api.reqres.pojo_response.userdata_response.UserdataResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.example.api.reqres.util.JsonToStringConverter.convertJsonToString;
import static org.hamcrest.Matchers.equalTo;

public class UserdataTests {

    @Test
    public void userListJsonSchemeValidation() {
        Specifications.installSpecifications(Specifications.requestSpecification(), Specifications.responseSpecification(200));
        given()
                .when()
                .get("api/users?page=2")
                .then()
                .log().status()
                .log().body()
                .body(matchesJsonSchemaInClasspath("json_scheme/userdata_list.json"));
    }

    @Test
    public void singleUserJsonSchemeValidation() {
        Specifications.installSpecifications(Specifications.requestSpecification(), Specifications.responseSpecification(200));
        given()
                .when()
                .get("https://reqres.in/api/users/2")
                .then()
                .log().status()
                .log().body()
                .body(matchesJsonSchemaInClasspath("json_scheme/single_user.json"));
    }

    @Test
    public void singleUserTest() {
        Specifications.installSpecifications(Specifications.requestSpecification(), Specifications.responseSpecification(200));
        given()
                .when()
                .get("api/users/2")
                .then()
                .log().status()
                .log().body()
                .assertThat()
                .body("data.size()", equalTo(5));
    }

    @Test
    public void userdataListTests() {
        Specifications.installSpecifications(Specifications.requestSpecification(), Specifications.responseSpecification(200));
        List<UserdataResponse> users = given()
                .when()
                .get("api/users?page=2")
                .then()
                .log().status()
                .log().body()
                .extract().body().jsonPath().getList("data", UserdataResponse.class);
        users.forEach(x -> Assert.assertTrue(x.getEmail().matches("\\w+\\.\\w+@reqres\\.in")));
        users.forEach(x -> Assert.assertTrue(x.getAvatar().matches("https://reqres.in/img/faces/\\d+-image\\.\\w+")));
        users.forEach(x -> Assert.assertTrue(x.getAvatar().contains(x.getId().toString())));
    }

    @Test
    public void userdataEquityTests() {
        Specifications.installSpecifications(Specifications.requestSpecification(), Specifications.responseSpecification(200));
        String userDataPage1 = given()
                .when()
                .get("api/users?page=1")
                .then()
                .log().status()
                .extract().body().asString().replace(" ", "");

        String userDataPage2 = given()
                .when()
                .get("api/users?page=2")
                .then()
                .log().status()
                .extract().body().asString().replace(" ", "");

        Assert.assertEquals(userDataPage1, convertJsonToString("src/main/resources/json_testing_data/userdata_page1.json"));
        Assert.assertEquals(userDataPage2, convertJsonToString("src/main/resources/json_testing_data/userdata_page2.json"));
    }
}