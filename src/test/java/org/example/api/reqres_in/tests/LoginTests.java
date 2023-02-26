package org.example.api.reqres_in.tests;

import org.example.api.reqres_in.pojo_request.login_request.SuccessfulLoginRequest;
import org.example.api.reqres_in.pojo_request.login_request.UnSuccessfulLoginRequest;
import org.example.api.reqres_in.pojo_response.login_response.SuccessfulLoginResponse;
import org.example.api.setup.Specifications;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class LoginTests {

    @Test
    public void successfulLogin() {
        Specifications.installSpecifications(Specifications.requestSpecification("https://reqres.in/"), Specifications.responseSpecification(200));
        SuccessfulLoginRequest loginSuccessfulRequest = new SuccessfulLoginRequest("eve.holt@reqres.in", "cityslicka");
        String token = "QpwL5tke4Pnpja7X4";
        SuccessfulLoginResponse loginSuccessfulResponse = given()
                .body(loginSuccessfulRequest)
                .when()
                .post("api/login")
                .then()
                .log().status()
                .log().body()
                .extract().as(SuccessfulLoginResponse.class);
        Assert.assertEquals(loginSuccessfulResponse.getToken(), token);
    }

    @Test
    public void unSuccessfulLogin() {
        Specifications.installSpecifications(Specifications.requestSpecification("https://reqres.in/"), Specifications.responseSpecification(400));
        UnSuccessfulLoginRequest unSuccessfulLoginRequest = new UnSuccessfulLoginRequest("peter@klaven");
        String errorText = "Missing password";
        given()
                .body(unSuccessfulLoginRequest)
                .when()
                .post("api/login")
                .then()
                .log().status()
                .log().body()
                .body("error", equalTo(errorText));
    }
}
