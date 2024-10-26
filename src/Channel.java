import java.util.*;

public class Channel {
    public String topic, name;
    public ArrayList<Message> messages;

    public Channel(ArrayList<Message> channelMessages) {
        this.messages = new ArrayList<Message>(channelMessages);
    }

    public Channel(String topic, String name) {
        this.topic = topic;
        this.name = name;
        this.messages = new ArrayList<>();
    }

    //toString to print channel topic/name (can be deleted?)
    public String toString() {
        return topic+" - "+name;
    }

    //adding a message to channel
    public void add(Message message) {
        messages.add(message);
    }

    //deleting message from a channel
    public void deleteMessage(int idx) {
        if(idx>=0 && idx<messages.size()) {
            messages.remove(idx);
        }
    }

    //find all message from specific user
    public ArrayList<Message> findMessageByUser(User user) {
        ArrayList<Message> userMessages = new ArrayList<>();
        for(int i=0; i<messages.size(); i++) {
            if(messages.get(i).sender == user) {
                userMessages.add(messages.get(i));
            }
        }
        return userMessages;  
    }

    //find messages about topic
    public String getTopic() {
        return topic;
    }

    //check if a message has already been sent in a channel
    public boolean messageExists(Message message) {
        return messages.contains(message);
    }

    //get all messages in a channel
    public ArrayList<Message> getAllMessages() {
        return messages;
    }

    //display all messages
    public void displayAllMessages() {
        for(int i=0; i<messages.size(); i++) {
            System.out.println(messages.get(i).displayChannelMessage());
        }
    }
}
