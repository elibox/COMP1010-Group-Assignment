import java.util.*;

public class User {
    public long studentId;
    public String email, password, username;
    public ArrayList<User> friendsList;
    public ArrayList<User> blockList;
    public ArrayList<Subscription> subscriptions;

    /* to do:
        - boolean for blocked users
        - unable to see blocked users' messages
     */

    public User(long studentId, String username, String email, String password) {
        this.studentId = studentId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.friendsList = new ArrayList<>();
        this.blockList = new ArrayList<>();
        this.subscriptions = new ArrayList<>();

    }

    //toString to return just the username of the user (remove?)
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
        Subscription newSubscription = new Subscription(channel);
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
    //to do: rework the loop into a recursive function
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

    //checker to see if user is blocked
    public boolean isBlockedBy(User other) {
        return blockList.contains(other) || other.blockList.contains(this);
    }
}
