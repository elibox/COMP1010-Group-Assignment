import java.util.*;

public class User {

    long studentId;
    String email, password;
    String username;
    ArrayList<User> friendsList;
    ArrayList<User> blockList;
    ArrayList<Subscription> subscriptions;

    /* think we should remove the info we dont want shown from the paramenters
    then have separate constructors for the private info, or just make another constructor for it idk - nawal

    or idk if this would be under the profile class?

    public User(String username, and whatever else we wanna display here) {
        this.username = username;
    }*/

    /* to do:
        - boolean for blocked users
        - unable to see blocked users' messages
     */

    public User(long studentId, String username, String email, String password) {
        this.studentId = studentId;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    //toString to return just the username of the user (remove?)
    public String toString() {
        return username;
    }

    //adding subscriptions
    public void subscribeTo(Subscription s) {
        subscriptions.add(s);
        System.out.println(this.username+" has subscribed to "+s.channel.toString());
    }

    //removing subscriptions
    public void unsubscribeFrom(int idx) {
        if(idx>=0 && idx<subscriptions.size()) {
            Subscription s = subscriptions.get(idx);
            subscriptions.remove(idx);
            System.out.println(this.username+" has unsubscribed from "+s.channel.toString());
        } else {
            System.out.println("Error: failed to unsubscribe from channel");
        }
    }

    //checking friend list (extremely scuffed)
    //to do: rework the loop into a recursive function
    public void checkFriendList(ArrayList<User> friendsList) {
         if (friendsList.size() > 0) {
            System.out.print("Friend list: ");
            for(int i = 0; i < friendsList.size(); i++) {
                System.out.print(friendsList.get(i)+" ");
            }
            System.out.println(" ");
        } else {
            System.out.println("Error: failed to check friend list");
        } 
    }

    //adding friends
    public void addFriend(User friend, ArrayList<User> friendsList) {
        if (friendsList.contains(friend) == false) {
            friendsList.add(friend);
            System.out.println(friend+" has been added to the friends list");
        } else {
            System.out.println("Error: failed to add "+friend+" to friends list");
        }
    }

    //removing friends
    public void removeFriend(User friend, ArrayList<User> friendsList) {
        if (friendsList.contains(friend) == true) {
            friendsList.remove(friend);
            System.out.println(friend+" has been removed from the friends list");
        } else {
            System.out.println("Error: failed to remove "+friend+" from friends list");
        }
    }

    //to do: checking block list

    //blocking someone
    //please rename "toBlock" if it's bad, variable names are my enemy -- claire
    public void blockUser(User toBlock, ArrayList<User> blockList) {
        if (blockList.contains(toBlock) == false) {
            blockList.add(toBlock);
            System.out.println(toBlock+" has been blocked");
        } else {
            System.out.println("Error: failed to block "+toBlock);
        }
    }

    //unblocking someone
    public void unblockUser(User toUnblock, ArrayList<User> blockList) {
        if (blockList.contains(toUnblock) == true) {
            blockList.remove(toUnblock);
            System.out.println(toUnblock+" has been unblocked");
        }  else {
            System.out.println("Error: failed to unblock "+toUnblock);
        }
    }

    /*i think we need to set up methods for users to send messages and in here so instead of having to go 
    new Private message... we can just user.____() - not really sure tho - nawal */

}
