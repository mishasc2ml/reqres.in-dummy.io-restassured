package org.example.api.reqres_in.tests;

import org.example.api.reqres_in.pojo_request.user_request.CreateUserRequest;
import org.example.api.reqres_in.pojo_response.user_response.CreatedUserResponse;
import org.example.api.setup.Specifications;
import org.example.api.setup.TestRetrySettings;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Clock;
import java.util.Arrays;

import static io.restassured.RestAssured.given;

public class UserTests {

    @BeforeMethod
    public void setUp(ITestContext context) {
        ITestNGMethod[] methodsList = context.getAllTestMethods();
        Arrays.stream(methodsList).forEach(x -> x.setRetryAnalyzerClass(TestRetrySettings.class));
    }

    @Test
    public void createdUserTests() {
        Specifications.installSpecifications(Specifications.requestSpecification("https://reqres.in/"), Specifications.responseSpecification(201));
        CreateUserRequest user1 = new CreateUserRequest("morpheus", "leader");
        CreatedUserResponse createdUser = given()
                .body(user1)
                .when()
                .post("api/users")
                .then()
                .log().status()
                .log().body()
                .extract().body().as(CreatedUserResponse.class);
        String timeRegex = "\\..+";
        String currentTime = Clock.systemUTC().instant().toString().replaceAll(timeRegex, "");
        Assert.assertEquals(createdUser.getCreatedAt().replaceAll(timeRegex, ""), currentTime);
    }

    @Test
    public void updatedUserTests() {
        Specifications.installSpecifications(Specifications.requestSpecification("https://reqres.in/"), Specifications.responseSpecification(200));
        CreateUserRequest user1 = new CreateUserRequest("morpheus", "zion resident");
        given()
                .body(user1)
                .when()
                .put("api/users/2")
                .then()
                .log().status()
                .log().body()
                .assertThat()
                .body("name", Matchers.equalTo("morpheus"))
                .body("job", Matchers.equalTo("zion resident"));
    }

}
