package org.example.tests.reqres;

import org.example.api.reqres.pojo_response.list_resource_response.ListResourceResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class ResourceTests {

    @Test
    public void listResourceJsonSchemeValidation() {
        Specifications.installSpecifications(Specifications.requestSpecification(), Specifications.responseSpecification(200));
        given()
                .get("api/unknown")
                .then()
                .log().status()
                .log().body()
                .body(matchesJsonSchemaInClasspath("json_scheme/list_resource.json"));
    }

    @Test
    public void listResourceTests() {
        Specifications.installSpecifications(Specifications.requestSpecification(), Specifications.responseSpecification(200));
        List<ListResourceResponse> resourceList = given()
                .when()
                .get("api/unknown")
                .then()
                .log().status()
                .log().body()
                .extract().body().jsonPath().getList("data", ListResourceResponse.class);

        resourceList.forEach(x -> Assert.assertTrue(x.getColor().matches("#\\w{6}")));
        resourceList.forEach(x -> Assert.assertTrue(x.getPantone_value().matches("\\d{2}-\\d{4}")));

        List<Integer> years = resourceList.stream().map(ListResourceResponse::getYear).toList();
        List<Integer> sortedYears = years.stream().sorted().toList();
        Assert.assertEquals(years, sortedYears);
    }

    @Test
    public void deleteUserTest() {
        Specifications.installSpecifications(Specifications.requestSpecification(), Specifications.responseSpecification(204));
        given()
                .when()
                .delete("api/users/2")
                .then()
                .log().status();
    }
}
