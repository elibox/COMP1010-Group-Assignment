import java.util.*;

public class Channel {
    public String topic, name;
    ArrayList<Message> messages;

    public Channel(ArrayList<Message> channelMessages) {
        messages = new ArrayList<Message>(channelMessages);
    }

    public Channel(String topic, String name) {
        this.topic = topic;
        this.name = name;
    }

    //toString to print channel topic/name
    public String toString() {
        return topic+" - "+name;
    }

    //adding a message to channel? 
    public void add(Message m) {
        messages.add(m);
    }

    //deleting message from a channel
    public void deleteMessage(int idx) {
        if(idx>=0 && idx<messages.size()) {
            messages.remove(idx);
        }
    }
}
