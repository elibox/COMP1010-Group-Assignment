import static org.junit.Assert.*;
import org.junit.Test;


public class UserTest {
    @Test
    public void testUser() {
        User user = new User(12345678, "j0hN", "john.doe@gmail.com", "jontron3000");
        assertEquals(12345678, user.studentId);
        assertEquals("j0hN", user.username);
        assertEquals("john.doe@gmail.com", user.email);
        assertEquals("jontron3000", user.password);
        assertTrue(user.friendsList == null);
        assertTrue(user.blockList == null);
        assertTrue(user.subscriptions.isEmpty());
    }

    @Test
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
    public void testAddFriend() {
        User user1 = new User(12345678, "j0hN", "john.doe@gmail.com", "jontron3000");
        User user2 = new User(12345679, "janeee", "jane.doe@gmail.com", "abcde999");
        user1.addFriend(user2);
        assertEquals(user2, user1.friendsList.friend);

        //edge case, if a user attempts to add the same friend again
        int originalSize = friendCount(user1.friendsList);
        user1.addFriend(user2);
        assertEquals(originalSize, friendCount(user1.friendsList)); //should not duplicate
    }

    @Test
    public void testRemoveFriend() {
        User user1 = new User(12345678, "j0hN", "john.doe@gmail.com", "jontron3000");
        User user2 = new User(12345679, "janeee", "jane.doe@gmail.com", "abcde999");
        user1.addFriend(user2);
        user1.removeFriend(user2);
        assertTrue(user1.friendsList == null);

        //edge case, if a user attempts to remove a friend they don't have added
        int originalSize = friendCount(user1.friendsList);
        user1.removeFriend(user2);
        assertEquals(originalSize, friendCount(user1.friendsList)); //friendsList should remain unchanged
    }

    @Test
    public void testBlockUser() {
        User user1 = new User(12345678, "j0hN", "john.doe@gmail.com", "jontron3000");
        User user2 = new User(12345679, "janeee", "jane.doe@gmail.com", "abcde999");
        user1.blockUser(user2);
        assertEquals(user2, user1.blockList.blockedUser);

        //edge case, if a user attempts to block some they've already blocked
        int originalSize = blockedCount(user1.blockList);
        user1.blockUser(user2);
        assertEquals(originalSize, blockedCount(user1.blockList)); //should not duplicate
    }

    @Test
    public void testUnblockUser() {
        User user1 = new User(12345678, "j0hN", "john.doe@gmail.com", "jontron3000");
        User user2 = new User(12345679, "janeee", "jane.doe@gmail.com", "abcde999");
        user1.blockUser(user2);
        user1.unblockUser(user2);
        assertTrue(user1.blockList == null);

        //edge case, if a user attempts to unblock someone they haven't blocked
        int originalSize = blockedCount(user1.blockList);
        user1.unblockUser(user2);
        assertEquals(originalSize, blockedCount(user1.blockList)); //blockList should remain unchanged
    }
        
    //helper recursive method - returns number of friends
    public int friendCount(FriendNode node) {
        if(node == null) {
            return 0;
        }
        return 1 + friendCount(node.next);
    }

    //helper recursive method - returns number of blocked users
    public int blockedCount(BlockNode node) {
        if(node == null) {
            return 0;
        }
        return 1 + blockedCount(node.next);
    }
}
