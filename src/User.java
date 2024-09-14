import java.util.*;

public class User {

    long studentId;
    String email, password;
    String username;
    ArrayList<Friend> friendsList;
    ArrayList<Subscription> subscriptions;

    /* think we should remove the info we dont want shown from the paramenters
    then have separate constructors for the private info, or just make another constructor for it idk - nawal

    or idk if this would be under the profile class?

    public User(String username, and whatever else we wanna display here) {
        this.username = username;
    }*/

    public User(long studentId, String username, String email, String password) {
        this.studentId = studentId;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    //toString to return just the username of the user
    public String toString() {
        return username;
    }

    //adding subscriptions
    public void subscribeTo(Subscription s) {
        subscriptions.add(s);
    }

    //removing subscriptions
    public void unsubscribeFrom(int idx) {
        if(idx>=0 && idx<subscriptions.size()) {
            subscriptions.remove(idx);
        }
    }

    //adding friends
    public void addFriend(Friend f) {
        friendsList.add(f);
    }

    //removing friends
    public void removeFriend(int idx) {
        if(idx>=0 && idx<friendsList.size()) {
            friendsList.remove(idx);
        }
    }

    /*i think we need to set up methods for users to send messages and in here so instead of having to go 
    new Private message... we can just user.____() - not really sure tho - nawal */


    /* probably not the right way to do it - nick
    public void studentId() {
        System.out.println(studentId);
    }

    public void email() {
        System.out.println(email);
    }

    public void password() {
        System.out.println(password);
    }

    public void alias() {
        System.out.println(alias);
    }

    public void name() {
        System.out.println(name);
    }

    public void age() {
        System.out.println(age);
    }

    public void publicInfo() {
        System.out.println(alias + " | " + name + " | " + age);
    }

    public void privateInfo() {
        System.out.println(studentId + " | " + email + " | " + password);
    }
    */
}
