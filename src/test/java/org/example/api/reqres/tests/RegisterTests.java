package org.example.api.reqres.tests;

import org.example.api.reqres.pojo_request.register_request.SuccessfulRegisterRequest;
import org.example.api.reqres.pojo_request.register_request.UnSuccessfulRegisterRequest;
import org.example.api.reqres.pojo_response.register_response.SuccessfulRegisterResponse;
import org.example.api.setup.Specifications;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class RegisterTests {

    @Test
    public void successfulRegister() {
        Specifications.installSpecifications(Specifications.requestSpecification("https://reqres.in/"), Specifications.responseSpecification(200));
        SuccessfulRegisterRequest successfulRegisterRequest = new SuccessfulRegisterRequest("eve.holt@reqres.in", "pistol");
        Integer id = 4;
        String token = "QpwL5tke4Pnpja7X4";
        SuccessfulRegisterResponse successfulRegisterResponse = given()
                .body(successfulRegisterRequest)
                .when()
                .post("api/register")
                .then()
                .log().status()
                .log().body()
                .extract().as(SuccessfulRegisterResponse.class);
        Assert.assertEquals(successfulRegisterResponse.getId(), id);
        Assert.assertEquals(successfulRegisterResponse.getToken(), token);
    }

    @Test
    public void unSuccessfulRegister() {
        Specifications.installSpecifications(Specifications.requestSpecification("https://reqres.in/"), Specifications.responseSpecification(400));
        UnSuccessfulRegisterRequest unSuccessfulRegisterRequest = new UnSuccessfulRegisterRequest("sydney@fife");
        String errorText = "Missing password";
        given()
                .body(unSuccessfulRegisterRequest)
                .when()
                .post("api/register")
                .then()
                .log().status()
                .log().body()
                .assertThat()
                .body("error", equalTo(errorText));
    }
}
