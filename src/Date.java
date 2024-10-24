import java.time.LocalDate;

public class Date {
    public int day, month, year;

    //date constuctor to get current date
    public Date() {
        LocalDate currentDate = LocalDate.now();
        this.day = currentDate.getDayOfMonth();
        this.month = currentDate.getMonthValue();
        this.year = currentDate.getYear();

    }

    public Date(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }
    public String toString() {
        return day+"/"+month+"/"+year;
   } 
}
