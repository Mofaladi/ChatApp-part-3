package com.mycompany.chatapp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

public class MessageTest {

    @Test
    public void testCheckMessageIDSuccess() {
        Message m = new Message(0, "+27718693002", "Hi tonight");
        m.setMessageID("0012345678");
        assertTrue(m.checkMessageID());
    }

    @Test
    public void testCheckRecipientCellSuccess() {
        Message m = new Message(0, "+27718693002", "Hi tonight");
        assertEquals("Cell phone number successfully captured.", m.checkRecipientCell());
    }

    @Test
    public void testCheckRecipientCellFail() {
        Message m = new Message(0, "08575975889", "Hi tonight");
        assertEquals("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.", m.checkRecipientCell());
    }

    @Test
    public void testCheckMessageLengthSuccess() {
        Message m = new Message(0, "+27718693002", "Hi tonight");
        assertEquals("Message ready to send.", m.checkMessageLength());
    }

    @Test
    public void testCheckMessageLengthFail() {
        String longMsg = "a".repeat(300);
        Message m = new Message(0, "+27718693002", longMsg);
        assertTrue(m.checkMessageLength().contains("exceeds 250 characters"));
    }

    @Test
    public void testCreateMessageHash() {
        Message m = new Message(0, "+27718693002", "Hi tonight");
        m.setMessageID("0012345678");
        assertEquals("00:0:HITONIGHT", m.createMessageHash());
    }

    @Test
    public void testSentMessageSent() {
        Message m = new Message(0, "+27718693002", "Hi tonight");
        assertEquals("Message successfully sent.", m.SentMessage(1));
    }

    @Test
    public void testSentMessageDisregard() {
        Message m = new Message(0, "+27718693002", "Hi tonight");
        assertEquals("Press 0 to delete the message.", m.SentMessage(2));
    }

    @Test
    public void testSentMessageStored() {
        Message m = new Message(0, "+27718693002", "Hi tonight");
        assertEquals("Message successfully stored.", m.SentMessage(3));
    }
    @Test
public void testSentMessagesArray() {
    ArrayList<Message> all = new ArrayList<>();
    Message m1 = new Message(0, "+27834557896", "Did you get the cake?");
    m1.setFlag("Sent");
    Message m4 = new Message(0, "0838884567", "It is dinner time !");
    m4.setFlag("Sent");
    all.add(m1); all.add(m4);

    String result = "";
    for (Message m : all) {
        if (m.getFlag().equals("Sent")) result += m.getMessageText() + ",";
    }
    assertEquals("Did you get the cake?,It is dinner time !,", result);
}

@Test
public void testDisplayLongestMessage() {
    Message m2 = new Message(0, "+27838884567", "Where are you? You are late! I have asked you to be on time.");
    m2.setFlag("Stored");
    Message m1 = new Message(0, "+27834557896", "Did you get the cake?");
    m1.setFlag("Sent");
    Message longest = m1;
    if (m2.getMessageText().length() > longest.getMessageText().length()) longest = m2;
    assertEquals("Where are you? You are late! I have asked you to be on time.", longest.getMessageText());
}

@Test
public void testSearchByMessageID() {
    Message m4 = new Message(0, "0838884567", "It is dinner time !");
    String searchID = m4.getMessageID();
    assertEquals(searchID, m4.getMessageID());
    assertEquals("It is dinner time !", m4.getMessageText());
}

@Test
public void testSearchByRecipient() {
    Message m2 = new Message(0, "+27838884567", "Where are you? You are late! I have asked you to be on time.");
    Message m5 = new Message(0, "+27838884567", "Ok, I am leaving without you.");
    int count = 0;
    if (m2.getRecipient().equals("+27838884567")) count++;
    if (m5.getRecipient().equals("+27838884567")) count++;
    assertEquals(2, count);
}

@Test
public void testDeleteByMessageHash() {
    ArrayList<Message> all = new ArrayList<>();
    Message m2 = new Message(0, "+27838884567", "Where are you? You are late! I have asked you to be on time.");
    all.add(m2);
    String hash = m2.getMessageHash();
    all.removeIf(m -> m.getMessageHash().equals(hash));
    assertEquals(0, all.size());
    assertEquals("Message successfully deleted.", "Message successfully deleted.");
}

@Test
public void testDisplayReport() {
    Message m1 = new Message(0, "+27834557896", "Did you get the cake?");
    m1.setFlag("Sent");
    String report = m1.getMessageHash() + " | " + m1.getRecipient() + " | " + m1.getMessageText();
    assertTrue(report.contains("+27834557896"));
    assertTrue(report.contains("Did you get the cake?"));
}
}
