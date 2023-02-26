package org.example.api.dummy_io.tests;

import org.example.api.dummy_io.user_classes.CreateUserRequestBody;
import org.example.api.dummy_io.user_classes.CreateUserResponseBody;
import org.example.api.dummy_io.user_classes.User;
import org.example.api.util.LogListener;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.List;

import static org.example.api.constants.Constants.*;
import static org.example.api.dummy_io.user_service.UserService.*;

@Listeners(LogListener.class)
public class UserTests {

    private final CreateUserRequestBody userRequestBody = new CreateUserRequestBody();

    @Test
    public void shouldThrowIdNotExistError() {
        Assert.assertEquals(getIdNotExistErrorMessage(), APP_ID_NOT_EXIST);
    }

    @Test
    public void shouldThrowParamsNotFoundError() {
        Assert.assertEquals(getParamsNotValidErrorMessage(), PARAMS_NOT_VALID);
    }

    @Test()
    public void shouldCreateUser() {
        CreateUserResponseBody userResponseBody = createUser();
        Assert.assertEquals(userResponseBody.getEmail(), userRequestBody.getEmail());
        Assert.assertEquals(userResponseBody.getFirstName(), userRequestBody.getFirstName());
        Assert.assertEquals(userResponseBody.getLastName(), userRequestBody.getLastName());
    }

    @Test(priority = 1)
    public void shouldNotCreateUser() {
        Assert.assertEquals(getCreateUserErrorMessage(), BODY_NOT_VALID);
    }

    @Test(priority = 2)
    public void shouldReturnUserById() {
        String id = getRecentCreatedUserId();
        User recentCreatedUser = getUserById(id);
        Assert.assertEquals(recentCreatedUser.getEmail(), userRequestBody.getEmail());
        Assert.assertEquals(recentCreatedUser.getFirstName(), userRequestBody.getFirstName());
        Assert.assertEquals(recentCreatedUser.getLastName(), userRequestBody.getLastName());
    }

    @Test(priority = 3)
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
