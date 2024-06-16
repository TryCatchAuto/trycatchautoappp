package contorller;

import model.ApplicationForEarlierSalary;
import model.DataBaseConnection;
import model.Driver;
import model.Ride;
import view.VDriver;
import view.VRide;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class CDriver {


    public static void driverMenu(DataBaseConnection conn,Driver driver){
//        boolean driverDuringRide=false;
        Scanner scanner=new Scanner(System.in);
        int choice=1;
        while(choice!=0){
            VDriver.printMenu();
            choice =scanner.nextInt();
            switch (choice){
                case 0://log out
                    break;
                case 1:
                    //driverMenuDuringWork(driver);
                    inWork(conn,driver);
                    break;
                case 2:
                    accountHistory(conn,driver);
                    break;
                case 3:
                    requestEarlierSalary(conn,driver);
                    break;


            }
//            VDriver.printMenu();
//            choice =scanner.nextInt();

        }
    }
    public static void requestEarlierSalary(DataBaseConnection conn,Driver driver){
        Scanner scanner=new Scanner(System.in);
        VDriver.askForReason();
        String comment=scanner.nextLine();
        VDriver.printAskForSalary();
        String choice= scanner.next();
        if(choice.equals("1")){

            ApplicationForEarlierSalary newApplication= new ApplicationForEarlierSalary();
            List<String> employeesIDs=conn.SelectAllEmployeesID();
            String employeeID= employeesIDs.get(new Random().nextInt(employeesIDs.size()));
            newApplication.setEmployee_id(employeeID);
            //newApplication.setEmployee_id(conn.SelectAllEmployeesID().get(new Random().nextInt(conn.SelectAllEmployeesID().size())));
            newApplication.setDescription(comment);
            newApplication.setDate(Date.valueOf(LocalDate.now()));
            newApplication.setDriver_id(driver.getDriver_id());
            newApplication.setStatus("Unresolved");
            conn.InsertApplicationForEarlierSalary(newApplication);
        }
    }
    public static void accountHistory(DataBaseConnection conn, Driver driver){
        Scanner scanner=new Scanner(System.in);
        int choice=1;
        while(choice!=0) {
            choice = scanner.nextInt();
            //tu będzie potrzebnu Bartek
            if (choice==1){

            }
        }
    }


    private static void inWork(DataBaseConnection conn,Driver driver){
        Scanner scanner=new Scanner(System.in);
        while(true) {
            VDriver.printMenuDuringWork();
            String choice=scanner.next();
            if (choice.equals("1")) {
                break;
            }
            else if (choice.equals("2")) {
                takeABreak(conn, driver);
            } else {
                Ride ride = new Ride(conn);
                VDriver.printRideApperead();
                VRide.printRideForDriver(conn, ride);
                VDriver.printRideDecision();
                int choice2 = scanner.nextInt();
                if (choice2 == 1) {
                    ride.setDriver_id(driver.getDriver_id());
                    Time startTime = ride.getStartTime();
                    LocalTime tmptime = startTime.toLocalTime();
                    int timeLength = new Random().nextInt(5, 20);
                    tmptime = tmptime.plusMinutes(timeLength);
                    ride.setEndTime(Time.valueOf(tmptime));
                    VDriver.askForRating();
                    int passengerRating = scanner.nextInt();
                    while (passengerRating < 0 || passengerRating > 5) {
                        VDriver.wrongRating();
                        passengerRating = scanner.nextInt();
                    }
                    ride.setRatingForPassenger(passengerRating);
                    conn.InsertRide(ride);
                    conn.UpdateRatingForPassenger();
                }

            }
        }



    }
    private static void takeABreak(DataBaseConnection conn, Driver driver){
        VDriver.printBreak();
        Scanner scanner=new Scanner(System.in);
        scanner.next();
        //driverMenuDuringWork(driver);
       // inWork(conn,driver);
    }
    static class TimerTask implements Runnable {
        public void run() {
            while (true) {
                try {
                    Thread.sleep(15000); // Opóźnienie 15 sekund
                    System.out.println("Minęło 15 sekund");
                } catch (InterruptedException e) {
                    System.out.println("Timer wątek został przerwany");
                    break;
                }
            }
        }
    }


}
