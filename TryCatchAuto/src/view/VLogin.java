package view;

/**
 * class used to display login screens in console
 */
public class VLogin {
    /**
     * displaying first login screen to choose which account you want to lo into
     */
    public static void start_login(){
        System.out.println("Choose which account you want to log in to:");
        System.out.println("1. Passenger");
        System.out.println("2. Driver");
        System.out.println("3. Management");
        System.out.println("4. Quit");
    }
}
