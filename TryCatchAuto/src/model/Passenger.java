package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import static view.VPassenger.*;

public class Passenger {
    private String passenger_id;
    private String lastName;
    private String firstName;
    private String email;
    private String login;
    private String password;
    private float rating;
    private Wallet wallet;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getPassenger_id() {
        return passenger_id;
    }

    public void setPassenger_id(String passenger_id) {
        this.passenger_id = passenger_id;
    }

    public void setWallet(Wallet wallet){this.wallet = wallet;}
    private boolean canIAffordIt(Wallet wallet,float price){
        try{
            if(wallet.getMoney()<price) return false;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return true;
    }

    public static String chceckLogIn(DataBaseConnection conn, String login, String password){
        //sprawdzamy czy login istnieje i czy hasło pasuje
        //badLogin- nie ma takiego loginu, badPassword-zle haslo
        String result = "";
        //metoda bartka
        result = conn.CheckLoginPassenger(login, password);
        return result;
    }
    public void orderRide(DataBaseConnection conn){
        String from="";
        String to="";
        int nOfPassengers=0;
        printGiveDestination();
        Scanner scan = new Scanner(System.in);
        try {
            to = scan.nextLine();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        printGiveLocation();
        try {
            from = scan.nextLine();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        while(nOfPassengers<1 || nOfPassengers>4){
            try {
                printGiveNOfPeople();
                nOfPassengers = scan.nextInt();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        Ride ride = new Ride(this.passenger_id, to, from, conn);
        float price = ride.calculatePrice();
        if(!canIAffordIt(this.wallet, price)){
            printNotEnoughMoney(price, this.wallet.getMoney());
            return;
        }
        printShowPrice(price);
        int option=-1;
        while(option!=0 && option!=1){
            try {
                option = scan.nextInt();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        if(option==0){
            //cena nie zaakceptowana
            return;
        }else{
            List<Driver> drivers = null;
            int id = -1;
            int time = 0;
            String driverId = null;
            int driverFound = -1;
            //cena zaakceptowana
            wallet.setMoney(wallet.getMoney()-price);
            while(driverFound!=1 && driverFound!=0){
                printLookingForDriver();
                drivers = new ArrayList<>();
                Driver driver = new Driver();
                driver.setDriver_id("driver1");
                driver.setFirstName("Adam");
                driver.setLastName("Kowalski");
                driver.setRating(4.5f);
                drivers.add(driver);
//              drivers = conn.SelectAllDriversToList();
//              Random rd = new Random();
//              id = rd.nextInt(drivers.size());
//              driverId = drivers.get(id).getDriver_id();
                driverId = driver.getDriver_id();
                time = ride.estimateArrivalTime(driverId);
                printDriverFound(driver.getFirstName(), driver.getLastName(), driver.getRating(), time);
                //printDriverFound(drivers.get(id).getFirstName(),drivers.get(id).getLastName(), drivers.get(id).getRating(),time);
                driverFound = -1;
                while(driverFound!=0 && driverFound!=1 && driverFound!=2){
                    try {
                        scan = new Scanner(System.in);
                        driverFound = scan.nextInt();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
            if(driverFound==0){
                //rezygnacja z przejazdu
            }else{
                //kierowca wybrany
                ride.setDriver_id(driverId);
                //czas do pzyjazdu kierowcy
                printTimeToDriverArrival(time);
                //rozpoczecie przejazdu

                int endOfRide = -1;
                printChangeOpt();
                while(endOfRide!=0){
                    String newDestination = null;
                    endOfRide = scan.nextInt();
                    if(endOfRide==9){
                        endOfRide = -1;
                        //change destination
                        printChangeDestinaton();
                        newDestination = scan.nextLine();
                        newDestination = scan.nextLine();
                        //check if distance ok
                        if(ride.checkDistance(newDestination)){
                            float newPrice = ride.calculateAdditionalPrice();
                            if(canIAffordIt(wallet,newPrice)){
                                ride.setDestination(newDestination);
                                printDestinationChanged();
                            }else{
                                printNotEnoughMoney(newPrice, wallet.getMoney());
                            }
                        }else{
                            printWrongDistance();
                        }
                    }
                }

                //po zakonczeniu przejazdu
                printAskForTip();
                int tip= -1;
                try {
                    tip = scan.nextInt();
                }catch(Exception e){
                    System.out.println(e.getMessage());
                }
                if(tip==1){
                    printAskHowMuchTip(wallet.getMoney());
                    float tipAmount = -1;
                    while(tipAmount<0 || tipAmount>wallet.getMoney()){
                        tipAmount = scan.nextFloat();
                    }
                    wallet.setMoney(wallet.getMoney()-tipAmount);
                }
                printAskForRating();
                int driverRating = -1;
                while(driverRating<0 || driverRating>5){
                    driverRating=scan.nextInt();
                }
                ride.setRatingForDriver(driverRating);
                //update database
                conn.UpdateWalletMoney(wallet.getMoney(), this.passenger_id);
                conn.UpdateRatingForDriver();
                conn.InsertRide(ride);
            }

        }
    }

    public void addFunds(DataBaseConnection conn) {
        printAddFunds();
        float fundsToAdd=0;
        try{
            Scanner scan = new Scanner(System.in);
            fundsToAdd = scan.nextFloat();
            wallet.setMoney(wallet.getMoney()+fundsToAdd);
            conn.UpdateWalletMoney(wallet.getMoney(), this.passenger_id);
            printMoneyAdded();
        }catch(Exception e){System.out.println(e.getMessage());}
    }
}
