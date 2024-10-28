## Program overview

BigMACS is a Java-based application that allows users to send and read messages to others, similar to other social messaging apps. 

Upon signing in with a username and password, users can send messages privately, to group chats, or to channels, as well as make new friends and block other users. 

Group chats must have 3 or more users, and users can be added or removed from group chats. Channels can be freely created, do not require user invitation, and can also be subscribed to.

## Program structure

The following classes handle the program's functions.

### Client

- **User customisation:** other users that can be interacted with can be customised under the '**testUsers**' function.
- **Scanner:** the scanner class is able to read user input, allowing for interactibility with the application.
- **IO:** the `java.io` package allows a transcript of activity with the application to be produced. A transcript of the users on the application, as well as all user activity is saved locally as .txt files.

### Channel

- **Messages:** messages can be sent and/or deleted.

### Date and Time

Using the `java.time` package, the user's current date and time is displayed in messages.

### Message

- **Sending messages:** messages can be sent privately, to group chats, or to channels.
- **Removing messages:** messages can be deleted individually.
- **Group chat management:** users can be added or removed from group chats.

### Signin

Upon running the program, the user's details (username and password) can be inputted in. The program will check whether the input is valid or not.

### Subscription

User objects and channel objects can be returned using the Subscription class. A toString function also returns the name of a channel.

### User

- **Friends:** friends can be added or removed from the user's friend list.
- **Blocked users:** others can be added or removed from the user's list of blocked users.
- **Channel subscriptions:** channels can be added or removed from the user's subscription list. 

## How to run the program

It is recommended to have **Java JDK 21 or higher** installed before running the program.

1. Open the program through **Visual Studio Code** and run **Client.java** to run the application.
2. A menu will appear in the terminal. Options can be selected by inputting the corresponding number.
3. To make a new account, input '**2**' to register. The subsequent prompts will ask for a student ID, username, email and password. The application will return to the BigMacs menu after all prompts have been answered.
4. To login, input '**1**'. The prompts will ask for a username and password. If all prompts were answered correctly, a user menu will appear.
5. Options can be selected by inputting the corresponding number. The options that will appear are subscribing to a channel, sending a message, blocking users, adding users as a friend, removing friends, unblocking users, unsubscribing from a channel, and logging out.
 - By default, the users '**j0hN**', '**janeee**', and '**jojo**' can be interacted with to add/remove as friends, to block/unblock, and send messages to.
 - Interactable users can be customised in '**Client.java**' under the '**testUsers**' function.
6. To log out, input '**8**'. This will lead to the BigMacs menu.
7. To terminate the program, input '**3**'.

Two .txt files will be saved and updated locally while using the program.
 - **user_activity_log.txt:** a transcript of all interactions with the menus, such as subscriptions, messages sent, friends added, etc.
 - **users.txt:** a transcript of all the registered users on the application. Their student ID, username, and email are provided.