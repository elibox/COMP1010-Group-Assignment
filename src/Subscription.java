public class Subscription {
    public User user;
    public Channel channel;
    
    public Subscription(User user, Channel channel) {
        this.user = user;
        this.channel = channel;
    }

    public Subscription(Channel channel) {
        this.channel = channel;
    }

    public String toString() {
        if(channel != null) {
            return channel.name;
        }
        return "";
    }
}
