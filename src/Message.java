import java.util.*;

public class Message {
    public User sentFrom, sentTo;
    public Channel channel;
    public String message;
    public Date date;
    public Time time;
    public ArrayList<User> groupChatMembers;

    //i think having 3 constructors is a bit pointless - nawal
    public Message(User sentFrom, String message, Date date, Time time, Channel channel, ArrayList<User> groupChatMembers, User sentTo) {
        this.sentFrom = sentFrom;
        this.message = message;
        this.date = date;
        this.time = time;
        this.channel = channel;
        this.groupChatMembers = groupChatMembers;
        this.sentTo = sentTo;
    }

    //sending channel messages
    public void sendChannelMessage() {

    }

    //sending private messages
    public void sendPrivateMessage() {

    }

    //sending group messages
    public void sendGroupMessage() {

    }

    //deleting messages
    public void deleteMessge() {

    }

    //add message to list of messages
    public void addMessage() {

    }

    //remove messages from the list of messages
    public void removeMessage() {
        
    }

    //displaying channel messages
    public String displayChannelMessage() {
        return channel+" | "+sentFrom.username+" | "+dateTimeToString()+": "+message;
    }

    //displaying private messages
    public String displayPrivMessage() {
        return sentFrom.username+" sent a private message to "+sentTo.username+" at "+dateTimeToString()+": "+message;
    }

    //displaying group messages
    public String displayGroupMessage() {
        return sentFrom.username+" sent a group message to "+getGroupChatMemberNames()+ " at "+dateTimeToString()+": "+message;
    }

    //display names of all groupchat members
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

    //adding user to a group chat
    public void addUserToGroupChat(User user) {
        if(!groupChatMembers.contains(user)) {
            groupChatMembers.add(user);
            System.out.println(user.username+" was added to the group chat with: "+getGroupChatMemberNames()+" at "+dateTimeToString());
        } else {
            System.out.println("Error: "+user.username+" is already in the group chat");
        }
    }
    
    //removing user from a group chat
    public void removeUserFromGroupChat(User user) {
        if (groupChatMembers.contains(user) && groupChatMembers.size()>2) {
            groupChatMembers.remove(user);
            System.out.println(user.username+" was removed from the group chat with: "+getGroupChatMemberNames()+" at "+dateTimeToString());
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

        //channel Message (possibly remove)
    public Message(Channel channel, User sentFrom, String message, Date date, Time time) {
        this.channel = channel;
        this.sentFrom = sentFrom;
        this.message = message;
        this.date = date;
        this.time = time; 
        //System.out.println(channel+" | "+sentFrom+" | "+dateTimeToString()+": "+message);
    }

    //private message (possibly remove)
    public Message(User sentFrom, User sentTo, Date date, Time time, String message) {
        this.message = message;
        this.sentFrom = sentFrom;
        this.sentTo = sentTo;
        this.date = date;
        this.time = time;
    }

    //group message (possibly remove)
    //maybe group chats can have names? just an idea -- claire
    public Message(User sentFrom, ArrayList<User> groupChatMembers, Date date, Time time, String message) {
        this.message = message;
        this.sentFrom = sentFrom;
        this.groupChatMembers = groupChatMembers;
        this.date = date;
        this.time = time;
    }

    
}
