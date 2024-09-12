public class DateTime {
    public Date date;
    public Time time;

    public DateTime(Date date, Time time) {
        this.date = date;
        this.time = time;
    }

    public String toString() {
        return this.date.toString()+", "+this.time.toString();
    }
}
