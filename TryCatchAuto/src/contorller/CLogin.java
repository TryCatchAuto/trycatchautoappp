package contorller;


import model.Management;

import static view.VLogin.start_login;
import java.util.Scanner;

public class CLogin {
    public static void login_start(){
        Scanner scanner=new Scanner(System.in);
        start_login();
        int choice;
        while(true) {
            try {
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
                scanner.nextLine();
                break;
            } catch(Exception e) {
                System.out.println("Invalid input\n Try again:");
                scanner.nextLine();
            }
        }
        String login;
        String password;
        switch (choice) {
             case 1: //to do
                break;
             case 2: //to do
                 break;
            case 3:
                 logging: while(true) {
                     System.out.println("Management");
                     System.out.println("Login:");
                     login = scanner.nextLine();
                     System.out.println("Password:");
                     password = scanner.nextLine();
                     int check= Management.chcekLoginAndPassword(login, password);
                     switch (check){
                         case 0: break logging;
                         case 1: System.out.println("invalid login");break;
                         case 2: System.out.println("invalid password");break;
                     }
                 }
                 CManagment.menu();
                 break;
             case 4: //to do
                // break;
             default:
                throw new RuntimeException("something went very bad");
            }
    }
}
