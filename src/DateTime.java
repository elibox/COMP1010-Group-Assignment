public class DateTime {
    public Date date;
    public Time time;

    public DateTime(Date date, Time time) {
        this.date = date;
        this.time = time;
    }

    public String toString() {
        return date.toString()+", "+time.toString();
    }
}
