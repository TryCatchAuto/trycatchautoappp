package contorller;

import model.DataBaseConnection;
import model.Passenger;
import java.util.Scanner;
import static view.VWallet.*;
import static view.VWallet.printYourMoney;

public class CWallet {
    public static void WalletMenu(DataBaseConnection conn, Passenger passenger){
        int optWallet = -1;
        while (optWallet != 0) {
            printYourMoney(passenger.getWallet().getMoney());
            printMenuWallet();
            Scanner scan = new Scanner(System.in);
            optWallet = scan.nextInt();
            switch (optWallet) {
                case 1:
                    //add card
                    passenger.addCard(conn);
                    break;
                case 2:
                    passenger.addFunds(conn);
                    break;
            }
        }
    }
}