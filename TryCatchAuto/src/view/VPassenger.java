package view;

public class VPassenger {
    public static void printMenu(){
        System.out.println("0. Exit.");
        System.out.println("1. Order a ride.");
        System.out.println("2. Manage your wallet.");
        System.out.println("3. Show history.");
    }
    public static void printGiveDestination(){
        System.out.println("Input destination.");
    }
    public static void printGiveLocation(){
        System.out.println("Input pickup location.");
    }
    public static void printGiveNOfPeople(){
        System.out.println("How many passengers (1,4).");
    }
    public static void printShowPrice(float price){
        System.out.println("Price of the ride: " + String.valueOf(price) + " zl.");
        System.out.println("To accept choose 1, to cancel press 0");
    }
    public static void printLookingForDriver(){
        System.out.println("Looking for available driver...");
    }
    public static void printDriverFound(String name,String lastName, float rating, int time){
        System.out.println("Driver has been found.");
        System.out.println("Driver info: " + name + " " + lastName + " rating: " + String.valueOf(rating));
        System.out.println("Estimated time of your driver arrival: " + String.valueOf(time) + " minutes");
        System.out.println("To accept press 1. To find another driver press 2.");
        System.out.println("To cancel your ride press 0.");
    }
    public static void printTimeToDriverArrival(int time){
        System.out.println("Your driver will be in pickup spot in: " + String.valueOf(time) + " minutes");
    }
    public static void printAskForTip(){
        System.out.println("Would you like to tip your driver?  1 - YES, 0 - NO");
    }
    public static void printAskHowMuchTip(float money){
        System.out.println("Insert tip. Currently you have: " + String.valueOf(money) + " zl.");
    }
    public static void printAskForRating(){
        System.out.println("Rate your driver (insert number from 1 to 5)");
    }
    public static void printNotEnoughMoney(float price, float money){
        System.out.println("Not enough money in your wallet. Currently you have: " + String.valueOf(money) + " zl.");
        System.out.println("Price of the ride is " + String.valueOf(price) + " zl.");
    }
    public static void printChangeOpt(){
        System.out.println("If you want to change destination press 9");
    }
    public static void printChangeDestinaton(){
        System.out.println("Input new destination.");
    }
    public static void printWrongDistance(){
        System.out.println("New destination does not meet the required conditions.");
    }
    public static void printDestinationChanged(){
        System.out.println("Destination has been changed.");
    }
    public static void printMenuWallet(){
        System.out.println("0. Exit.");
        System.out.println("1. Add card.");
        System.out.println("2. Add funds.");
    }
    public static void printAddFunds(){
        System.out.println("Enter amount of funds to add.");
    }
    public static void printMoneyAdded(){
        System.out.println("Money has been added to your wallet.");
    }
}
