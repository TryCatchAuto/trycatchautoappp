package contorller;


import model.DataBaseConnection;

import model.DataBaseConnection;
import model.Driver;
import model.Passenger;
import view.VDriver;
import view.VLogin;

import static contorller.CPassenger.Pmenu;
import static model.Passenger.chceckLogIn;
import static view.VLogin.*;

import static view.VLogin.start_login;
import java.util.Scanner;
import view.VPassenger.*;

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
                if(choice>=1&&choice<=4) {
                    break;
                }
            } catch(Exception e) {
                System.out.println("Invalid input\n Try again:");
                scanner.nextLine();
            }
        }
        String login;
        String password;
        switch (choice) {
             case 1: printLogin();
                 while(true) {
                     try {
                         login = scanner.next();
                         break;
                     } catch(Exception e) {
                         System.out.println("Invalid input\n Try again:");
                     }
                 }
                 printPassword();
                 while(true) {
                     try {
                         password = scanner.next();
                         break;
                     } catch(Exception e) {
                         System.out.println("Invalid input\n Try again:");
                     }
                 }
                 String result;
                 result = chceckLogIn(conn,login,password);
                 switch (result){
                     case "badLogin":
                         //brak uzytkownika
                         printNoUser();
                         break;
                     case "badPassword":
                         //bledne haslo
                         printWrongPassword();
                         break;
                     default:
                         //poprawne logowanie
                         Passenger passenger = conn.SelectOnePassenger(result);
                         passenger.setWallet(conn.SelectDataForWallet(result));
                         Pmenu(passenger, conn);
                         break;
                 }
                break;
            case 2:
                    String driverID=loginDriver(conn);
                    Driver driver=conn.SelectOneDriverAndCar(driverID).getFirst().getFirst();
                    CDriver.driverMenu(conn,driver);
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
                             System.out.println("Invalid login");
                             System.out.println("Enter 'exit' to exit or press enter to continue");
                             checkResult=scanner.nextLine();
                             if(checkResult.equals("exit")) {
                                 flag=false;
                             }
                             break;
                         case "badPassword":
                             System.out.println("Invalid Password");
                             System.out.println("Enter 'exit' to exit or press enter to continue");
                             checkResult=scanner.nextLine();
                             if(checkResult.equals("exit")) {
                                 flag=false;
                             }
                             break;
                         default:
                             CManagement.menu(conn,checkResult);
                             flag=false;
                             break;

                     }
                 }
                 break;
             case 4: //to do
                 break;
             default:
                throw new RuntimeException("something went very bad");
        }
    }
    private static String loginDriver(DataBaseConnection conn){
        Scanner scanner=new Scanner(System.in);
        VLogin.printLogin();
        String login=scanner.nextLine();
        VLogin.printPassword();
        String password=scanner.nextLine();
        String id=Driver.checkLogIn(conn,login,password);
        //int choice=1;
        while((id.equals("badLogin")||id.equals("badPassword"))){
            VLogin.wrongData();
            int choice=scanner.nextInt();
            if(choice==0){
                break;
            }
            VLogin.printLogin();
            login=scanner.nextLine();
            password=scanner.nextLine();
            id=Driver.checkLogIn(conn,login,password);
        }
        return id;

    }
}
