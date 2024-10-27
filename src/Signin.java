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
        return signInRecursive(users, 0);
    }
    
    private User signInRecursive(ArrayList<User> users, int index) {
        // Base case: if index is equal to the size of the users list, return null
        if (index >= users.size()) {
            return null;
        }
    
        User user = users.get(index);
        // Check if the current user's credentials match
        if (user.username.equals(this.username) && user.password.equals(this.password)) {
            return user; // Return the matched user
        }
    
        // Recursive case: check the next user
        return signInRecursive(users, index + 1);
    }
}

