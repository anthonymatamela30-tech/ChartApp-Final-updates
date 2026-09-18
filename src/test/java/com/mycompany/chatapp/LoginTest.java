/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.chatapp;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Student
 */
public class LoginTest {

    

 @Test
    public void testUsernameCorrectlyFormatted() {
        Login login = new Login("kyl_1", "Ch&&ser@ke99!", "+27648522288");
        assertEquals(true, login.checkUserName());
    }

    @Test
    public void testUsernameIncorrectlyFormatted() {
        Login login = new Login("kyle !!!!!!!", "Ch&&ser@ke99!", "+27648522288");
        assertEquals(false, login.checkUserName());
    }

    @Test
    public void testPasswordMeetsComplexityRequirements() {
        Login login = new Login("kyl_1", "Ch&&ser@ke99!", "+27648522288");
        assertEquals(true, login.checkPasswordComplexity());
    }

    @Test
    public void testPasswordDoesNotMeetComplexityRequirements() {
        Login login = new Login("kyl_1", "password", "+27648522288");
        assertEquals(false, login.checkPasswordComplexity());
    }

    @Test
    public void testCellPhoneCorrectlyFormatted() {
        Login login = new Login("kyl_1", "Ch&&ser@ke99!", "+27648522288");
        assertEquals(true, login.checkCellPhoneNumber());
    }

    @Test
    public void testCellPhoneIncorrectlyFormatted() {
        Login login = new Login("kyl_1", "Ch&&ser@ke99!", "0648522288");
        assertEquals(false, login.checkCellPhoneNumber());
    }

    @Test
    public void testLoginSuccessful() {
        Login login = new Login("kyl_1", "Ch&&ser@ke99!", "+27648522288");
        assertTrue(login.loginUser("kyl_1", "Ch&&ser@ke99!"));
    }

    @Test
    public void testLoginFailed() {
        Login login = new Login("kyl_1", "Ch&&ser@ke99!", "+27648522288");
        assertFalse(login.loginUser("wrong", "wrong"));
    }

    @Test
    public void testUsernameCorrectlyFormattedAssertTrue() {
        Login login = new Login("kyl_1", "Ch&&ser@ke99!", "+27648522288");
        assertTrue(login.checkUserName());
    }

    @Test
    public void testUsernameIncorrectlyFormattedAssertFalse() {
        Login login = new Login("kyle !!!!!!!", "Ch&&ser@ke99!", "+27648522288");
        assertFalse(login.checkUserName());
    }

    @Test
    public void testPasswordMeetsRequirementsAssertTrue() {
        Login login = new Login("kyl_1", "Ch&&ser@ke99!", "+27648522288");
        assertTrue(login.checkPasswordComplexity());
    }

    @Test
    public void testPasswordDoesNotMeetRequirementsAssertFalse() {
        Login login = new Login("kyl_1", "password", "+27648522288");
        assertFalse(login.checkPasswordComplexity());
    }

    @Test
    public void testCellPhoneCorrectlyFormattedAssertTrue() {
        Login login = new Login("kyl_1", "Ch&&ser@ke99!", "+27648522288");
        assertTrue(login.checkCellPhoneNumber());
    }

   @Test
public void testCellPhoneIncorrectlyFormattedAssertFalse() {
    Login login = new Login("kyl_1", "Ch&&ser@ke99!", "0648522288");
    assertFalse(login.checkCellPhoneNumber());
}
}


        