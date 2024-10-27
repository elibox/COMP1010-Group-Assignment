
public class Subscription {
    
    public User user;
    public Channel channel;
    
    public Subscription(User user, Channel channel) {
        this.user = user;
        this.channel = channel;
    }

    public User getUser() {
        return user;
    }
    public Channel getChannel() {
        return channel;
    }
    public String toString() {
        if(channel != null) {
            return channel.name;
        } else {
            return "";
        }
    }
}
