import java.util.Scanner;

public class Signin {
    Scanner demoScanner = new Scanner(System.in);
    System.out.println("Enter username");

    String username = demoScanner.nextLine();
    System.out.println("Username is: " + username);

    demoScanner.close();
}
