package org.example.api.dummy.user_service;

import org.example.api.dummy.user_classes.CreateUserRequestBody;
import org.example.api.dummy.user_classes.CreateUserResponseBody;
import org.example.api.dummy.user_classes.RecentCreatedUserListResponseBody;
import org.example.api.dummy.user_classes.User;

import java.util.List;

import static org.example.api.dummy.user_service.UserClient.createUserResponse;
import static org.example.api.dummy.user_service.UserClient.recentCreatedUserResponse;

public class UserService {

    public static CreateUserResponseBody createUser() {
        CreateUserRequestBody requestBody = new CreateUserRequestBody();
        return createUserResponse(requestBody)
                .then()
                .log().all()
                .extract().as(CreateUserResponseBody.class);
    }

    public static String getRecentCreatedUserId() {
        List<RecentCreatedUserListResponseBody> userList = recentCreatedUserResponse()
                .then()
                .extract().jsonPath().getList("data", RecentCreatedUserListResponseBody.class);
        return userList.get(userList.size() - 1).getId();
    }

    public static User getUserById(String id) {
        return UserClient.getUserById(id)
                .then()
                .log().status()
                .log().body()
                .extract().as(User.class);
    }

    public static String deleteUserById(String id) {
        return UserClient.deleteUserById(id)
                .then()
                .log().status()
                .extract().jsonPath().getString("id");
    }

    public static List<User> getUsersList() {
        return UserClient.getUsersList()
                .then()
                .log().status()
                .log().body()
                .extract().jsonPath().getList("data", User.class);
    }
}
