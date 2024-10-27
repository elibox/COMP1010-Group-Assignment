public class SubscriptionNode {
    public Subscription subscription;
    public SubscriptionNode next;

    public SubscriptionNode(Subscription subscription) {
        this.subscription = subscription;
        this.next = null;
    }

    //recursive method to display all subscriptions
    public void displaySubscriptions() {
        System.out.print(subscription+" ");
        if(next != null) {
            next.displaySubscriptions();
        }
    }
}
