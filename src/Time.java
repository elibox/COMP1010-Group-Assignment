public class Time {
    public int hour, minute, second;
    
    public Time(int hour, int minute, int second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    //future idea: minutes/seconds increase over time by a random amount
    
    public String toString() {
        return hour+":"+minute+":"+second;
   } 
}
