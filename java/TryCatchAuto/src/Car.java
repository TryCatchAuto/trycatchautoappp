public class Car {
    private String car_id;
    private  String plates;
    private String model;
    private String color;
    private int seats;

    public Car() {}
    public Car(String plates, String model, String color, int seats) {
        this.plates = plates;
        this.model = model;
        this.color = color;
        this.seats = seats;
    }

    public String getPlates() {
        return plates;
    }

    public void setPlates(String plates) {
        this.plates = plates;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public String getCar_id() {
        return car_id;
    }

    public void setCar_id(String car_id) {
        this.car_id = car_id;
    }
}
