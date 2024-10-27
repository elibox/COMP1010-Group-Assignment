import java.util.*;

public class User {
    public long studentId;
    public String email, password, username;
    public ArrayList<User> friendsList;
    public ArrayList<User> blockList;
    public ArrayList<Subscription> subscriptions;

    public User(long studentId, String username, String email, String password) {
        this.studentId = studentId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.friendsList = new ArrayList<>();
        this.blockList = new ArrayList<>();
        this.subscriptions = new ArrayList<>();
    }

    public String toString() {
        return username;
    }

    //adding subscriptions
    public void subscribeToChannel(Channel channel) {
        //checking if user is subscribed to channel already
        for(int i=0; i<subscriptions.size(); i++) {
            Subscription subscription = subscriptions.get(i);
            if(subscription.channel == channel) {
                System.out.println("Error: "+this.username+" is already subscrobe to "+channel.toString());
                return;
            }
        }
        Subscription newSubscription = new Subscription(null, channel);
        subscriptions.add(newSubscription);
        System.out.println(this.username+" has subscribed to "+ channel.toString());
    }

    //removing subscriptions
    public void unsubscribeFromChannel(int idx) {
        if(idx>=0 && idx<subscriptions.size()) {
            Subscription subscription = subscriptions.get(idx);
            subscriptions.remove(idx);
            System.out.println(this.username+" has unsubscribed from "+subscription.channel);
        } else {
            System.out.println("Error: failed to unsubscribe from channel");
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
        } else {
            System.out.println("Error: no channel's have been subscribed to");
        }
    }

    //adding friends
    public void addFriend(User friend) {
        if (!friendsList.contains(friend)) {
            friendsList.add(friend);
            System.out.println(friend+" has been added to the friends list");
        } else {
            System.out.println("Error: failed to add "+friend+" to friends list");
        }
    }

    //removing friends
    public void removeFriend(User friend) {
        if (friendsList.contains(friend)) {
            friendsList.remove(friend);
            System.out.println(friend+" has been removed from the friends list");
        } else {
            System.out.println("Error: failed to remove "+friend+" from friends list");
        }
    }

    //displaying friend list (extremely scuffed)
    public void displayFriendsList() {
        if(!friendsList.isEmpty()) {
            System.out.print("Friend List: ");
            for(int i=0; i<friendsList.size(); i++) {
                System.out.print(friendsList.get(i)+" ");
            }
            System.out.println();
        } else {
            System.out.println("Error: No users have been added");
        }
    }

    // Define a recursive data structure for a linked list of friends
    /*someone said to convert this into a recursive function so i did it - dora
    commenting it out for now coz this causes issues in the client code for some reason
class FriendNode {
    String username;
    FriendNode next;

    public FriendNode(String username, FriendNode next) {
        this.username = username;
        this.next = next;
    }

    //recursive method to display the friends list
    public void displayFriendsList() {
        // Print the current friend's name
        System.out.print(this.username + " ");
        //displaying the next friend
        if (this.next != null) {
            this.next.displayFriendsList();
        }
    }

    //helper method
    public static void display(FriendNode head) {
        if (head == null) {
            System.out.println("Error: No users have been added");
        } else {
            System.out.print("Friend List: ");
            head.displayFriendsList();
            System.out.println(); 
        }
    }
    */

    //blocking someone
    public void blockUser(User toBlock, ArrayList<User> blockList) {
        if (!blockList.contains(toBlock)) {
            blockList.add(toBlock);
            System.out.println(toBlock+" has been blocked");
        } else {
            System.out.println("Error: failed to block "+toBlock);
        }
    }

    //unblocking someone
    public void unblockUser(User toUnblock, ArrayList<User> blockList) {
        if (blockList.contains(toUnblock)) {
            blockList.remove(toUnblock);
            System.out.println(toUnblock+" has been unblocked");
        }  else {
            System.out.println("Error: failed to unblock "+toUnblock);
        }
    }

    //displaying block list
    public void displayBlockList() {
        if(!blockList.isEmpty()) {
            System.out.print("Blocked users: ");
            for(int i=0; i<blockList.size(); i++) {
                System.out.print(blockList.get(i)+" ");
            }
            System.out.println();
        } else {
            System.out.println("Error: no users have been blocked");
        }
    }


     /* Define a recursive data structure for linked list of blocked users
class BlockedUserNode {
    String username;
    BlockedUserNode next;

    public BlockedUserNode(String username, BlockedUserNode next) {
        this.username = username;
        this.next = next;
    }

    //recursive method displaying the block list
    public void displayBlockList() {
        //print the current blocked user's name
        System.out.print(this.username + " ");
        
        // If there is a next blocked user, display it recursively
        if (this.next != null) {
            this.next.displayBlockList();
        }
    }

    //helper method
    public static void display(BlockedUserNode head) {
        if (head == null) {
            System.out.println("Error: no users have been blocked");
        } else {
            System.out.print("Blocked users: ");
            head.displayBlockList();
            System.out.println();
        }
    }*/


    //checker to see if user is blocked
    public boolean isBlockedBy(User other) {
        return blockList.contains(other) || other.blockList.contains(this);
    }
}
