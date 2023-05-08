/**
 *  This class contains test methods for testing the functionality of LoginController
 *  The class uses JUnit 5 testing framework for writing the test cases
 *  The methods test the login functionality with different input values
 *  It uses the UserLoginService class to verify the credentials and prints the result of the login test
 * */
package demo.controller;

import demo.persistance.Login;
import demo.persistance.User;
import demo.service.UserLoginService;
import javafx.util.Pair;
import org.junit.jupiter.api.Test;


class LoginControllerTest {
    @Test
    void testLoginWhenInvalidCredentials() {
        loginValidation("test@cqu.edu.au", "12345");
    }

    @Test
    void testLoginWhenValidCredentials() {
        loginValidation("done@cqu.edu.au", "1234");
    }

    private void loginValidation(String email, String password) {
        Login login = new Login();
        login.setCquEmail(email);
        login.setPassword(password);
        Pair<Boolean, User> loggedIn = new UserLoginService().onUserLogin(login);
        if (!loggedIn.getKey()) {
            System.out.println("Login Failed! Check Your Email and Password.");
        } else {
            System.out.println("Successfully login.");
        }
    }
}