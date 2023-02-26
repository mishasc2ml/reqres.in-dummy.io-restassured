package org.example.api.dummy.user_service;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.example.api.dummy.user_classes.CreateUserRequestBody;

import static io.restassured.RestAssured.given;

public class UserClient {

    public static Response createUserResponse(CreateUserRequestBody userRequestBody) {
        return given()
                .header("app-id", "63fb6330f33556140d16c3f0")
                .contentType(ContentType.JSON)
                .body(userRequestBody)
                .expect().statusCode(200)
                .when()
                .post("https://dummyapi.io/data/v1/user/create");
    }

    public static Response recentCreatedUserResponse() {
        return given()
                .header("app-id", "63fb6330f33556140d16c3f0")
                .queryParam("created", "1")
                .expect().statusCode(200)
                .when()
                .get("https://dummyapi.io/data/v1/user");
    }

    public static Response getUserById(String id) {
        return given()
                .header("app-id", "63fb6330f33556140d16c3f0")
                .pathParam("id", id)
                .expect().statusCode(200)
                .when()
                .get("https://dummyapi.io/data/v1/user/{id}");
    }

    public static Response deleteUserById(String id) {
        return given()
                .header("app-id", "63fb6330f33556140d16c3f0")
                .pathParam("id", id)
                .expect().statusCode(200)
                .when()
                .delete("https://dummyapi.io/data/v1/user/{id}");
    }

    public static Response getUsersList() {
        return given()
                .header("app-id", "63fb6330f33556140d16c3f0")
                .pathParam("limit", 20)
                .expect().statusCode(200)
                .when()
                .get("https://dummyapi.io/data/v1/user?{limit}");
    }
}
