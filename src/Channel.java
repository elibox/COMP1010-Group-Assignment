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

    //toString to print channel topic-name
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

  //recursive data structure for linked list of messages
class MessageNode {
    Message message;
    MessageNode next;

    public MessageNode(Message message, MessageNode next) {
        this.message = message;
        this.next = next;
    }

    //recursive method to find all messages from a specific user
    public ArrayList<Message> findMessageByUser(User user) {
        ArrayList<Message> userMessages = new ArrayList<>();

        //if the current message is from the specified user, add it to the list
        if (this.message.sender == user) {
            userMessages.add(this.message);
        }

        //if there is a next node, recursively find messages in the rest of the list
        if (this.next != null) {
            userMessages.addAll(this.next.findMessageByUser(user));
        }

        return userMessages;
    }
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
