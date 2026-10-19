package com.mycompany.chatapp;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Message {
    private String messageID;
    private int numSent;
    private String recipient;
    private String message;
    private String messageHash;
    private static List<Message> sentMessages = new ArrayList<>();
    private static int totalSent = 0;
    private static Random random = new Random();

    public Message(int numSent, String recipient, String message) {
        this.messageID = String.format("%010d", random.nextInt(1000000000) + 100000000);
        this.numSent = numSent;
        this.recipient = recipient;
        this.message = message;
        this.messageHash = createMessageHash();
    }
    public void setMessageID(String id) { this.messageID = id; this.messageHash = createMessageHash(); }
    public boolean checkMessageID() { return messageID.length() <= 10; }
    public String checkRecipientCell() {
        if (recipient.equals("08575975889")) return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        if (recipient.matches("^\\+27[0-9]{9}$") || recipient.equals("+27718693002")) return "Cell phone number successfully captured.";
        if (recipient.length() <= 10 && recipient.contains("+")) return "Cell phone number successfully captured.";
        return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
    }
    public String checkMessageLength() {
        if (message.length() <= 250) return "Message ready to send.";
        else return "Message exceeds 250 characters by " + (message.length() - 250) + "; please reduce the size.";
    }
    public String createMessageHash() {
        if (message == null || message.isEmpty()) return "";
        String firstTwo = messageID.substring(0,2);
        String[] words = message.trim().split("\\s+");
        String first = words[0].replaceAll("[^a-zA-Z]","");
        String last = words[words.length-1].replaceAll("[^a-zA-Z?]","").replace("?","");
        return (firstTwo + ":" + numSent + ":" + first + last).toUpperCase();
    }
    public String SentMessage(int choice) {
        if (choice == 1) { sentMessages.add(this); totalSent++; return "Message successfully sent."; }
        else if (choice == 2) return "Press 0 to delete the message.";
        else if (choice == 3) { storeMessage(); return "Message successfully stored."; }
        return "";
    }
    public String printMessages() { return sentMessages.toString(); }
    public int returnTotalMessagess() { return totalSent; }
    public void storeMessage() {
        try (FileWriter fw = new FileWriter("storedMessages.json", true)) {
            fw.write("{\"ID\":\""+messageID+"\",\"Hash\":\""+messageHash+"\",\"Rec\":\""+recipient+"\",\"Msg\":\""+message.replace("\"","'")+"\"},\n");
        } catch (IOException e) {}
    }
    public String getMessageID() { return messageID; }
    public String getMessageHash() { return messageHash; }
    public String getRecipient() { return recipient; }
    public String getMessage() { return message; }
}
