/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp;

import java.io.File;
import java.util.Scanner;

/**
 *
 * @author Student
 */
public class QuickChat {

    public static void main(String[] args) {
        
        Scanner input = new Scanner (System.in);

        // REGISTRATION
        String username;

        while (true) {
            System.out.print("Enter your username: ");
            username = input.nextLine();

            if (username.contains("_") && username.length() <= 5) {
                System.out.println("Username successfully captured.");
                break;
            }

            System.out.println(
                "Username is not correctly formatted; "
                + "please ensure that your username contains "
                + "an underscore and is no more than five "
                + "characters in length."
            );
        }

        System.out.print("Enter your first name: ");
        String firstName = input.nextLine();

        System.out.print("Enter your last name: ");
        String lastName = input.nextLine();

        String password;

        while (true) {
            System.out.print("Enter your password: ");
            password = input.nextLine();

            boolean hasCapital = false;
            boolean hasNumber = false;
            boolean hasSpecial = false;

            for (char c : password.toCharArray()) {
                if (Character.isUpperCase(c)) {
                    hasCapital = true;
                } else if (Character.isDigit(c)) {
                    hasNumber = true;
                } else if (!Character.isLetterOrDigit(c)) {
                    hasSpecial = true;
                }
            }

            if (password.length() >= 8
                    && hasCapital && hasNumber && hasSpecial) {
                System.out.println("Password successfully captured.");
                break;
            }

            System.out.println(
                "Password is not correctly formatted; "
                + "please ensure that the password contains at "
                + "least eight characters, a capital letter, "
                + "a number, and a special character."
            );
        }

        String cellNumber;

        while (true) {
            System.out.print("Enter your South African cell number: ");
            cellNumber = input.nextLine();

            if (cellNumber.startsWith("+27")
                    && cellNumber.length() == 12
                    && cellNumber.substring(3).matches("\\d{9}")) {
                System.out.println(
                    "Cell phone number successfully captured."
                );
                break;
            }

            System.out.println(
                "Cell phone number incorrectly formatted or does "
                + "not contain a valid international code."
            );
        }

        // CREATE USER AND REGISTER
        Login user = new Login(username, password, cellNumber);

        String registrationResult = user.registerUser();
        System.out.println(registrationResult);

        // IMPORTANT: The full stop must match Login.java
        if (!registrationResult.equals(
                "User has been registered successfully.")) {
            input.close();
            return;
        }

        // LOGIN
        System.out.println("\nLOGIN");

        System.out.print("Enter your username to login: ");
        String loginUsername = input.nextLine();

        System.out.print("Enter your password to login: ");
        String loginPassword = input.nextLine();

        boolean loginStatus =
                user.loginUser(loginUsername, loginPassword);

        System.out.println(
            user.returnLoginStatus(loginStatus, firstName, lastName)
        );

        // Do not display the menu unless login succeeds
        if (!loginStatus) {
            input.close();
            return;
        }

        // WELCOME MESSAGE
        System.out.println("\nWelcome to QuickChat.");

        // SET THE MAXIMUM NUMBER OF MESSAGES FOR THIS SESSION
        int maxMessages;

        while (true) {
            maxMessages = readInteger(
                input,
                "How many messages do you wish to enter? "
            );

            if (maxMessages > 0) {
                break;
            }

            System.out.println(
                "Please enter a number greater than zero."
            );
        }

        int messagesEntered = 0;
        int totalMessagesSent = 0;
        boolean running = true;

        // MAIN MENU: REPEATS UNTIL THE USER QUITS
        while (running) {

            System.out.println("\nPlease choose an option:");
            System.out.println("1) Send Messages");
            System.out.println("2) Show recently sent messages");
            System.out.println("3) Quit");

            int option = readInteger(input, "Choose an option: ");

            switch (option) {

                case 1:

                    if (messagesEntered >= maxMessages) {
                        System.out.println(
                            "You have reached your message limit of "
                            + maxMessages + " messages."
                        );
                        break;
                    }

                    // Enter messages only up to the chosen limit
                    while (messagesEntered < maxMessages) {

                        System.out.println(
                            "\nEntering message "
                            + (messagesEntered + 1)
                            + " of " + maxMessages
                        );

                        System.out.print("Enter recipient number: ");
                        String recipient = input.nextLine();

                        System.out.print("Enter your message: ");
                        String messageText = input.nextLine();

                        // Count this message entry
                        messagesEntered++;

                        Message msg = new Message(
                            recipient, messageText
                        );

                        // Validate recipient
                        if (!msg.checkRecipientCell()) {
                            System.out.println(
                                "Invalid recipient number. "
                                + "Please use +27 followed by "
                                + "9 digits."
                            );
                            continue;
                        }

                        // Validate message ID
                        if (!msg.checkMessageID()) {
                            System.out.println("Invalid message ID.");
                            continue;
                        }

                        // Validate message length
                        String messageStatus = msg.sentMessage();

                        if (!messageStatus.equals(
                                "Message ready to send")) {
                            System.out.println(messageStatus);
                            continue;
                        }

                        System.out.println(msg.printMessage());

                        System.out.println("\n1) Send Message");
                        System.out.println("2) Disregard Message");
                        System.out.println("3) Store Message");

                        int choice = readInteger(
                            input, "Choose an option: "
                        );

                        if (choice == 1) {

                            totalMessagesSent++;

                            System.out.println(
                                "Message successfully sent."
                            );

                        } else if (choice == 2) {

                            System.out.println(
                                "Message disregarded."
                            );

                        } else if (choice == 3) {

                            msg.storeMessage();

                            System.out.println(
                                "Message successfully stored."
                            );

                        } else {

                            System.out.println(
                                "Invalid option. Message not sent."
                            );
                        }

                        System.out.println(
                            "Messages entered: " + messagesEntered
                            + " of " + maxMessages
                        );
                    }

                    System.out.println(
                        "You have reached your message entry limit."
                    );

                    System.out.println(
                        "Total messages sent: " + totalMessagesSent
                    );

                    break;

                case 2:

                    // This feature is still in development
                    System.out.println("Coming Soon.");

                    break;

                case 3:

                    System.out.println("Application Closed.");
                    running = false;

                    break;

                default:

                    System.out.println(
                        "Invalid option. Please choose 1, 2, or 3."
                    );
            }
        }

        input.close();
       }

         // Helper method to safely read numeric menu choices
          private static int readInteger(
            Scanner input, String prompt) {

          while (true) {
            System.out.print(prompt);
            String value = input.nextLine();

            try {
                return Integer.parseInt(value.trim());
            } catch (NumberFormatException e) {
                System.out.println(
                    "Invalid input. Please enter a whole number."
                );
            }
        }
    }
}
