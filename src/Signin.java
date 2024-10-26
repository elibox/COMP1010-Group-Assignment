import java.util.Scanner;

public class Signin {
    public String username, password;

    public Signin() {
        Scanner demoScanner = new Scanner(System.in);

        System.out.println("Enter username:");
        this.username = demoScanner.nextLine();

        System.out.println("Enter password:");
        this.password = demoScanner.nextLine();

        demoScanner.close();
    }
}
