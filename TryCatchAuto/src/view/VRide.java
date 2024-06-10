package view;

import model.DataBaseConnection;
import model.Passenger;
import model.Ride;

public class VRide {
    public static void printRideForDriver(DataBaseConnection conn,Ride ride){
        Passenger passenger= conn.SelectOnePassenger(ride.getPassenger_id());
        System.out.println("Passenger`s name: "+passenger.getFirstName()+
                "\nPassenger`s rating: " +passenger.getRating()+
                "\nPlace of pickup: " +ride.getPick_up()+
                "\nDestination: "+ride.getDestination()+
                "\nRide length: "+ride.getRideLength()
        );

    }

}
