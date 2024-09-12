import java.util.*;

public class User {
    /*ArrayList<Integer> studentID = ArrayList<>(); //8 characters
    public ArrayList<String> alias, pronouns;  
    password reqs, 1 uppercase letter + 1 special character, min 8 char
    no repeating aliass
    info stored for login?*/
    
    long studentId;
    String email, password;
    String username;
    ArrayList<Friend> friendsList;
    ArrayList<Subscription> subscriptions;


    /* think we should remove the info we dont want shown from the paramenters
    then have separate constructors for the private info // or just make another constructor for it idk - nawal
    public User(String username, and whatever else we wanna display here) {
        this.username = username;
    }*/


    public User(long studentId, String username, String email, String password) {
        this.studentId = studentId;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String toString() {
        return username;
    }

    /* probably not the right way to do it - nick
    public void studentId() {
        System.out.println(studentId);
    }

    public void email() {
        System.out.println(email);
    }

    public void password() {
        System.out.println(password);
    }

    public void alias() {
        System.out.println(alias);
    }

    public void name() {
        System.out.println(name);
    }

    public void age() {
        System.out.println(age);
    }

    public void publicInfo() {
        System.out.println(alias + " | " + name + " | " + age);
    }

    public void privateInfo() {
        System.out.println(studentId + " | " + email + " | " + password);
    }
    */
}
