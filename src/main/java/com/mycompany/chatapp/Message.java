/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Student
 */
public class Message {
    
    private String messageID;
    private int messageNumber;
    private String recipient;
    private String message;
    private String messageHash;

    private static int counter = 0;

    // Load previously saved messages when the class starts
    private static JSONArray messageList = loadMessages();

    // Constructor
    public Message(String recipient, String message) {

        this.recipient = recipient;
        this.message = message;

        counter++;
        this.messageNumber = counter;

        // Generate a random 10-digit message ID
        this.messageID = String.valueOf(
            ThreadLocalRandom.current().nextLong(
                1_000_000_000L, 10_000_000_000L
            )
        );
    }

    // Check message ID
    public boolean checkMessageID() {
        return messageID != null && messageID.length() <= 10;
    }

    // Check South African recipient number
    public boolean checkRecipientCell() {

        return recipient != null
                && recipient.startsWith("+27")
                && recipient.length() == 12
                && recipient.substring(3).matches("\\d{9}");
    }

    // Check message length
    public String sentMessage() {

        if (message.length() > 250) {

            int excess = message.length() - 250;

            return "Message exceeds 250 characters by "
                    + excess + ", please reduce size.";

        }

        return "Message ready to send";
    }

    // Create message hash
    public String createMessageHash() {

        String trimmedMessage = message.trim();

        String[] words;

        if (trimmedMessage.isEmpty()) {
            words = new String[]{"EMPTY"};
        } else {
            words = trimmedMessage.split("\\s+");
        }

        String firstWord = words[0];
        String lastWord = words[words.length - 1];

        messageHash = messageID.substring(0, 2)
                + ":" + messageNumber
                + ":" + firstWord.toUpperCase()
                + lastWord.toUpperCase();

        return messageHash;
    }

    // Display message details
    public String printMessage() {

        return "Message ID: " + messageID
                + "\nMessage Hash: " + createMessageHash()
                + "\nRecipient: " + recipient
                + "\nMessage: " + message;
    }

    // Store message in message.json
    public JSONObject storeMessage() {

        JSONObject obj = new JSONObject();

        obj.put("MessageID", messageID);
        obj.put("MessageHash", createMessageHash());
        obj.put("Recipient", recipient);
        obj.put("Message", message);

        messageList.add(obj);

        try (FileWriter writer = new FileWriter("message.json")) {

            writer.write(messageList.toJSONString());
            writer.flush();

        } catch (IOException e) {

            System.out.println(
                "Error saving message: " + e.getMessage()
            );
        }

        return obj;
    }

    // Load previously stored messages
    private static JSONArray loadMessages() {

        File file = new File("message.json");

        if (!file.exists()) {
            return new JSONArray();
        }

        try (FileReader reader = new FileReader(file)) {

            Object data = new JSONParser().parse(reader);

            if (data instanceof JSONArray) {
                return (JSONArray) data;
            }

        } catch (IOException | ParseException e) {

            System.out.println(
                "Could not load saved messages: " + e.getMessage()
            );
        }

        return new JSONArray();
    }

    // Read and display messages from the JSON file
    public static void readMessage() {

        File file = new File("message.json");

        if (!file.exists()) {
            System.out.println("No stored messages found.");
            return;
        }

        try (FileReader reader = new FileReader(file)) {

            Object data = new JSONParser().parse(reader);

            if (!(data instanceof JSONArray)) {
                System.out.println("Invalid message file format.");
                return;
            }

            JSONArray storedMessages = (JSONArray) data;

            if (storedMessages.isEmpty()) {
                System.out.println("No stored messages found.");
                return;
            }

            System.out.println("\nStored Messages:");

            for (Object storedMessage : storedMessages) {
                System.out.println(storedMessage.toString());
            }

        } catch (IOException | ParseException e) {

            System.out.println(
                "Error reading messages: " + e.getMessage()
            );
        }
    }
}
  
