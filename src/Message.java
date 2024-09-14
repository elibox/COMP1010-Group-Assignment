public class Message {
    public Channel channel;
    public User sender;
    public String message;
    public DateTime timeSent;

    public Message(Channel channel, User sender, String message, DateTime timeSent) {
        this.channel = channel;
        this.sender = sender;
        this.message = message;
        this.timeSent = timeSent;
    }


    public String channelMessage() {
        return channel+" | "+sender+" | "+timeSent.toString()+": "+message;
    }
}
