import java.util.*;

public class Client {
    public static void main(String[] args) throws Exception {

        Date d1 = new Date (12, 9, 2024);
        Time t1 = new Time (6, 15, 59);
        DateTime dt1 = new DateTime(d1, t1);
        
        User john = new User(12345678, "John Doe", "john.doe@gmail.com", "jontron3000");
        System.out.println(john.studentId + " | " + john.username + " | " + john.email + " | " + john.password);

        User jane = new User(12345679, "Jane123", "janedoe@gmail.com", "abc123");
        User sam = new User(987654321, "lololol", "sam@gmail.com", "ppp32");
        User paul = new User(87654291, "rawr", "pawl@gmail.com", "lol123");


        //group message
        ArrayList<User> a = new ArrayList<User>(Arrays.asList(jane, sam, paul));
        Message gc = new Message(john, a, dt1, "hru >,<");
        System.out.println(gc.groupMessage());


        //private message
        Message dm = new Message(paul, jane, dt1, "have u done the quiz yet");
        System.out.println(dm.privMessage());
        

        //adding someone to the gc
        User kate = new User(98888192, "katekate", "kate@gmail.com", "password!");
        gc.addUser(kate);


        //removing someone from the gc
        gc.removeUser(kate);

        
        //sending message in a channel? - again probs need to add a method in user so user can directly send messages
        Channel test = new Channel("Study", "COMP1010");
        Message firstMes = new Message(test, kate, "how was the prac?",  dt1);
        System.out.println(firstMes.channelMessage());

    }
}
