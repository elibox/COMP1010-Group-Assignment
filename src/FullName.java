public class FullName {
    String firstName, middleNames, lastName;
    
    public FullName(String firstName, String middleNames, String lastName) {
        this.firstName = firstName;
        this.middleNames = middleNames;
        this.lastName = lastName;
    }

    public String toString() {
        return(firstName + middleNames + lastName);
    }
}