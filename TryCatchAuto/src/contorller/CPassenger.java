package contorller;
import model.DataBaseConnection;
import model.Passenger;

import java.util.Scanner;

import static view.VPassenger.*;

public class CPassenger {
    public static void Pmenu(Passenger passenger, DataBaseConnection conn) {
        System.out.println("Jestes w menu pasazera");
        boolean quit = false;
        while (!quit) {
            printMenu();
            Scanner scan = new Scanner(System.in);
            int option = -1;
            try {
                option = scan.nextInt();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            switch (option) {
                case 0:
                    //quit
                    quit = true;
                    break;
                case 1:
                    //zamawiamy przejazd
                    passenger.orderRide(conn);
                    break;
                case 2:
                    //portfel
                    int optWallet = -1;
                    while (optWallet != 0) {
                        printMenuWallet();
                        optWallet = scan.nextInt();
                        switch (optWallet) {
                            case 1:
                                break;
                            case 2:
                                passenger.addFunds(conn);
                                break;
                        }
                    }
                    break;
                case 3:
                    //historia
                    break;
                default:
                    System.out.println("Invalid input. Try again.");
            }
        }
    }

}
