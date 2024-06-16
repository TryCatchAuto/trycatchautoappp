package model;

import contorller.CWallet;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static view.VPassenger.*;
import static view.VWallet.*;
import static view.VWallet.printAddCard;
import static view.VWallet.printAddFunds;
import static view.VWallet.printCreditCardAdded;
import static view.VWallet.printCreditCardChanged;
import static view.VWallet.printMoneyAdded;
import static view.VWallet.printNoCreditCard;


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

    public Wallet getWallet() {
        return wallet;
    }

    private boolean canIAffordIt(Wallet wallet, float price){
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
            Driver driver = null;
            int id = -1;
            int time = 0;
            int driverFound = -1;
            //cena zaakceptowana
            wallet.setMoney(wallet.getMoney()-price);
            while(driverFound!=1 && driverFound!=0){
                printLookingForDriver();

                //dane do testow bez bazy danych
//                drivers = new ArrayList<>();
//                Driver driver1 = new Driver();
//                driver1.setDriver_id("driver1");
//                driver1.setFirstName("Adam");
//                driver1.setLastName("Kowalski");
//                driver1.setRating(4.5f);
//                drivers.add(driver1);
//                Driver driver2 = new Driver();
//                driver2.setDriver_id("driver2");
//                driver2.setFirstName("Ronald");
//                driver2.setLastName("Kowalski");
//                driver2.setRating(4.5f);
//                drivers.add(driver2);

                drivers = conn.SelectAllDriversToList();
                id++;
                if(drivers.isEmpty() || id>=drivers.size()){
                    //nie znaleziono kierowcy
                    printNoDriverFound();
                    return;
                }
                driver = drivers.get(id);
                time = ride.estimateArrivalTime(driver.getDriver_id());
                printDriverFound(driver.getFirstName(), driver.getLastName(), driver.getRating(), time);
                driverFound = -1;
                while(driverFound!=0 && driverFound!=1 && driverFound!=2){
                    // 0 - cancel, 1 - ok, 2-find another
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
                Car car = conn.SelectOneDriverAndCar(driver.getDriver_id()).get(0).getSecond();
                //Car car = new Car("plate", "model", "blue",3);
                ride.setDriver_id(driver.getDriver_id());
                //czas do pzyjazdu kierowcy
                printInfoAboutYourDriver(driver.getFirstName(), driver.getLastName(), car.getPlates(), car.getModel(), car.getColor());
                printTimeToDriverArrival(time);
                //rozpoczecie przejazdu
                boolean destChanged = false;
                ride.setDate(Date.valueOf(LocalDate.now()));
                ride.setStartTime(Time.valueOf(LocalTime.now()));

                boolean endOfRide = false;
                int opt;
                while(!endOfRide){
                    printChangeOpt();
                    opt = scan.nextInt();
                    switch (opt){
                        case 0:
                            endOfRide=true;
                            break;
                        case 2:
                            CWallet.WalletMenu(conn,this);
                            break;
                        case 9:
                            if(!destChanged)
                                destChanged = changeDestination(ride);
                            else
                                printOneDestinationChangePerRide();
                    }
                }

                //po zakonczeniu przejazdu
                ride.setEndTime(Time.valueOf(LocalTime.now()));
                ride.setRideLength(ride.calculateLength());
                manageTipping();

                printAskForRating();
                int driverRating = -1;
                while(driverRating<0 || driverRating>5){
                    driverRating=scan.nextInt();
                }
                ride.setRatingForDriver(driverRating);
                ride.setRatingForPassenger(-1);
                //update database
                conn.UpdateWalletMoney(wallet.getMoney(), this.passenger_id);
                conn.UpdateRatingForDriver();
                conn.InsertRide(ride);
            }

        }
    }

    public boolean changeDestination(Ride ride){
        String newDestination=null;
        printChangeDestination();
        Scanner scan = new Scanner(System.in);
        newDestination = scan.nextLine();
        //check if distance ok
        if(ride.checkDistance(newDestination)){
            float newPrice = ride.calculateAdditionalPrice();
            if(canIAffordIt(wallet,newPrice)){
                printShowPrice(newPrice);
                int acceptNewPrice = 0;
                try {
                    acceptNewPrice = scan.nextInt();
                }catch (Exception e){System.out.println(e.getMessage());}
                if(acceptNewPrice==1){
                    ride.setDestination(newDestination);
                    wallet.setMoney(Math.round((wallet.getMoney()-newPrice)*100.0f)/100.0f);
                    printDestinationChanged();
                    return true;
                }
            }else{
                printNotEnoughMoney(newPrice, wallet.getMoney());
            }
        }else{
            printWrongDistance();
        }
        return false;
    }

    public void manageTipping(){
        printAskForTip();
        int giveTip= -1;
        Scanner scan = new Scanner(System.in);
        try {
            giveTip = scan.nextInt();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        if(giveTip==1){
            printAskHowMuchTip(wallet.getMoney());
            float tipAmount = scan.nextFloat();
            while(tipAmount<0 || tipAmount>wallet.getMoney()){
                System.out.println("Wrong input. Try again.\n");
                tipAmount = scan.nextFloat();
            }
            wallet.setMoney(Math.round((wallet.getMoney()-tipAmount)*100.0f)/100.0f);
        }
    }

    public void addFunds(DataBaseConnection conn) {
        printAddFunds();
        float fundsToAdd=0;
        if(wallet.isCreditCard()){
            try{
                Scanner scan = new Scanner(System.in);
                fundsToAdd = scan.nextFloat();
                wallet.setMoney(wallet.getMoney()+fundsToAdd);
                conn.UpdateWalletMoney(wallet.getMoney(), this.passenger_id);
                printMoneyAdded();
            }catch(Exception e){System.out.println(e.getMessage());}
        }else{
            printNoCreditCard();
        }
    }

    public void addCard(DataBaseConnection conn){
        printAddCard();
        String cardNr;
        boolean alreadyHaveCard = this.wallet.isCreditCard();
        try{
            Scanner scan = new Scanner(System.in);
            cardNr = scan.nextLine();
            this.wallet.setCreditCard(true);
            conn.UpdateWalletCreditCard(this.wallet.isCreditCard(), this.passenger_id);
            if(!alreadyHaveCard) printCreditCardAdded();
            else printCreditCardChanged();
        }catch(Exception e){System.out.println(e.getMessage());}
    }
}