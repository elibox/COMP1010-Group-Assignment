public class Subscription {
    public User user;
    public Channel channel;
    
    public Subscription(User user, Channel channel) {
        this.user = user;
        this.channel = channel;
    }
    
    /*message for when user subscribed to channel? or idk if this could just be a printline 
    under a subscribeTo method in user?

    public String toString() {
        return(user.toString()+"has subscribed to"+channel.toString());
    }*/

}