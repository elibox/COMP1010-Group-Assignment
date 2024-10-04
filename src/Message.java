import java.util.*;

public class Message {
    public User sentFrom;
    public User sentTo;

    public Channel channel;
    public String message;
    public DateTime timeSent;

    public ArrayList<User> gcMembers;


    public Message(ArrayList<User> pm) {
        gcMembers = new ArrayList<User>(pm);
    }


    //channel message
    public Message(Channel channel, User sentFrom, String message, DateTime timeSent) {
        this.channel = channel;
        this.sentFrom = sentFrom;
        this.message = message;
        this.timeSent = timeSent;
    }

    public String channelMessage() {
        return channel+" | "+sentFrom+" | "+timeSent.toString()+": "+message;
    }


    //group private message
    //maybe group chats can have names? just an idea -- claire
    public Message(User sentFrom, ArrayList<User> gcMembers, DateTime timeSent, String message) {
        this.message = message;
        this.sentFrom = sentFrom;
        this.gcMembers = gcMembers;
        this.timeSent = timeSent;
    }

    public String groupMessage() {
        return sentFrom.username+" sent a group message to "+gcMembers+ " at "+timeSent.toString()+": "+message;
    }


    //adding a user to gc
    public void addUser(User a) {
        gcMembers.add(a);
        System.out.println(a+" was added to the group chat "+gcMembers+" at "+timeSent.toString());
    }

    
    //removing a user from gc
    public void removeUser(User a) {
        if (gcMembers.size() > 2 && gcMembers.contains(a) == true) {
            gcMembers.remove(a);
            System.out.println(a+" was removed from the group chat "+gcMembers+" at "+timeSent.toString());
        } else {
            //remove if deemed necessary, thought it'd help with debugging
            System.out.println("Error: failed to remove "+a);
        }
    }


    //private message between 2 users
    public Message(User sentFrom, User sentTo, DateTime timeSent, String message) {
        this.message = message;
        this.sentFrom = sentFrom;
        this.sentTo = sentTo;
        this.timeSent = timeSent;
    }

    public String privMessage() {
        return sentFrom.username+" sent a private message to "+sentTo+" at "+timeSent.toString()+": "+message;
    }
}
