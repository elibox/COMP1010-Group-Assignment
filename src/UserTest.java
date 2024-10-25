import static org.junit.Assert.*;
import org.junit.Test;


public class UserTest {
    @Test
    public void testUser() {
        //test for the main constructor in the user class
        User user = new User(12345678, "j0hN", "john.doe@gmail.com", "jontron3000");
        assertEquals(12345678, user.studentId);
        assertEquals("j0hN", user.username);
        assertEquals("john.doe@gmail.com", user.email);
        assertEquals("jontron3000", user.password);
        assertTrue(user.friendsList.isEmpty());
        assertTrue(user.blockList.isEmpty());
        assertTrue(user.subscriptions.isEmpty());
    }

    @Test
    //testing subscribeToChannel() function
    public void testSubscribeToChannel() {
        User user = new User(12345678, "j0hN", "john.doe@gmail.com", "jontron3000");
        Channel channel = new Channel("Study", "Computer Science");
        user.subscribeToChannel(channel);
        assertEquals(1, user.subscriptions.size());
        assertEquals(channel, user.subscriptions.get(0).channel);

        //edge case, if user tries to subscribe to a channel they are already subscribed too
        int originalSize = user.subscriptions.size();
        user.subscribeToChannel(channel);
        assertEquals(originalSize, user.subscriptions.size()); //should not duplicate
    }

    @Test
    //testing UnsubscribeFromChannel() function
    public void testUnsubscribeFromChannel() {
        User user = new User(12345678, "j0hN", "john.doe@gmail.com", "jontron3000");
        Channel channel = new Channel("Study", "Computer Science");
        user.subscribeToChannel(channel);
        user.unsubscribeFromChannel(0);
        assertTrue(user.subscriptions.isEmpty());

        //edge case, if a user attempts to unsubscribe from a channel when they aren't subscribed to any
        int originalSize = user.subscriptions.size();
        user.unsubscribeFromChannel(0);
        assertEquals(originalSize, user.subscriptions.size()); //subscriptions should remain unchanged
    }

    @Test
    //testing addFriend() function
    public void testAddFriend() {
        User user1 = new User(12345678, "j0hN", "john.doe@gmail.com", "jontron3000");
        User user2 = new User(12345679, "janeee", "jane.doe@gmail.com", "abcde999");
        user1.addFriend(user2);
        assertEquals(user2, user1.friendsList.get(0));

        //edge case, if a user attempts to add the same friend again
        int originalSize = user1.friendsList.size();
        user1.addFriend(user2);
        assertEquals(originalSize, user1.friendsList.size()); //should not duplicate
    }

    @Test
    //testing removeFriend() function
    public void testRemoveFriend() {
        User user1 = new User(12345678, "j0hN", "john.doe@gmail.com", "jontron3000");
        User user2 = new User(12345679, "janeee", "jane.doe@gmail.com", "abcde999");
        user1.addFriend(user2);
        user1.removeFriend(user2);
        assertTrue(user1.friendsList.isEmpty());

        //edge case, if a user attempts to remove a friend they don't have added
        int originalSize = user1.friendsList.size();
        user1.removeFriend(user2);
        assertEquals(originalSize, user1.friendsList.size()); //friendsList should remain unchanged
    }

    @Test
    //testing blockUser() function
    public void testBlockUser() {
        User user1 = new User(12345678, "j0hN", "john.doe@gmail.com", "jontron3000");
        User user2 = new User(12345679, "janeee", "jane.doe@gmail.com", "abcde999");
        user1.blockUser(user2, user1.blockList);
        assertEquals(user2, user1.blockList.get(0));

        //edge case, if a user attempts to block some they've already blocked
        int originalSize = user1.blockList.size();
        user1.blockUser(user2, user1.blockList);
        assertEquals(originalSize, user1.blockList.size()); //should not duplicate
    }

    @Test
    //testing unblockUser() function
    public void testUnblockUser() {
        User user1 = new User(12345678, "j0hN", "john.doe@gmail.com", "jontron3000");
        User user2 = new User(12345679, "janeee", "jane.doe@gmail.com", "abcde999");
        user1.blockUser(user2, user1.blockList);
        user1.unblockUser(user2, user1.blockList);
        assertTrue(user1.blockList.isEmpty());

        //edge case, if a user attempts to unblock someone they haven't blocked
        int originalSize = user1.blockList.size();
        user1.unblockUser(user2, user1.blockList);
        assertEquals(originalSize, user1.blockList.size()); //blockList should remain unchanged
    }
}
