import java.util.*;

public class User {
    public long studentId;
    public String email, password, username;
    public FriendNode friendsList;
    public BlockNode blockList;
    public ArrayList<Subscription> subscriptions;

    public User(long studentId, String username, String email, String password) {
        this.studentId = studentId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.friendsList = null;
        this.blockList = null;
        this.subscriptions = new ArrayList<>();
    }

    //toString to return username
    public String toString() {
        return username;
    }

    //method to find a specific user from a list of users
    public static User findUserByUsername(ArrayList<User> users, String username) {
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            if (user.username == username) {
                return user;
            }
        }
        return null;
    }

    //adding subscriptions
    public void subscribeToChannel(Channel channel) {
        //checking if user is subscribed to channel already
        for(int i=0; i<subscriptions.size(); i++) {
            Subscription subscription = subscriptions.get(i);
            if(subscription.channel == channel) {
                System.out.println("Error: "+this.username+" is already subscribed to "+channel.toString()+".");
                return;
            }
        }
        Subscription newSubscription = new Subscription(null, channel);
        subscriptions.add(newSubscription);
        System.out.println(this.username+" has subscribed to "+ channel.toString()+".");
    }

    //removing subscriptions
    public String unsubscribeFromChannel(int idx) {
        if(subscriptions.isEmpty()) {
            System.out.println("Error: no channels have been subscribed to.");
            return null;
        }
        if(idx>=0 && idx<subscriptions.size()) {
            Subscription subscription = subscriptions.get(idx);
            subscriptions.remove(idx);
            System.out.println(this.username+" has unsubscribed from "+subscription.channel+".");
            return subscription.getChannel().name;
        } else {
            System.out.println("Error: failed to unsubscribe from channel.");
            return null;
        }
    }

    //displaying subscriptions
    public void displayChannelSubscriptions() {
        if(!subscriptions.isEmpty()) {
            System.out.print("Subscriptions: ");
            for(int i=0; i<subscriptions.size(); i++) {
                System.out.print(subscriptions.get(i)+" ");
            }
            System.out.println();
        }
    }

    //recursive method - adding friends
    public void addFriend(User newFriend) {
        friendsList = addFriendHelper(friendsList, newFriend);
    }
    //helper method
    public FriendNode addFriendHelper(FriendNode node, User newFriend) {
        if(node == null) {
            System.out.println(newFriend+" has been added to the friends list.");
            return new FriendNode(newFriend);
        }
        if(node.friend == newFriend) {
            System.out.println("Error: "+newFriend+" is already added.");
            return node;
        } else {
            node.next = addFriendHelper(node.next, newFriend);
            return node;
        }
    }

    //recursive method - removing friends
    public void removeFriend(User friendToRemove) {
        friendsList = removeFriendHelper(friendsList, friendToRemove);
    }
    //helper method
    public FriendNode removeFriendHelper(FriendNode node, User friendToRemove) {
        if(node == null) {
            System.out.println("Error: "+friendToRemove+" is not in friends lists.");
            return null;
        }
        if(node.friend == friendToRemove) {
            System.out.println(friendToRemove+" has been removed as a friend.");
            return node.next;
        } else {
            node.next = removeFriendHelper(node.next, friendToRemove);
            return node;
        }
    }

    //recursive method - displaying friends 
    public void displayFriendsList() {
        if(friendsList == null) {
            System.out.println("Error: no users have been added.");
        } else {
            System.out.println(("Friends List: "));
            displayFriendsListHelper(friendsList);
            System.out.println();
        }
    }
    //helper method 
    public void displayFriendsListHelper(FriendNode node) {
        if(node != null) {
            System.out.print(node.friend+" ");
            displayFriendsListHelper(node.next);
        }
    }

    //recursive method - blocking someone
    public void blockUser(User toBlock) {
        blockList = blockUserHelper(blockList, toBlock);
    }
    //helper
    public BlockNode blockUserHelper(BlockNode node, User toBlock) {
        if(node == null) {
            System.out.println(toBlock+" has been blocked.");
            return new BlockNode(toBlock);
        }
        if(node.blockedUser == toBlock) {
            System.out.println("Error: "+toBlock+" has already been blocked.");
            return node;
        } else {
            node.next = blockUserHelper(node.next, toBlock);
            return node;
        }
    }

    //recursive method - unblocking someone
    public void unblockUser(User toUnblock) {
        BlockNode newBlockList =  unblockUserHelper(blockList, toUnblock);
        if(newBlockList != blockList) {
            blockList = newBlockList;
            System.out.println(toUnblock+" has been unblocked.");
        } else {
            System.out.println("Error: failed to unblock "+toUnblock+".");
        }
    }
    //helper method
    public BlockNode unblockUserHelper(BlockNode node, User toUnblock) {
        if(node == null) {
            return null;
        }
        if(node.blockedUser == toUnblock) {
            return node.next;
        } else {
            node.next = unblockUserHelper(node.next, toUnblock);
            return node;
        }
    }

    //recursive method - displaying block list
    public void displayBlockList() {
        if(blockList == null) {
            System.out.println("Error: no uses have been blocked.");
        } else {
            System.out.print("Blocked users: ");
            displayBlockListHelper(blockList);
        }
    }
    //helper method
    public void displayBlockListHelper(BlockNode node) {
        if(node != null) {
            System.out.println(node.blockedUser+" ");
            displayBlockListHelper(node.next);
        }
    }

    //recursive method - checker to see if user is blocked
    public boolean isBlockedBy(User other) {
        return isBlockedByHelper(other.blockList) || other.isBlockedByHelper(this.blockList);
    }
    //helper method
    public boolean isBlockedByHelper(BlockNode node) {
        if(node == null) {
            return false;
        }
        if(node.blockedUser == this) {
            return true;
        }
        else {
            return isBlockedByHelper(node.next);
        }
    }
    
    //displaying message transcript
    public void messageTranscript(ArrayList<Message> messages) {
        for(int i=0; i<messages.size(); i++) {
            System.out.println(messages.get(i).toString());
        }
    }
}
