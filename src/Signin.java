import java.util.Scanner;
//test by dora
public class Signin {
    public String username, password;

    public Signin() {
        Scanner demoScanner = new Scanner(System.in);

        System.out.println("Enter username:");
        this.username = demoScanner.nextLine();

        System.out.println("Enter password:");
        this.password = demoScanner.nextLine();

        System.out.println("Username is: " + this.username);
        System.out.println("Password is: " + this.password);

        demoScanner.close();
    }

    public static void main(String[] args) {
        Signin signInInstance = new Signin();
        System.out.println("Sign in details for " + signInInstance.username);
    }
}
