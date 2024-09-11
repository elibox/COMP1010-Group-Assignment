public class User {
    /*ArrayList<Integer> studentID = ArrayList<>(); //8 characters
    public ArrayList<String> alias, pronouns;  
    password reqs, 1 uppercase letter + 1 special character, min 8 char
    no repeating aliass
    info stored for login?*/
    
    long studentId;
    String email, password;
    String name;

    public User(long studentId, String name, String email, String password) {
        this.studentId = studentId;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    /* probably not the right way to do it - nick
    public void studentId() {
        System.out.println(studentId);
    }

    public void email() {
        System.out.println(email);
    }

    public void password() {
        System.out.println(password);
    }

    public void alias() {
        System.out.println(alias);
    }

    public void name() {
        System.out.println(name);
    }

    public void age() {
        System.out.println(age);
    }

    public void publicInfo() {
        System.out.println(alias + " | " + name + " | " + age);
    }

    public void privateInfo() {
        System.out.println(studentId + " | " + email + " | " + password);
    }
    */
}
