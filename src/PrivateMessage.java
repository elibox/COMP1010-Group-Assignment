import java.util.*;

public class PrivateMessage {
    public User sentFrom;
    public String privateMessage;
    public DateTime timeSent;
    //tasks says private messages can be sent to one or more think we use a list of users for that
    ArrayList<User> sentTo;
    User receiver;

    public PrivateMessage(ArrayList<User> pm) {
        sentTo = new ArrayList<User>(pm);
    }

    //adding a user to private message
    public void addUser(User a) {
        sentTo.add(a);
    }
    
    //removing someone from groupchat
    public void removeUser(int idx) {
        if(idx>=0 && idx<sentTo.size()) {
            sentTo.remove(idx);
        }
    }

    public PrivateMessage(User sentFrom, ArrayList<User> sentTo, DateTime timeSent, String privateMessage) {
        this.privateMessage = privateMessage;
        this.sentFrom = sentFrom;
        this.sentTo = sentTo;
        this.timeSent = timeSent;
    }

    public PrivateMessage(User sentFrom, User receiver, DateTime timeSent, String privateMessage) {
        this.privateMessage = privateMessage;
        this.sentFrom = sentFrom;
        this.receiver = receiver;
        this.timeSent = timeSent;
    }

    public String privMessage() {
        return sentFrom.username+" sent a private message to "+receiver +" at "+timeSent.toString()+": "+privateMessage;
    }

    public String groupMessage() {
        return sentFrom.username+" sent a group message to "+sentTo+ " at "+timeSent.toString()+": "+privateMessage;
    }
}
