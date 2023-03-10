package com.alex.jobs.innotech.rest;

import com.alex.jobs.innotech.rest.dto.User;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/*
    READ ME:
    During this task we assume, that API methods like 'login', 'delete', 'getUser'
    have been already well tested in appropriate suites.
    We also noticed, that although methods 'delete' and 'update' claims for logged in user,
    they do not require it and there is no place for login session value.

    To build a beautified Allure report, please:
        1) install allure commandline tool
        2) run from the project root: `allure serve`
 */
@Feature("Update User API")
public class InnoTechRestTest {

    private static final String BASE_URI = "https://petstore.swagger.io/v2/user";
    private UserApiSteps api = new UserApiSteps();
    private User user;

    @BeforeTest
    public void before() {
        RestAssured.baseURI = BASE_URI; // TODO: Move to REST config
        RestAssured.filters(new AllureRestAssured());
        user = new User();
        user = api // TODO: Move to steps or test fragment
                .deleteUser(user.getUsername()) // just in case if the user wasn't deleted @afterTest
                .createUser(user)
                .login(user)
                .getUser(user.getUsername());
    }

    @Step("Update user data")
    private void updateUserData() {
        // TODO: update all user fields
        user.setEmail("newEmail@wiley.com");
        user.setPhone("+1(123)964234");
        user.setPassword("newPass");
    }

    @Test(description = "Update user data")
    public void updateUserTest() {
        updateUserData();
        User updatedUser = api
                .updateUser(user)
                .getUser(user.getUsername());
        assertEquals(updatedUser, user);
    }

    @AfterTest
    private void afterTest() {
        api.deleteUser(user.getUsername());
    }
}
