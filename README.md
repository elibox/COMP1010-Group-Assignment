## Program overview

BigMACS is a Java-based application that allows users to send and read messages to others, similar to other social messaging apps. 

Upon signing in with a username and password, users can send messages privately, to group chats, or to channels, as well as make new friends and block other users. Channels also offer a search function and a subscription function.

## Program structure

The following classes handle the program's functions.

### Channel

- **Messages:** messages can be sent and/or deleted. A transcript of all messages sent is also available.
- **Search:** messages sent from a given user can be searched for.

### Date and Time

Using the `java.time` package, the user's current date and time is displayed in messages.

### Message

- **Sending messages:** messages can be sent privately, to group chats, or to channels.
- **Removing messages:** messages can be deleted individually.
- **Group chat management:** users can be added or removed from group chats. A list of users in a group chat is also generatable.

### Signin

Upon running the program, the user's details (username and password) can be inputted in. The program will check whether the input is valid or not.

### User

- **Friends:** friends can be added or removed from the user's friend list.
- **Blocked users:** others can be added or removed from the user's list of blocked users.
- **Channel subscriptions:** channels can be added or removed from the user's subscription list. The user will be notified of new messages posted in a channel when subscribed.

## How to run the program

It is recommended to have **Java JDK 21 or higher** installed before running the program.

Open the program through **Visual Studio Code** and run **Client.java** to run the application.