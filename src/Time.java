import java.time.LocalTime;

public class Time {
    public int hour, minute, second;

    //constructor to return the current time
    public Time() {
        LocalTime currentTime = LocalTime.now();
        this.hour = currentTime.getHour();
        this.minute = currentTime.getMinute();
        this.second = currentTime.getSecond();
    }

    public String toString() {
        return String.format("%02d:%02d:%02d", hour, minute, second);
   } 
}
