package view;

public class VWallet {
    public static void printMenuWallet(){
        System.out.println("0. Exit.");
        System.out.println("1. Add card.");
        System.out.println("2. Add funds.\n");
    }
    public static void printAddFunds(){
        System.out.println("Enter amount of funds to add.\n");
    }
    public static void printMoneyAdded(){
        System.out.println("Money has been added to your wallet.\n");
    }
    public static void printNoCreditCard(){System.out.println("To add funds, add a credit card.\n");}
    public static void printAddCard(){System.out.println("Enter card number.\n");}
    public static void printCreditCardAdded(){System.out.println("Your credit card has been added to your wallet.\n");}
    public static void printCreditCardChanged(){System.out.println("Your credit cart has been changed.\n");}
    public static void printYourMoney(float money){System.out.println("Currently you have: " + String.valueOf(money)  + " zl.\n" );}
}
