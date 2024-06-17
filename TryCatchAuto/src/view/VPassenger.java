package view;

public class VPassenger {
    public static void printMenu(){
        System.out.println("0. Exit.");
        System.out.println("1. Order a ride.");
        System.out.println("2. Manage your wallet.");
        System.out.println("3. Show history.\n");
    }
    public static void printGiveDestination(){
        System.out.println("Enter destination.\n");
    }
    public static void printGiveLocation(){
        System.out.println("Enter pickup location.\n");
    }
    public static void printGiveNOfPeople(){
        System.out.println("How many passengers (1,4).\n");
    }
    public static void printShowPrice(float price){
        System.out.printf("Price of the ride: %.2f zl\n", price);
        System.out.println("To accept choose 1, to cancel press 0.\n");
    }
    public static void printLookingForDriver(){
        System.out.println("Looking for available driver...\n");
    }
    public static void printNoDriverFound(){System.out.println("Can't find a driver. Try again later.\n");}
    public static void printDriverFound(String name,String lastName, float rating, int time){
        System.out.println("Driver has been found.");
        System.out.println("Driver info: " + name + " " + lastName + " rating: " + String.valueOf(rating));
        System.out.println("Estimated time of your driver arrival: " + String.valueOf(time) + " minutes");
        System.out.println("To accept press 1. To find another driver press 2.");
        System.out.println("To cancel your ride press 0.\n");
    }
    public static void printInfoAboutYourDriver(String name,String lastName, String carPlate, String carModel, String carColor){
        System.out.println("Driver info: ");
        System.out.println("Name: " + name + " " + lastName);
        System.out.println("Car info: ");
        System.out.println("Car plate: " + carPlate +
                "\nCar model: " + carModel +
                "\nCar color: " + carColor + "\n");
    }
    public static void printTimeToDriverArrival(int time){
        System.out.println("Your driver will be in pickup spot in: " + String.valueOf(time) + " minutes.\n");
    }
    public static void printAskForTip(){
        System.out.println("Would you like to tip your driver?  1 - YES, 0 - NO\n");
    }
    public static void printAskHowMuchTip(float money){
        System.out.printf("Insert tip. Currently you have: %.2f zl.\n\n", money);
    }
    public static void printAskForRating(){
        System.out.println("Rate your driver (insert number from 1 to 5).\n");
    }
    public static void printNotEnoughMoney(float price, float money){
        System.out.printf("Not enough money in your wallet. Currently you have: %.2f zl.\n", money);
        System.out.printf("Price of the ride is %.2f zl.\n\n", price);
    }
    public static void printChangeOpt(){
        System.out.println("If you want to change destination press 9.\n" +
                "If you want to manage your wallet press 2.\n" +
                "(To end ride press 0.)\n");
    }
    public static void printChangeDestination(){
        System.out.println("Input new destination.\n");
    }
    public static void printWrongDistance(){
        System.out.println("New destination does not meet the required conditions.\n");
    }
    public static void printDestinationChanged(){
        System.out.println("Destination has been changed.\n");
    }
    public static void printOneDestinationChangePerRide(){System.out.println("Destination can be changed only once per ride.\n");}
}