public class Client {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        
        User john = new User(12345678, "John Doe", "john.doe@gmail.com", "jontron3000");
        System.out.println(john.studentId + " | " + john.name + " | " + john.email + " | " + john.password);
    }
}
