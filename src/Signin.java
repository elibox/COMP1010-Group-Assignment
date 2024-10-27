import java.util.Scanner;
import java.util.*;

public class Signin {
    public String username, password;

    public Signin(ArrayList<User> users) {
        Scanner demoScanner = new Scanner(System.in);

        System.out.println("Enter username:");
        this.username = demoScanner.nextLine();

        System.out.println("Enter password:");
        this.password = demoScanner.nextLine();

        User signedInUser = signIn(users);
        if(signedInUser != null) {
            System.out.println("Welcome back, "+signedInUser.username+"!");
        } else {
            System.out.println("Error: login failed.");
        }

        demoScanner.close();
    }

    //checking if username and password signed in with belong to a user
    public User signIn(ArrayList<User> users) {
        for(int i=0; i<users.size(); i++) {
            User user = users.get(i);
            if(user.username == this.username && user.password == this.password) {
                return user;
            }
        }
        return null;
    }
}

