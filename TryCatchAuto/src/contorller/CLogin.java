package contorller;


import model.DataBaseConnection;
import model.Management;

import static view.VLogin.start_login;
import java.util.Scanner;

/**
 * class used for controlling system-user interactions at the start of the program
 */
public class CLogin {
    /**
     * control over login panel
     *
     * @param conn database connection for interaction with DB
     */
    public static void login_start(DataBaseConnection conn){
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
                boolean flag=true;
                  while(flag) {
                     System.out.println("Management");
                     System.out.println("Login:");
                     login = scanner.nextLine();
                     System.out.println("Password:");
                     password = scanner.nextLine();
                     String checkResult= conn.CheckLoginManagement(login,password);
                     switch (checkResult) {
                         case "badLogin":
                             
                             break;
                         case "badPassword":
                             break;
                         default:
                             CManagment.menu();
                             break;

                     }
                 }

                 break;
             case 4: //to do
                // break;
             default:
                throw new RuntimeException("something went very bad");
            }
    }
}
