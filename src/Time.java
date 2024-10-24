import java.time.LocalTime;

public class Time {
    public int hour, minute, second;

    //constructor to get current time
    public Time() {
        LocalTime currentTime = LocalTime.now();
        this.hour = currentTime.getHour();
        this.minute = currentTime.getMinute();
        this.second = currentTime.getSecond();
    }
    
    public Time(int hour, int minute, int second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    //future idea: minutes/seconds increase over time by a random amount
    
    public String toString() {
        return String.format("%02d", hour)+":"+String.format("%02d", minute)+":"+String.format("%02d", second);
   } 
}
