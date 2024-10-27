
public class Subscription {
    public static void main(String[] args){
    public User user;
    public Channel channel;
    
    public Subscription(User user, Channel channel) {
        this.user = user;
        this.channel = channel;
    }

    public User getUser(){
        return user;
    }
    public Channel getChannel(){
        return channel;
    }
    public String toString(){
        return user.toString() + " has subscribed to " + channel+toString();
    }
}