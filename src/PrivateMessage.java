import java.util.*;

public class PrivateMessage {
    public User sentFrom;
    public String privateMessage;
    public DateTime timeSent;

    //tasks says private messages can be sent to one or more think we use a list of users for that
    ArrayList<User> gcMembers;
    User sentTo;

    public PrivateMessage(ArrayList<User> pm) {
        gcMembers = new ArrayList<User>(pm);
    }

    //adding a user to private message
    public void addUser(User a) {
        gcMembers.add(a);
    }
    
    //removing someone from groupchat
    public void removeUser(User a) {
        if (gcMembers.size() > 2 && gcMembers.contains(a) == true) {
            gcMembers.remove(a);
        }
    }

    /* public void removeUser(int idx) {
        if(idx>=0 && idx<gcMembers.size()) {
            gcMembers.remove(idx);
        }
    } */

    /*note- could add a boolean isFriend? to see if they are friends before the person can send a message to them
    or add them to a group dm */

    //group private message
    public PrivateMessage(User sentFrom, ArrayList<User> gcMembers, DateTime timeSent, String privateMessage) {
        this.privateMessage = privateMessage;
        this.sentFrom = sentFrom;
        this.gcMembers = gcMembers;
        this.timeSent = timeSent;
    }

    //private message between 2 users
    public PrivateMessage(User sentFrom, User sentTo, DateTime timeSent, String privateMessage) {
        this.privateMessage = privateMessage;
        this.sentFrom = sentFrom;
        this.sentTo = sentTo;
        this.timeSent = timeSent;
    }

    public String privMessage() {
        return sentFrom.username+" sent a private message to "+sentTo+" at "+timeSent.toString()+": "+privateMessage;
    }

    public String groupMessage() {
        return sentFrom.username+" sent a group message to "+gcMembers+ " at "+timeSent.toString()+": "+privateMessage;
    }
}
