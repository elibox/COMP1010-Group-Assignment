import java.util.ArrayList;

public class Client {
    public static void main(String[] args) {
        User user1 = new User(12345678, "j0hN", "john.doe@gmail.com", "jontron3000");
        User user2 = new User(12345679, "janeee", "jane.doe@gmail.com", "abcde999");
        User user3 = new User(12345680, "jojo", "jojo.dodo@gmail.com", "owowo001");

        // Display initial user details
        System.out.println("Created Users:");
        System.out.println("User1: " + user1.username);
        System.out.println("User2: " + user2.username);
        System.out.println("User3: " + user3.username);

        // Test adding and removing friends
        user1.addFriend(user2);
        user1.displayFriendsList(); // Display updated friends list after adding

        user1.removeFriend(user2);
        user1.displayFriendsList(); // Display updated friends list after removing

        // Test blocking and unblocking
        user1.blockUser(user2);
        user1.displayBlockList(); // Display updated block list after blocking

        user1.unblockUser(user2);
        user1.displayBlockList(); // Display updated block list after unblocking

        // Create a Channel and test subscribing/unsubscribing
        Channel studyChannel = new Channel("Study", "Computer Science");
        user1.subscribeToChannel(studyChannel);
        user1.displayChannelSubscriptions(); // Display updated subscriptions after subscribing

        user1.unsubscribeFromChannel(0);
        user1.displayChannelSubscriptions(); // Display updated subscriptions after unsubscribing

        // Create a Message list
        ArrayList<Message> messageList = new ArrayList<>();

        // Send a private message
        Message privateMessage = new Message(user1, "Hi, how are you?", null, null, user2);
        privateMessage.sendPrivateMessage(messageList); // Send and display message output

        // Send a channel message
        Message channelMessage = new Message(user1, "Study hard!", studyChannel, null, null);
        channelMessage.sendChannelMessage(messageList); // Send and display message output

        // Test adding and removing users in a group chat
        ArrayList<User> groupChatMembers = new ArrayList<>();
        groupChatMembers.add(user1);
        groupChatMembers.add(user2);

        Message groupMessage = new Message(user1, "Welcome to the study group!", null, groupChatMembers, null);
        groupMessage.addUserToGroupChat(user3); // Adding user to group chat

        groupMessage.sendGroupMessage(messageList); // Send and display group message output

        groupMessage.removeUserFromGroupChat(user2); // Remove user from group chat
        System.out.println("Group chat members after removal: " + groupMessage.groupChatMembers);

        System.out.println("All messages sent: ");
        user1.messageTranscript(messageList);
    }
}
