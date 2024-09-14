public class Profile {
    public User user;
    public String bio, pronouns;
    public DateTime dateJoined;

    public Profile(User user, String bio, String pronouns, DateTime dateJoined) {
        this.user = user;
        this.bio = bio;
        this.pronouns = pronouns;
        this.dateJoined = dateJoined;
    }

    public String printProfile() {
        return user+" | "+pronouns+" | "+dateJoined+" | "+bio;
    }
}
