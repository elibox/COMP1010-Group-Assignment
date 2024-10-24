import java.util.*;

public class Message {
    public User sentFrom, sentTo;
    public Channel channel;
    public String message;
    public Date date;
    public Time time;
    public ArrayList<User> groupChatMembers;

    //channel Message
    public Message(Channel channel, User sentFrom, String message, Date date, Time time) {
        this.channel = channel;
        this.sentFrom = sentFrom;
        this.message = message;
        this.date = date;
        this.time = time; 
        //System.out.println(channel+" | "+sentFrom+" | "+dateTimeToString()+": "+message);
    }

    //private message
    public Message(User sentFrom, User sentTo, Date date, Time time, String message) {
        this.message = message;
        this.sentFrom = sentFrom;
        this.sentTo = sentTo;
        this.date = date;
        this.time = time;
    }

    //group message
    //maybe group chats can have names? just an idea -- claire
    public Message(User sentFrom, ArrayList<User> groupChatMembers, Date date, Time time, String message) {
        this.message = message;
        this.sentFrom = sentFrom;
        this.groupChatMembers = groupChatMembers;
        this.date = date;
        this.time = time;
    }

    //displaying channel messages
    public String channelMessage() {
        return channel+" | "+sentFrom.username+" | "+dateTimeToString()+": "+message;
    }


    //displaying private messages
    public String privMessage() {
        return sentFrom.username+" sent a private message to "+sentTo.username+" at "+dateTimeToString()+": "+message;
    }

    //displaying group messages
    public String groupMessage() {
        return sentFrom.username+" sent a group message to "+getGroupChatMemberNames()+ " at "+dateTimeToString()+": "+message;
    }

    //i think we might be able to remove this? coz i dont think its being used idk
    public Message(ArrayList<User> privateMessage) {
        groupChatMembers = new ArrayList<User>(privateMessage);
    }

    //display groupchat members
    public String getGroupChatMemberNames() {
        String memberNames = "";
        for(int i=0; i<groupChatMembers.size(); i++) {
            if(i > 0) {
                memberNames += ", ";
            }
            memberNames += groupChatMembers.get(i).username;
        }
        return memberNames;
    }

    //adding a user to a group chat
    public void addUser(User user) {
        if(!groupChatMembers.contains(user)) {
            groupChatMembers.add(user);
            System.out.println(user.username+" was added to the group chat "+getGroupChatMemberNames()+" at "+dateTimeToString());
        } else {
            System.out.println("Error: "+user.username+" is already in the group chat");
        }
    }
    //note i think we can remove +groupChatMembers+ coz it'll just list the gc memebrs n it might look messy i think tis better just have the added to gc message - nawal
    
    //removing a user from gc
    public void removeUser(User user) {
        if (groupChatMembers.contains(user) && groupChatMembers.size()>2) {
            groupChatMembers.remove(user);
            System.out.println(user.username+" was removed from the group chat "+getGroupChatMemberNames()+" at "+dateTimeToString());
        } else {
            //remove if deemed necessary, thought it'd help with debugging
            System.out.println("Error: failed to remove "+user.username+" from group chat");
        }
    }
    
    //helper string for datetime
    //i might rework this later, i feel like i'm gonna run into issues with this - claire
    public String dateTimeToString() {
        return date.toString()+", "+time.toString();
    }
}
