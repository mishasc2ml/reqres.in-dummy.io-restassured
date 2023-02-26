package org.example.api.dummy_io.user_service;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.example.api.dummy_io.user_classes.CreateUserRequestBody;

import static io.restassured.RestAssured.given;

public class UserClient {

    public static Response createUserResponse(CreateUserRequestBody userRequestBody) {
        return given()
                .header("app-id", "63fb6330f33556140d16c3f0")
                .contentType(ContentType.JSON)
                .body(userRequestBody)
                .filter(new AllureRestAssured())
                .expect().statusCode(200)
                .when()
                .post("https://dummyapi.io/data/v1/user/create");
    }

    public static Response recentCreatedUserResponse() {
        return given()
                .header("app-id", "63fb6330f33556140d16c3f0")
                .queryParam("created", "1")
                .filter(new AllureRestAssured())
                .expect().statusCode(200)
                .when()
                .get("https://dummyapi.io/data/v1/user");
    }

    public static Response getUserByIdResponse(String id) {
        return given()
                .header("app-id", "63fb6330f33556140d16c3f0")
                .pathParam("id", id)
                .filter(new AllureRestAssured())
                .expect().statusCode(200)
                .when()
                .get("https://dummyapi.io/data/v1/user/{id}");
    }

    public static Response deleteUserByIdResponse(String id) {
        return given()
                .header("app-id", "63fb6330f33556140d16c3f0")
                .pathParam("id", id)
                .filter(new AllureRestAssured())
                .expect().statusCode(200)
                .when()
                .delete("https://dummyapi.io/data/v1/user/{id}");
    }

    public static Response getUsersListResponse() {
        return given()
                .header("app-id", "63fb6330f33556140d16c3f0")
                .pathParam("limit", 20)
                .filter(new AllureRestAssured())
                .expect().statusCode(200)
                .when()
                .get("https://dummyapi.io/data/v1/user?{limit}");
    }

    public static Response getBodyNotValidErrorResponse(CreateUserRequestBody userRequestBody) {
        return given()
                .header("app-id", "63fb6330f33556140d16c3f0")
                .contentType(ContentType.JSON)
                .body(userRequestBody)
                .filter(new AllureRestAssured())
                .expect().statusCode(400)
                .when()
                .post("https://dummyapi.io/data/v1/user/create");
    }

    public static Response getIdNotExistErrorResponse() {
        return given()
                .header("app-id", "63fb6330f33556140d16c3f0312312")
                .filter(new AllureRestAssured())
                .expect().statusCode(403)
                .when()
                .get("https://dummyapi.io/data/v1/");
    }

    public static Response getParamsNotValidErrorResponse() {
        return given()
                .header("app-id", "63fb6330f33556140d16c3f0")
                .pathParam("id", 2132131)
                .filter(new AllureRestAssured())
                .expect().statusCode(400)
                .when()
                .get("https://dummyapi.io/data/v1/user/{id}");
    }
}

