package view;

public class VDriver {

    public static void printMenu(){
     System.out.println("Select what you want to do. Your options are:\n" +
             "1. Start work\n" +
             "2. Account history\n" +
             "3. Request earlier payment\n" +
             "0. Log out (Exit application)");
    }
    public static void printMenuDuringWork(){
        System.out.println("Select what you want to do. Your options are:\n" +
                "1. Finish work\n" +
                "2. Take a break from work");
    }
    public static void printBreak(){
        System.out.println("Press a button to finish your break.");
    }
    public static void printRideApperead(){
        System.out.println("""
                You have an errand available. Do you want to take it?
                1. Yes (Start ride)
                2. No (Reject ride)          
                """);
                //D. Show more details (press D)""");
    }
    public static void askForRating(){
        System.out.println("Rate your passenger (insert number from 1 to 5)");
    }
    public static void wrongRating(){
        System.out.println("Wrong rating number. Try again");
    }
    public static void printAskForSalary(){
        System.out.println("Press 1 to send application for earlier payment or 0 to cancel");
    }
    public static void askForReason(){
        System.out.println("Insert your reason for earlier payment");
    }
    public static void accountHistoryMenu(){
        System.out.println("""
            1. Show income in this month
            2. Show all income
            3. Show every ride this month
            4. Show all rides
            0. Exit
                """);
    }
}
