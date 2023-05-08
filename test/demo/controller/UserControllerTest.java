package demo.controller;

import demo.persistance.User;
import demo.service.UserRegistrationService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserControllerTest {

    @Test
    void testRegisterWhenUserCreate() {
        User user = new User();
        user.setFullName("Sanuri Senanayake");
        user.setCquEmail("s.senanayaka@@cqu.edu.au");
        user.setPassword("1234");
        user.setType("Student");
        user.setAddress("asasasas");
        user.setPhoneNumber("61455469929");
        new UserRegistrationService().userRegistration(user);
    }

    @Test
    void testRegisterWhenUserCreateFieldsNotFill() {
        User user = new User();
        user.setFullName("Sanuri Senanayake");
        user.setPassword("1234");
        user.setType("Student");
        user.setAddress("asasasas");
        user.setPhoneNumber("61455469123");
        new UserRegistrationService().userRegistration(user);
    }

    @Test
    void testRegisterWhenUserCreateWithDuplicateFiled() {
        User user = new User();
        user.setFullName("Sanuri");
        user.setCquEmail("s.senanayaka@@cqu.edu.au");
        user.setPassword("1234");
        user.setType("Student");
        user.setAddress("asasasas");
        user.setPhoneNumber("61455469129");
        new UserRegistrationService().userRegistration(user);
    }
}