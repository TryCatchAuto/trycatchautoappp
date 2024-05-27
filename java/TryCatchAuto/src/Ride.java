import java.sql.Date;
import java.sql.Time;

public class Ride {
    private Date date;
    private String pick_up;
    private String destination;
    private float price;
    private float rideLength;
    private Time startTime;
    private Time endTime;
    private String passenger_id;
    private String driver_id;

    private float ratingForPassenger; // ocena jaką dostał pasażer
    private float ratingForDriver; //ocena jaką dostał kierowca

    public java.sql.Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPick_up() {
        return pick_up;
    }

    public void setPick_up(String pick_up) {
        this.pick_up = pick_up;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getRideLength() {
        return rideLength;
    }

    public void setRideLength(float rideLength) {
        this.rideLength = rideLength;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public String getPassenger_id() {
        return passenger_id;
    }

    public void setPassenger_id(String passenger_id) {
        this.passenger_id = passenger_id;
    }

    public String getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(String driver_id) {
        this.driver_id = driver_id;
    }

    public float getRatingForPassenger() {
        return ratingForPassenger;
    }

    public void setRatingForPassenger(float ratingForPassenger) {
        this.ratingForPassenger = ratingForPassenger;
    }

    public float getRatingForDriver() {
        return ratingForDriver;
    }

    public void setRatingForDriver(float ratingForDriver) {
        this.ratingForDriver = ratingForDriver;
    }
}
