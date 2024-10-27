public class BlockNode {
    public User blockedUser;
    public BlockNode next;

    public BlockNode(User blockedUser) {
        this.blockedUser = blockedUser;
        this.next = null;
    }
}
