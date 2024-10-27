import java.util.*;

public class Message {
    public User sender, recipient;
    public Channel channel;
    public String message;
    public Date date;
    public Time time;
    public ArrayList<User> groupChatMembers;

    //consctuctor
    public Message(User sender, String messageContents, Channel channel, ArrayList<User> groupChatMembers, User recipient) {
        this.sender = sender;
        this.message = messageContents;
        this.channel = channel;
        this.groupChatMembers = groupChatMembers;
        this.recipient = recipient;
        //current date/time
        this.date = new Date();
        this.time = new Time();
    }

    //sending channel messages
    public void sendChannelMessage(ArrayList<Message> messages) {
        if(channel != null) {
            addMessage(messages, this);
            System.out.println(displayChannelMessage());
        } else {
            System.out.println("Error: failed to send message as channel does not exist");
        }
    }

    //sending private messages
    public void sendPrivateMessage(ArrayList<Message> messages) {
        if(recipient != null) {
            if(sender.isBlockedBy(recipient)) {
                System.out.println("Error: cannot send private message to "+recipient.username+" because you have been blocked or you have blocked them");
            } else { 
                addMessage(messages,this);
                System.out.println(displayPrivateMessage()); 
            }
        } else {
            System.out.println("Error: failed to send message as this user does");
        }
    }

    //sending group messages
    public void sendGroupMessage(ArrayList<Message> messages) {
        if(groupChatMembers != null && groupChatMembers.size() > 1) {
            addMessage(messages, this);
            System.out.println(displayGroupMessage());
        } else {
            System.out.println("Error: group chat must have more than one member to send a message");
        }
    }

    //add message to list of messages
    public void addMessage(ArrayList<Message> messages, Message message) {
        messages.add(message);
    }

    //deleting messages
    public void deleteMessage(ArrayList<Message> messages) {
        removeMessage(messages, this);
        System.out.println("Message has been deleted");
    }

    //remove messages (helper to delete messages)
    public void removeMessage(ArrayList<Message> messages, Message message) {
        if(messages.contains(message)) {
            messages.remove(message);
        }
    }

    //clear all messages
    public void clearMessages(ArrayList<Message> messages) {
        messages.clear();
    }

    //string to help display channel messages
    public String displayChannelMessage() {
        return "["+channel.toString()+"] "+sender.username+" at "+dateTimeToString()+": "+message;
    }

    //string to help display private messages
    public String displayPrivateMessage() {
        return sender.username+" sent a private message to "+recipient.username+" at "+dateTimeToString()+": "+message;
    }

    //string to help display group messages
    public String displayGroupMessage() {
        return sender.username+" sent a group message to ["+getGroupChatMemberNames()+"] at "+dateTimeToString()+": "+message;
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


  /*recursive data structure displaying names of all groupchat members
class UserNode {
    User user;
    UserNode next;

    public UserNode(User user, UserNode next) {
        this.user = user;
        this.next = next;
    }

    //recursive method to get the group chat member names
    public String getGroupChatMemberNames() {
        //getting the current user's name
        String memberNames = this.user.username;

        //if there is a next node, get the names recursively and append them
        if (this.next != null) {
            memberNames += ", " + this.next.getGroupChatMemberNames();
        }

        return memberNames;
    }
}*/
    //adding user to a group chat
    public void addUserToGroupChat(User user) {
        if(!groupChatMembers.contains(user)) {
            groupChatMembers.add(user);
            System.out.println(user.username+" was added to the group chat with ["+getGroupChatMemberNames()+"] at "+dateTimeToString());
        } else {
            System.out.println("Error: "+user.username+" is already in the group chat");
        }
    }
    
    //removing user from a group chat
    public void removeUserFromGroupChat(User user) {
        if (groupChatMembers.contains(user) && groupChatMembers.size() > 2) {
            groupChatMembers.remove(user);
            System.out.println(user.username+" was removed from the group chat with: "+getGroupChatMemberNames()+" at "+dateTimeToString());
        } else {
            System.out.println("Error: failed to remove "+user.username+" from group chat");
        }
    }
    
    //helper string for datetime
    public String dateTimeToString() {
        return date.toString()+", "+time.toString();
    }

    //helper string to manage displaying of the different message types
    public String toString() {
        if(channel != null) {
            return displayChannelMessage();
        }
        if(recipient != null) {
            return displayPrivateMessage();
        }
        if(groupChatMembers != null && groupChatMembers.size() > 1) {
            return displayGroupMessage();
        }
        return "Error: message is invalid.";
    }
}
