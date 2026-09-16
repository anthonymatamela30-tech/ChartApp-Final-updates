/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.chatapp;

import java.util.Scanner;

/**
 *
 * @author Student
 */
public class ChatApp {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter username: ");
        String username = input.nextLine();

        System.out.print("Enter password: ");
        String password = input.nextLine();

        System.out.print("Enter South African cell number: ");
        String cellPhoneNumber = input.nextLine();

        System.out.print("Enter first name: ");
        String firstName = input.nextLine();

        System.out.print("Enter last name: ");
        String lastName = input.nextLine();

        Test user = new Test(username, password, cellPhoneNumber);

        String registrationMessage = user.registerUser();
        System.out.println(registrationMessage);

        if (registrationMessage.equals("User has been registered successfully.")) {
            System.out.print("Enter username to login: ");
            String enteredUsername = input.nextLine();

            System.out.print("Enter password to login: ");
            String enteredPassword = input.nextLine();

            boolean loginStatus = user.loginUser(enteredUsername, enteredPassword);
            System.out.println(user.returnLoginStatus(loginStatus, firstName, lastName));
        }

        input.close();
    }
}
