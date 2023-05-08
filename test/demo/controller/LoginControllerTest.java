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