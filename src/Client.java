
import java.util.*;

public class Client {
    public static void main(String[] args) throws Exception {

        /*
         * test commentt
         */

        
        User john = new User(12345678, "John Doe", "john.doe@gmail.com", "jontron3000");
        System.out.println(john.studentId + " | " + john.username + " | " + john.email + " | " + john.password);

        User jane = new User(12345679, "Jane123", "janedoe@gmail.com", "abc123");
        User sam = new User(987654321, "lololol", "sam@gmail.com", "ppp32");
        User paul = new User(87654291, "rawr", "pawl@gmail.com", "lol123");

        ArrayList<User> johnsFriendList = new ArrayList<User>(Arrays.asList(jane));

        //group message
        ArrayList<User> a = new ArrayList<User>(Arrays.asList(jane, sam, paul));
        //Message gc = new Message(john, a, d1, t1, "hru >,<");
        //System.out.println(gc.displayGroupMessage());


        //private message
        //Message dm = new Message(paul, jane, d1, t1, "have u done the quiz yet");
        //System.out.println(dm.displayPrivateMessage());
        
        User kate = new User(98888192, "katekate", "kate@gmail.com", "password!");


        //friend list - add and remove user
        john.addFriend(sam);
        john.displayFriendsList();

        
        //sending message in a channel? - again probs need to add a method in user so user can directly send messages
        Channel test = new Channel("Study", "COMP1010");
        //Message firstMes = new Message(test, kate, "how was the prac?",  d1, t1);
        //System.out.println(firstMes.displayChannelMessage());

        Scanner demoScanner = new Scanner(System.in);
        System.out.println("Enter username");
    
        String username = demoScanner.nextLine();
        System.out.println("Username is: " + username);

        demoScanner.close();
    }
}
