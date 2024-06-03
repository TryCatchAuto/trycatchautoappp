package contorller;


import static view.VLogin.start_login;
import java.util.Scanner;

public class CLogin {
    public static void login_start(){
        Scanner scanner=new Scanner(System.in);
        start_login();
        int choice;
        while(true) {
            try {
                choice = scanner.nextInt();
                break;
            } catch(Exception e) {
                System.out.println("Invalid input\n Try again:");
            }
        }
        switch (choice){
            case 1: //to do
                break;
            case 2: //to do
                break;
            case 3: //to do
                break;
            case 4: //to do
                break;
            default:
                throw new RuntimeException("something went very bad");
        }
    }
}
