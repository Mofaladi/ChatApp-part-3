package com.mycompany.chatapp;

import java.util.Random;

public class Message {
    private String messageID;
    private String recipient;
    private String messageText;
    private String messageHash;
    private static int totalMessagesSent = 0;

    public Message(String recipient, String messageText) {
        this.messageID = generateMessageID();
        this.recipient = recipient;
        this.messageText = messageText;
        this.messageHash = createMessageHash();
    }
    
public Message(int messageNumber, String recipient, String messageText) {
    this.recipient = recipient;
    this.messageText = messageText;
    this.messageID = generateMessageID();
    this.messageHash = createMessageHash();
}

public void setMessageID(String messageID) {
    this.messageID = messageID;
}

public String SentMessage(int option) {
    if (option == 1) {
        return "Message successfully sent.";
    } else if (option == 2) {
        return "Press 0 to delete the message.";
    } else if (option == 3) {
        return "Message successfully stored.";
    } else {
        return "";
    }
}

    public String generateMessageID() {
        Random rand = new Random();
        long id = 1000000000L + (long)(rand.nextDouble() * 9000000000L);
        return String.valueOf(id);
    }

    public boolean checkMessageID() {
        return messageID.length() <= 10;
    }

   public String checkRecipientCell() {
    if (recipient.length() == 12 && recipient.startsWith("+27")) {
        return "Cell phone number successfully captured.";
    } else {
        return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
    }
}

    public String checkMessageLength() {
        if (messageText.length() <= 250) {
            return "Message ready to send.";
        } else {
            int excess = messageText.length() - 250;
            return "Message exceeds 250 characters by " + excess + ", please reduce size.";
        }
    }

    public String createMessageHash() {
        String[] words = messageText.split(" ");
        String first = words[0];
        String last = words[words.length - 1];
        String firstTwo = messageID.substring(0, 2);
        // hash = first 2 digits of ID : number : FIRSTWORD LASTWORD
        return (firstTwo + ":" + totalMessagesSent + ":" + first + last).toUpperCase();
    }

    public static int returnTotalMessages() {
        return totalMessagesSent;
    }

    public static void incrementMessages() {
        totalMessagesSent++;
    }

    public String printMessage() {
        return "MessageID: " + messageID + "\n" +
               "Message Hash: " + messageHash + "\n" +
               "Recipient: " + recipient + "\n" +
               "Message: " + messageText;
    }

    public String getMessageID() { return messageID; }
    public String getRecipient() { return recipient; }
    public String getMessageText() { return messageText; }
    public String getMessageHash() { return messageHash; }
        // ===== PART 3 - ADDED =====
    private String flag = "";

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
