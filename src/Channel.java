import java.util.*;

public class Channel {
    public String topic, name;
    ArrayList<Message> messages;

    public Channel(ArrayList<Message> channelMessages) {
        messages = new ArrayList<Message>(channelMessages);
    }

    //adding to list
    public void add(Message m) {
        messages.add(m);
    }

    //removing from specific index of list - idk if we really need this
    public void remove(int idx) {
        if(idx>=0 && idx<messages.size()) {
            messages.remove(idx);
        }
    }
}
