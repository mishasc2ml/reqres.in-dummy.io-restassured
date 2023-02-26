package org.example.api.dummy.tests;

import org.example.api.dummy.user_classes.CreateUserRequestBody;
import org.example.api.dummy.user_classes.CreateUserResponseBody;
import org.example.api.dummy.user_classes.User;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static org.example.api.dummy.user_service.UserService.*;

public class UserTests {

    @Test()
    public void shouldCreateUser() {
        CreateUserRequestBody userRequestBody = new CreateUserRequestBody();
        CreateUserResponseBody userResponseBody = createUser();
        Assert.assertEquals(userResponseBody.getEmail(), userRequestBody.getEmail());
        Assert.assertEquals(userResponseBody.getFirstName(), userRequestBody.getFirstName());
        Assert.assertEquals(userResponseBody.getLastName(), userRequestBody.getLastName());
    }

    @Test(priority = 1)
    public void shouldReturnUserById() {
        String id = getRecentCreatedUserId();
        getUserById(id);
    }

    @Test(priority = 2)
    public void shouldDeleteRecentUserById() {
        String id = getRecentCreatedUserId();
        Assert.assertTrue(deleteUserById(id).contains(id));
    }

    @Test
    public void shouldReturnUserList() {
        List<User> userList = getUsersList();
        userList.forEach(x -> Assert.assertTrue(x.getPicture().endsWith(".jpg")));
    }
}
