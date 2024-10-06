import java.util.*;

public class Message {
    public User sentFrom, sentTo;
    
    public Channel channel;
    public String message;
    public Date date;
    public Time time;

    public ArrayList<User> gcMembers;


    public Message(ArrayList<User> pm) {
        gcMembers = new ArrayList<User>(pm);
    }

    //i might rework this later, i feel like i'm gonna run into issues with this - claire
    public String dateTimeToString() {
        return date.toString()+", "+time.toString();
    }


    //channel message
    public Message(Channel channel, User sentFrom, String message, Date date, Time time) {
        this.channel = channel;
        this.sentFrom = sentFrom;
        this.message = message;
        this.date = date;
        this.time = time;

        //System.out.println(channel+" | "+sentFrom+" | "+dateTimeToString()+": "+message);
    }

    public String channelMessage() {
        return channel+" | "+sentFrom+" | "+dateTimeToString()+": "+message;
    }


    //group private message
    //maybe group chats can have names? just an idea -- claire
    public Message(User sentFrom, ArrayList<User> gcMembers, Date date, Time time, String message) {
        this.message = message;
        this.sentFrom = sentFrom;
        this.gcMembers = gcMembers;
        this.date = date;
        this.time = time;
    }

    public String groupMessage() {
        return sentFrom.username+" sent a group message to "+gcMembers+ " at "+dateTimeToString()+": "+message;
    }


    //adding a user to gc
    public void addUser(User a) {
        gcMembers.add(a);
        System.out.println(a+" was added to the group chat "+gcMembers+" at "+dateTimeToString());
    }

    
    //removing a user from gc
    public void removeUser(User a) {
        if (gcMembers.size() > 2 && gcMembers.contains(a) == true) {
            gcMembers.remove(a);
            System.out.println(a+" was removed from the group chat "+gcMembers+" at "+dateTimeToString());
        } else {
            //remove if deemed necessary, thought it'd help with debugging
            System.out.println("Error: failed to remove "+a+" from group chat");
        }
    }


    //private message between 2 users
    public Message(User sentFrom, User sentTo, Date date, Time time, String message) {
        this.message = message;
        this.sentFrom = sentFrom;
        this.sentTo = sentTo;
        this.date = date;
        this.time = time;
    }

    public String privMessage() {
        return sentFrom.username+" sent a private message to "+sentTo+" at "+dateTimeToString()+": "+message;
    }
}
