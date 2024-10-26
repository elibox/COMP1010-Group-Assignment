import static org.junit.Assert.*;
import org.junit.Test;
import java.util.*;

public class MessageTest {
    @Test
    public void testMessage() {
        User sender = new User(12345678, "j0hN", "john.doe@gmail.com", "jontron3000");
        User recipient = new User(12345679, "janeee", "jane.doe@gmail.com", "abcde999");
        Channel channel = new Channel("Study", "Computer Science");
        ArrayList<User> groupChatMembers = new ArrayList<>();
        groupChatMembers.add(sender);
        groupChatMembers.add(recipient);
        String messageContents = "Hi there 3-3";
        Message message = new Message(sender, messageContents, new Date(), new Time(), channel, groupChatMembers, recipient);
        assertEquals(sender, message.sender);
        assertEquals(recipient, message.recipient);
        assertEquals(messageContents, message.message);
        assertEquals(groupChatMembers, message.groupChatMembers);
    }

    @Test
    public void testSendChannelMessage() {
        User sender = new User(12345678, "j0hN", "john.doe@gmail.com", "jontron3000");
        Channel channel = new Channel("Study", "Computer Science");
        ArrayList<Message> messages = new ArrayList<>();
        Message message = new Message(sender, "Have you done the quiz yet?", new Date(), new Time(), channel, null, null);

        message.sendChannelMessage(messages);
        assertEquals(1, messages.size());
        assertEquals(message, messages.get(0));

        //edge case, if message is sent to a non existent channel
        message.channel = null;
        messages.clear();
        message.sendChannelMessage(messages);
        assertEquals(0, messages.size()); //message should not be added
    }

    @Test
    public void testSendPrivateMessage() {
        User sender = new User(12345678, "j0hN", "john.doe@gmail.com", "jontron3000");
        User recipient = new User(12345679, "janeee", "jane.doe@gmail.com", "abcde999");
        ArrayList<Message> messages = new ArrayList<>();
        sender.blockList.add(recipient);
        Message message = new Message(sender, "hi jane, hru", new Date(), new Time(), null, null, recipient);

        //attempting to send message to a blocked user
        message.sendPrivateMessage(messages);
        assertEquals(0, messages.size()); //message should not be added 

        //edge case, if sender unblocks the recepient and sends a message
        sender.blockList.remove(recipient);
        messages.clear();
        message.sendPrivateMessage(messages);
        assertEquals(1, messages.size());
        assertEquals(message, messages.get(0));
    }

    @Test
    public void testSendGroupMessage() {
        User sender = new User(12345678, "j0hN", "john.doe@gmail.com", "jontron3000");
        ArrayList<User> groupChatMembers = new ArrayList<>();
        groupChatMembers.add(sender);
        User recipient = new User(12345679, "janeee", "jane.doe@gmail.com", "abcde999");
        groupChatMembers.add(recipient);

        ArrayList<Message> messages = new ArrayList<>();
        Message message = new Message(sender, "hey guys, wanna play roblox", new Date(), new Time(), null, groupChatMembers, null);

        //sending a group message
        message.sendGroupMessage(messages);
        assertEquals(1, messages.size());
        assertEquals(message, messages.get(0));

        //edge case, if group chat has less than 2 members
        groupChatMembers.remove(recipient);
        messages.clear();
        message.sendGroupMessage(messages);
        assertEquals(0, messages.size()); //message should not be added
    }

    @Test
    public void testDeleteMessage() {
        User sender = new User(12345678, "j0hN", "john.doe@gmail.com", "jontron3000");
        ArrayList<Message> messages = new ArrayList<>();
        Message message = new Message(sender, "Hi.", new Date(), new Time(), null, null, null);

        messages.add(message);
        assertEquals(1, messages.size());

        message.deleteMessage(messages);
        assertEquals(0, messages.size()); //message should be deleted 
    }

    @Test
    public void testAddUserToGroupChat() {
        User groupChatOwner = new User(12345678, "j0hN", "john.doe@gmail.com", "jontron3000");
        ArrayList<User> groupChatMembers = new ArrayList<>();
        groupChatMembers.add(groupChatOwner);
        Message message = new Message(groupChatOwner, "welcome to the gc!", new Date(), new Time(), null, groupChatMembers, null);

        User newMember = new User(12345679, "janeee", "jane.doe@gmail.com", "abcde999");
        message.addUserToGroupChat(newMember);

        assertEquals(2, message.groupChatMembers.size());
        assertTrue(message.groupChatMembers.contains(newMember));

        //edge case, attempting to add the same user again
        int originalSize = message.groupChatMembers.size();
        message.addUserToGroupChat(newMember);
        assertEquals(originalSize, message.groupChatMembers.size()); //should not duplicate
    }

    @Test
    public void testRemoveUserFromGroupChat() {
        User groupChatOwner = new User(12345678, "j0hN", "john.doe@gmail.com", "jontron3000");
        User member = new User(12345679, "janeee", "jane.doe@gmail.com", "abcde999");
        User otherMember = new User(12345680, "jojo", "jojo.dodo@gmail.com", "owowo001");
        
        ArrayList<User> groupChatMembers = new ArrayList<>();
        groupChatMembers.add(groupChatOwner);
        groupChatMembers.add(member);
        groupChatMembers.add(otherMember);
        Message message = new Message(groupChatOwner, "bye bye!", new Date(), new Time(), null, groupChatMembers, null);
        
        assertEquals(3, message.groupChatMembers.size());

        message.removeUserFromGroupChat(member);
        assertEquals(2, message.groupChatMembers.size());
        assertFalse(message.groupChatMembers.contains(member));

        //edge case, attempting to remove a member when there are only 2 members in the group chat
        int originalSize = message.groupChatMembers.size();
        message.removeUserFromGroupChat(otherMember);
        assertEquals(originalSize, message.groupChatMembers.size()); //should remain unchanged
        assertTrue(message.groupChatMembers.contains(otherMember));
    }
}
