import java.io.*;
import java.util.*;

public class Client {
    public static final String USER_DATA_FILE = "bigMacs.txt";
    public static ArrayList<User> users = new ArrayList<>();
    public static ArrayList<Message> messages = new ArrayList<>();
    public static User loggedInUser;

    public static void main(String[] args) {
        loadUsersFromFile();
        Scanner scanner = new Scanner(System.in);

        while(true) {
            displayMenu();
            int choice = userChoice(scanner);
            
            if(choice == 1) {
                login(scanner);
            }
            if(choice == 2) {
                register(scanner);
            }
            if(choice == 3) {
                saveUsersToFile();
                System.exit(0);
            } else {
                System.out.println("Error: this option is not valid, please try again.");
            }
            if (loggedInUser != null) {
                userMenu(scanner);
            }
        }
    }

    public static void displayMenu() {
        System.out.println("| BigMacs Menu |");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. Exit");
        System.out.print("Please choose an option");
    }

    public static int userChoice(Scanner scanner) {
        return scanner.nextInt();
    }

    public static void loadUsersFromFile() {
        try(BufferedReader reader = new BufferedReader(new FileReader(USER_DATA_FILE))) {
            String line;
            while((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                long studentId = Long.parseLong(parts[0]);
                String username = parts[1];
                String email = parts[2];
                String password = parts[3];
                users.add(new User(studentId, username, email, password));
            }
        } catch (IOException e) {
            System.out.println("Error: loading user data.");
        }
    }

    public static void saveUsersToFile() {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(USER_DATA_FILE))) {
            for(int i=0; i<users.size(); i++) {
                User user = users.get(i);
                writer.write(user.studentId+","+user.username+","+user.email);
                writer.newLine();
            }
        } catch(IOException e) {
            System.out.println("Error saving user data.");
        }
    }

    public static void updateUserActivityToFile(String userActions) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(USER_DATA_FILE, true))) {
            writer.write(userActions);
            writer.newLine();
        } catch(IOException e) {
            System.out.println("Error: logging "+userActions);
        }
    }

    public static void login(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.next();
        System.out.print("Enter password: ");
        String password = scanner.next();

        loggedInUser = findUser(username, password);
        if (loggedInUser != null) {
            System.out.println("Login successful! Welcome, " + loggedInUser.username);
        } else {
            System.out.println("Error: login failed, please re-enter your username and password");
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

    public static void register(Scanner scanner) {
        System.out.print("Enter your Student ID: ");
        long studentId = scanner.nextLong();
        scanner.nextLine();
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        
        if (findUserByUsername(username) != null) {
            System.out.println("Error: this username already exists, please choose another");
            return;
        }

        users.add(new User(studentId, username, email, password));
        System.out.println("Registration successful! You can now log in");
    }

    public static void userMenu(Scanner scanner) {
        while (true) {
            displayUserMenu();
            int choice = userChoice(scanner);

            if(choice == 1) {
                subscribeToChannel(scanner);
            }
            if(choice == 2) {
                sendMessage(scanner);
            }
            if(choice == 3) {
                blockUser(scanner);
            }
            if(choice == 4) {
                addUser(scanner);
            }
            if(choice == 5) {
                loggedInUser = null;
                System.out.println("Logged out sucessfully");
            } else {
                System.out.println("Error: this option is not valid, please try again");
            }
        }
    }

    public static void displayUserMenu() {
        System.out.println("\nUser Menu:");
        System.out.println("1. Subscribe to Channel");
        System.out.println("2. Send Message");
        System.out.println("3. Block User");
        System.out.println("4. Add User");
        System.out.println("5. Log Out");
        System.out.print("Choose an option: ");
    }

    public static void subscribeToChannel(Scanner scanner) {
        System.out.print("Enter channel name to subscribe: ");
        String channelName = scanner.next();
        Channel channel = new Channel(channelName, channelName);
        loggedInUser.subscribeToChannel(channel);
        System.out.println("Subscribed to channel: " + channelName);
    }

    public static void sendMessage(Scanner scanner) {
        System.out.print("Choose message type (1: Channel, 2: Private, 3: Group): ");
        int messageType = scanner.nextInt();
        scanner.nextLine();

        if(messageType == 1) {
            sendChannelMessage(scanner);
        }
        if(messageType == 2) {
            sendPrivateMessage(scanner);
        }
        if(messageType == 3) {
            sendGroupMessage(scanner);
        } else {
            System.out.println("Error: this option is not valid, please try again.");
        }
    }

    public static void sendChannelMessage(Scanner scanner) {
        System.out.print("Enter channel name: ");
        String channelName = scanner.nextLine();
        System.out.print("Enter your message: ");
        String messageContent = scanner.nextLine();

        Channel channel = new  Channel(channelName, channelName);
        Message message = new Message(loggedInUser, messageContent, channel, null, null);
        message.sendChannelMessage(messages);
    }

    public static void sendPrivateMessage(Scanner scanner) {
        System.out.print("Enter recipient's username: ");
        String recipientUsername = scanner.nextLine();
        User recipient = findUserByUsername(recipientUsername);

        if (recipient != null) {
            System.out.print("Enter your message: ");
            String messageContent = scanner.nextLine();
            Message message = new Message(loggedInUser, messageContent, null, null, recipient);
            message.sendPrivateMessage(messages);
        } else {
            System.out.println("Error: User not found.");
        }
    }

    public static void sendGroupMessage(Scanner scanner) {
        System.out.print("Enter group name: ");
        System.out.print("Enter your message: ");
        String messageContent = scanner.nextLine();

        ArrayList<User> groupChatMembers = new ArrayList<>();
        Message message = new Message(loggedInUser, messageContent, null, groupChatMembers, null);
        message.sendGroupMessage(messages);
    }

    public static void blockUser(Scanner scanner) {
        System.out.print("Enter the username of the user to block: ");
        String usernameToBlock = scanner.nextLine();
        User userToBlock = findUserByUsername(usernameToBlock);

        if (userToBlock != null) {
            loggedInUser.blockUser(userToBlock);
            System.out.println("User " + usernameToBlock + " has been blocked.");
        } else {
            System.out.println("Error: User not found.");
        }
    }

    public static void addUser(Scanner scanner) {
        System.out.print("Enter your Student ID: ");
        long studentId = scanner.nextLong();
        scanner.nextLine();
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        
        if (findUserByUsername(username) != null) {
            System.out.println("Error: user is already added");
            return;
        }

        users.add(new User(studentId, username, email, password));
        System.out.println("User " + username + " has been added.");
    }

    public static User findUserByUsername(String username) {
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            if (user.username == username) {
                return user;
            }
        }
        return null;
    }
}
