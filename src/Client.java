import java.io.*;
import java.util.*;

public class Client {
    public static final String USER_DATA_FILE = "users.txt";
    public static final String ACTIVITY_LOG_FILE = "user_activity_log.txt";
    public static ArrayList<User> users = new ArrayList<>();
    public static ArrayList<Message> messages = new ArrayList<>();
    public static User loggedInUser;

    public static void main(String[] args) {
        loadUsersFromFile();

        //saving sample users to users.txt
        if(users.isEmpty()) {
            testUsers();
            saveUsersToFile();
        }

        Scanner scanner = new Scanner(System.in);
        //initial display menu 
        while(true) {
            displayMenu();
            int choice = userChoice(scanner);
            switch(choice) {
                case 1:
                    login(scanner);
                    break;
                case 2:
                    register(scanner);
                    break;
                case 3:
                    saveUsersToFile();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Error: this option is not valid, please try again.");
            }
            if (loggedInUser != null) {
                userMenu(scanner);
            }
        }
    }

    //print lines for display menu
    public static void displayMenu() {
        System.out.println("| BigMacs Menu |");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. Exit");
        System.out.print("Please select one of the options: ");
    }

    //handles the users choice
    public static int userChoice(Scanner scanner) {
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }

    //method that logs all active users information onto users textfile
    public static void loadUsersFromFile() {
        try(BufferedReader reader = new BufferedReader(new FileReader(USER_DATA_FILE))) {
            String line;
            while((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if(parts.length < 4) {
                    continue;
                }
                long studentId = Long.parseLong(parts[0]);
                String username = parts[1];
                String email = parts[2];
                String password = parts[3];
                users.add(new User(studentId, username, email, password));
            }
        } catch (IOException e) {
            System.out.println("Error: loading user data.");
        }
        if(users.isEmpty()) {
            testUsers();
        }
    }
    
    //sample users already on program
    public static void testUsers() {
        users.add(new User(12345678, "j0hN", "john.doe@gmail.com", "jontron3000"));
        users.add(new User(12345679, "janeee", "jane.doe@gmail.com", "abcde999"));
        users.add(new User(12345680, "jojo", "jojo.dodo@gmail.com", "owowo001"));
    }

    public static void saveUsersToFile() {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(USER_DATA_FILE))) {
            for(int i=0; i<users.size(); i++) {
                User user = users.get(i);
                writer.write(user.studentId+", "+user.username+", "+user.email+", ");
                writer.newLine();
            }
        } catch(IOException e) {
            System.out.println("Error saving user data.");
        }
    }

    //method to log all user activity onto the user activity log
    public static void logAction(String action) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(ACTIVITY_LOG_FILE, true))) {
            writer.write(action);
            writer.newLine();
        } catch(IOException e) {
            System.out.println("Error: logging "+action);
        }
    }

    //handles login
    public static void login(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.next();
        System.out.print("Enter password: ");
        String password = scanner.next();

        loggedInUser = findUser(username, password);
        if (loggedInUser != null) {
            System.out.println("Login successful! Welcome, " + loggedInUser.username);
            logAction("User logged in: "+loggedInUser.username);
        } else {
            System.out.println("Error: incorrect username or password, please login again.");
        }
    }

    public static User findUser(String username, String password) {
        for (int i=0; i<users.size(); i++) {
            User user = users.get(i);
            if (user.username.equals(username) && user.password.equals(password)) {
                return user;
            }
        }
        return null;
    }

    //handles registration
    public static void register(Scanner scanner) {
        System.out.print("Enter your Student ID: ");
        String studentIdStr = scanner.nextLine();

        if(!studentIdStr.matches("\\d{8}")) {
            System.out.println("Error: Student ID should contain exactly 8 digits, please try again");
            return;
        }
        long studentId = Long.parseLong(studentIdStr);

        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        if(findUserByUsername(username) != null) {
            System.out.println("Error: this username already exists, please choose another one");
            return;
        }

        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        if(!email.contains("@gmail.com")) {
            System.out.println("Error: email format is not valid, please enter a valid email");
            return;
        }

        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        if(password.length() < 8) {
            System.out.println("Error: password should have minimum 8 characters, please choose another one");
            return;
        }

        users.add(new User(studentId, username, email, password));
        logAction("New user registers: "+username);
        System.out.println("Registration successful! You can now log in");

        //saving new user to users.txt
        saveUsersToFile();
    }

    //handles the user menu option, after logging in and accessing the program
    public static void userMenu(Scanner scanner) {
        while (true) {
            displayUserMenu();
            int choice = userChoice(scanner);
            switch(choice) {
                case 1:
                    subscribeToChannel(scanner);
                    break;
                case 2:
                    sendMessage(scanner);
                    break;
                case 3:
                    blockUser(scanner);
                    break;
                case 4:
                    addFriend(scanner);
                    break;
                case 5:
                    removeFriend(scanner);
                    break;
                case 6:
                    unblockUser(scanner);
                    break;
                case 7:
                    unsubscribeFromChannel(scanner);
                    break;
                case 8:
                    loggedInUser = null;
                    System.out.println("Logged out successfully!");
                    logAction("User has logged out.");
                    return;
                default:
                    System.out.println("Error: this option is not valid, please try again.");
            }
        }
    }

    //print lines for user menu
    public static void displayUserMenu() {
        System.out.println("\nUser Menu:");
        System.out.println("1. Subscribe to Channel");
        System.out.println("2. Send a Message");
        System.out.println("3. Block a User");
        System.out.println("4. Add a User");
        System.out.println("5. Remove a Friend");
        System.out.println("6. Unblock a User");
        System.out.println("7. Unsubscribe from a Channel");
        System.out.println("8. Log Out");
        System.out.print("Please select one of the options: ");
    }

    //method for subscribing to channels
    public static void subscribeToChannel(Scanner scanner) {
        System.out.print("Enter channel name to subscribe: ");
        String channelName = scanner.next();
        Channel channel = new Channel(channelName, channelName);
        loggedInUser.subscribeToChannel(channel);
        logAction(loggedInUser.username+" has subscribed to the "+channelName+" channel.");
    }

    //method for unsubscribing from channels
    public static void unsubscribeFromChannel(Scanner scanner) {
        loggedInUser.displayChannelSubscriptions();
        if(loggedInUser.subscriptions.isEmpty()) {
            System.out.println("Error: user is not subscribed to any channels.");
            return;
        }
        System.out.print("Please enter the index of the channel you want to unsubscribe from: ");
        int idx = scanner.nextInt();
        String channelName = loggedInUser.unsubscribeFromChannel(idx);
        if(channelName != null) {
            logAction(loggedInUser.username+" has unsubscribed from the "+channelName+" channel");
        }
    }
    
    //manages selections for message types
    public static void sendMessage(Scanner scanner) {
        System.out.print("Please choose message type [1: Channel, 2: Private, 3: Group]: ");
        int messageType = scanner.nextInt();
        scanner.nextLine();
        if(messageType == 1) {
            sendChannelMessage(scanner);
        } else if(messageType == 2) {
            sendPrivateMessage(scanner);
        } else if(messageType == 3) {
            sendGroupMessage(scanner);
        } else {
            System.out.println("Error: this option is not valid, please try again.");
        }
    }

    //method for sending channel messages
    public static void sendChannelMessage(Scanner scanner) {
        System.out.print("Enter channel name: ");
        String channelName = scanner.nextLine();
        System.out.print("Enter your message: ");
        String messageContent = scanner.nextLine();

        Channel channel = new Channel(channelName, channelName);
        Message message = new Message(loggedInUser, messageContent, channel, null, null);
        message.sendChannelMessage(messages);
        logAction(loggedInUser.username+" sent a message to "+channelName+" channel");
    }

    //method for sending private messages
    public static void sendPrivateMessage(Scanner scanner) {
        System.out.print("Enter recipient's username: ");
        String recipientUsername = scanner.nextLine();
        User recipient = findUserByUsername(recipientUsername);

        if (recipient != null) {
            System.out.print("Enter your message: ");
            String messageContent = scanner.nextLine();
            Message message = new Message(loggedInUser, messageContent, null, null, recipient);
            message.sendPrivateMessage(messages);
            logAction(loggedInUser.username+" sent a private message to "+recipientUsername);
        } else {
            System.out.println("Error: User not found.");
        } 
    }

    //method for sending group messages
    public static void sendGroupMessage(Scanner scanner) {
        System.out.print("Enter users to send group message to, separating each user with a comma: ");
        String userInput = scanner.nextLine();

        String[] usernames = userInput.split(",");
        ArrayList<User> groupChatMembers = new ArrayList<>();
        boolean isGroupChatValid = true;

        for(int i=0; i<usernames.length; i++) {
            String username = usernames[i];
            User member = findUserByUsername(username);
            if(member != null) {
                groupChatMembers.add(member);
            } else {
                System.out.println("User not found: "+username);
                isGroupChatValid = false;
            }
        }

        if(isGroupChatValid == true) {
            System.out.print("Enter your message: ");
            String messageContent = scanner.nextLine();
            Message message = new Message(loggedInUser, messageContent, null, groupChatMembers, null);
            message.sendGroupMessage(messages);
            logAction(loggedInUser.username+ " sent a group message to ["+String.join(", ", usernames)+"]");
        }
    }

    //method to block users
    public static void blockUser(Scanner scanner) {
        System.out.print("Please enter the username of the user you would like to block: ");
        String usernameToBlock = scanner.nextLine();
        User userToBlock = findUserByUsername(usernameToBlock);
        if (userToBlock != null) {
            loggedInUser.blockUser(userToBlock);
            logAction(loggedInUser.username+" has blocked "+usernameToBlock);
        } else {
            System.out.println("Error: User not found.");
        }
    }

    //method for unblocking users
    public static void unblockUser(Scanner scanner) {
        if(loggedInUser.blockList == null) {
            System.out.println("Error: no users have been blocked.");
            return;
        }
        loggedInUser.displayBlockList();
        System.out.print("Please enter the username of the user you would like to unblock: ");
        String usernameToUnblock = scanner.next();
        User userToUnblock = findUserByUsername(usernameToUnblock);
        if(userToUnblock != null) {
            loggedInUser.unblockUser(userToUnblock);
            logAction(loggedInUser.username+" has unblocked "+usernameToUnblock);
        } else {
            System.out.println("Error: "+userToUnblock+" is not on block list.");
        }
    }

    //method for adding friends
    public static void addFriend(Scanner scanner) {
        System.out.print("Please enter the username of the user you would like to add: ");
        String usernameToAdd = scanner.nextLine().trim();
        User userToAdd = findUserByUsername(usernameToAdd);
        if(userToAdd != null) {
            loggedInUser.addFriend(userToAdd);
            logAction(loggedInUser.username+" has added friend "+usernameToAdd);
        } else {
            System.out.println("Error: this user does not exist.");
        }
    }

    //method for removing friends
    public static void removeFriend(Scanner scanner) {
        if(loggedInUser.friendsList == null) {
            return; 
        }
        loggedInUser.displayFriendsList();
        System.out.print("Please enter the username of the friend you would like to remove: ");
        String usernameToRemove = scanner.next();
        User userToRemove = findUserByUsername(usernameToRemove);
        if(userToRemove != null) {
            loggedInUser.removeFriend(userToRemove);
            logAction(loggedInUser.username+" has removed friend "+usernameToRemove);
        } else {
            System.out.println("Error: "+usernameToRemove+" is not on friends lists.");
        }
    }

    //helper to return the username of a specific user from a list of users
    public static User findUserByUsername(String username) {
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            if (user.username.equals(username)) {
                return user;
            }
        }
        return null;
    }
}
