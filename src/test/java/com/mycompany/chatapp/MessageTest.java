/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.chatapp;

import java.util.Arrays;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Student
 */
public class MessageTest {
    
    public MessageTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of checkMessageID method, of class Message.
     */
    @Test
    public void testCheckMessageID() {

        Message msg = new Message(
                "+27821234567",
                "Hello, how are you?"
        );

        assertTrue(
                msg.checkMessageID(),
                "Message ID should be 10 characters or less."
        );
    }

    // Test 2: Check that a valid South African
    // international recipient number is accepted.
    @Test
    public void testValidRecipientCell() {

        Message msg = new Message(
                "+27821234567",
                "Hello"
        );

        assertTrue(
                msg.checkRecipientCell(),
                "A valid +27 recipient number should be accepted."
        );
    }

    // Test 3: Check that an invalid recipient
    // number is rejected.
    @Test
    public void testInvalidRecipientCell() {

        Message msg = new Message(
                "0821234567",
                "Hello"
        );

        assertFalse(
                msg.checkRecipientCell(),
                "A number without the +27 code should be rejected."
        );
    }

    // Test 4: Check that a message of 250 characters
    // is accepted.
    @Test
    public void testMessageLengthWithinLimit() {

        String text = createText(250);

        Message msg = new Message(
                "+27821234567",
                text
        );

        assertEquals(
                "Message ready to send",
                msg.sentMessage(),
                "A 250-character message should be accepted."
        );
    }

    // Test 5: Check that a message exceeding
    // 250 characters is rejected.
    @Test
    public void testMessageLengthExceedsLimit() {

        String text = createText(251);

        Message msg = new Message(
                "+27821234567",
                text
        );

        assertEquals(
                "Message exceeds 250 characters by 1, "
                + "please reduce size.",
                msg.sentMessage(),
                "An overlong message should display an error."
        );
    }

    // Test 6: Check that the message hash contains
    // the first two characters of the message ID,
    // a message number, and the first and last words.
    @Test
    public void testCreateMessageHash() {

        Message msg = new Message(
                "+27821234567",
                "hello world"
        );

        String printed = msg.printMessage();

        String id = printed.substring(
                "Message ID: ".length(),
                printed.indexOf('\n')
        );

        String hash = msg.createMessageHash();

        String expectedPattern =
                java.util.regex.Pattern.quote(id.substring(0, 2))
                + ":\\d+:HELLOWORLD";

        assertTrue(
                hash.matches(expectedPattern),
                "Hash should contain the ID prefix, "
                + "message number, and uppercase first "
                + "and last words."
        );
    }

    // Test 7: Check that printMessage displays
    // all the required message details.
    @Test
    public void testPrintMessage() {

        Message msg = new Message(
                "+27821234567",
                "Hello there"
        );

        String result = msg.printMessage();

        assertTrue(result.contains("Message ID: "));
        assertTrue(result.contains("Message Hash: "));
        assertTrue(result.contains("Recipient: +27821234567"));
        assertTrue(result.contains("Message: Hello there"));
    }

    // Helper method for creating messages
    // of a specific length.
    private String createText(int length) {

        char[] characters = new char[length];
        Arrays.fill(characters, 'A');

        return new String(characters);
    }
}

