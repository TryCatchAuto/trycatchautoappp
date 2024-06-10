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
    public static void printLogin(){
        System.out.println("Podaj login:");
    }
    public static void printPassword(){
        System.out.println("Podaj haslo:");
    }
    public static void wrongData(){
        System.out.println("Incorrect login or password. Press 1 to try again or 0 to exit application");
    }
    public static void printNoUser(){
        System.out.println("Niepoprawny login.");
    }
    public static void printWrongPassword(){
        System.out.println("Bledne haslo.");
    }
    public static void printSomeError(){
        System.out.println("Error occurred  ");
    }
}
