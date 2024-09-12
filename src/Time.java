public class Time {
    public int hour, minute, second;
    
    public Time(int hour, int minute, int second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }
    
    public String toString() {
        return this.hour+":"+this.minute+":"+this.second;
   } 
}
