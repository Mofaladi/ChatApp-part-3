package com.mycompany.chatapp;

// part 2 commit 6
import java.util.Scanner;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ChatApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Login loginObj = new Login("", "", "");
        ArrayList<Message> allMessages = new ArrayList<>();

        System.out.println("=== REGISTRATION ===");
        System.out.print("Enter username (must contain _ and max 5 chars): ");
        String username = scanner.nextLine();
        System.out.print("Enter password (8 chars, capital, number, special char): ");
        String password = scanner.nextLine();
        System.out.print("Enter cellphone (e.g. +2783123456): ");
        String cell = scanner.nextLine();

        String regResult = loginObj.registerUser(username, password, cell);
        System.out.println(regResult);

        if (!regResult.equals("User registered successfully.")) {
            System.out.println("Registration failed. Exiting.");
            scanner.close();
            return;
        }

        System.out.println("\n=== LOGIN ===");
        System.out.print("Enter username: ");
        String loginU = scanner.nextLine();
        System.out.print("Enter password: ");
        String loginP = scanner.nextLine();

        System.out.println(loginObj.returnLoginStatus(loginU, loginP));

        if (!loginObj.loginUser(loginU, loginP)) {
            scanner.close();
            return;
        }

        // PART 2 - QUICKCHAT
        System.out.println("\nWelcome to QuickChat.");
        System.out.print("How many messages do you wish to send? Enter number: ");
        int numMessages = Integer.parseInt(scanner.nextLine());

        // LOOP for sending messages
        for (int i = 0; i < numMessages; i++) {
            System.out.println("\n--- Message " + (i+1) + "/" + numMessages + " ---");
            
            System.out.print("Enter recipient cell (e.g. +2771234567, max 10 chars): ");
            String recipient = scanner.nextLine();

            System.out.print("Enter your message (max 250 chars): ");
            String text = scanner.nextLine();

            Message msg = new Message(recipient, text);

            // Check recipient - Learning Unit 5 if else
            if (!msg.checkRecipientCell().equals("Cell phone number successfully captured.")) {
                System.out.println("Cell phone number is incorrectly formatted or does not contain an international code, please correct the number and try again.");
                i--; // ask again
                continue;
            }

            // Check length
            String lengthCheck = msg.checkMessageLength();
            System.out.println(lengthCheck);

            if (lengthCheck.startsWith("Message exceeds")) {
                i--; // ask again
                continue;
            }

            // Display message details
            System.out.println("\nMessage Details:");
            System.out.println(msg.printMessage());

            System.out.println("\nWhat do you want to do?");
            System.out.println("1) Send Message");
            System.out.println("2) Disregard Message");
            System.out.println("3) Store Message to send later");
            System.out.print("Choose (1-3): ");
            String choice = scanner.nextLine();

            // Switch - Learning Unit 5
            switch (choice) {
                case "1":      
            msg.setFlag("Sent"); // ADD THIS
            allMessages.add(msg);
             Message.incrementMessages();
            System.out.println("Message successfully sent.");
                 break;
                case "2":
                    msg.setFlag("Disregarded");
                    System.out.println("Press 0 to delete message.");
                    System.out.println("Message disregarded.");
                    break;
               case "3":
               msg.setFlag("Stored"); // ADD THIS
                allMessages.add(msg);
                   System.out.println("Message successfully stored.");
                 break;
                default:
                    System.out.println("Invalid option, message disregarded.");
                    break;
            }
        }

        // Display all messages
        System.out.println("\n=== All Messages ===");
        for (Message m : allMessages) {
            System.out.println(m.printMessage());
            System.out.println("-------------------");
        }
        System.out.println("Total messages sent: " + Message.returnTotalMessages());
        // ===== PART 3 MENU =====
int part3Choice;
do {
    System.out.println("\n--- Part 3 Menu ---");
    System.out.println("1) Display all senders and recipients of sent messages");
    System.out.println("2) Display the longest sent message");
    System.out.println("3) Search for message by Message ID");
    System.out.println("4) Search for messages by Recipient");
    System.out.println("5) Delete message by Message Hash");
    System.out.println("6) Display full report of sent messages");
    System.out.println("0) Quit");
    System.out.print("Enter choice: ");
    part3Choice = Integer.parseInt(scanner.nextLine());

    switch (part3Choice) {
        case 1:
            System.out.println("\n--- Senders and Recipients (Sent) ---");
            for (Message m : allMessages) {
                if (m.getFlag().equals("Sent")) {
                    System.out.println("Recipient: " + m.getRecipient() + " | Message: " + m.getMessageText());
                }
            }
            break;

        case 2:
            Message longest = null;
            for (Message m : allMessages) {
                if (!m.getFlag().equals("Disregard")) {
                    if (longest == null || m.getMessageText().length() > longest.getMessageText().length()) {
                        longest = m;
                    }
                }
            }
            if (longest != null) {
                System.out.println("\nLongest message: " + longest.getMessageText());
                System.out.println("Recipient: " + longest.getRecipient());
            } else {
                System.out.println("No sent messages.");
            }
            break;

        case 3:
            System.out.print("Enter Message ID to search: ");
            String searchID = scanner.nextLine();
            boolean foundID = false;
            for (Message m : allMessages) {
                if (m.getMessageID().equals(searchID)) {
                    System.out.println("\nRecipient: " + m.getRecipient());
                    System.out.println("Message: " + m.getMessageText());
                    foundID = true;
                }
            }
            if (!foundID) System.out.println("Message ID not found.");
            break;

        case 4:
            System.out.print("Enter Recipient to search: ");
            String searchRec = scanner.nextLine();
            boolean foundRec = false;
            for (Message m : allMessages) {
                if (m.getRecipient().equals(searchRec)) {
                    System.out.println("\nMessage: " + m.getMessageText());
                    foundRec = true;
                }
            }
            if (!foundRec) System.out.println("No messages for that recipient.");
            break;

        case 5:
            System.out.print("Enter Message Hash to delete: ");
            String searchHash = scanner.nextLine();
            boolean deleted = false;
            for (int i = 0; i < allMessages.size(); i++) {
                if (allMessages.get(i).getMessageHash().equals(searchHash)) {
                    System.out.println("Message \"" + allMessages.get(i).getMessageText() + "\" successfully deleted.");
                    allMessages.remove(i);
                    deleted = true;
                    break;
                }
            }
            if (!deleted) System.out.println("Message Hash not found.");
            break;

        case 6:
            System.out.println("\n--- Full Report (Sent) ---");
            for (Message m : allMessages) {
                if (m.getFlag().equals("Sent")) {
                    System.out.println(m.printMessage());
                    System.out.println("----------------------");
                }
            }
            break;
            
            case 7:
    loadFromJSON();
    break;


        case 0:
            System.out.println("Goodbye!");
            break;

        default:
            System.out.println("Invalid option.");
    }
} while (part3Choice != 0);

        scanner.close();
    }
    public static void loadFromJSON() {
    try {
        String content = new String(java.nio.file.Files.readAllBytes(
            java.nio.file.Paths.get("src/com/mycompany/chatapp/storedMessages.json")));
        JOptionPane.showMessageDialog(null, "Messages loaded from JSON:\n" + content);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "JSON file not found: " + e.getMessage());
    }
}
}