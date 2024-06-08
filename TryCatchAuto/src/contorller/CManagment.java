package contorller;

import view.VManagement;
import java.util.Scanner;

public class CManagment {
public static void menu(){
    //maybe add management to function arguments
    Scanner sc = new Scanner(System.in);
    VManagement.menu();
    int choice;
    while(true) {
        try {
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();
            break;
        } catch(Exception e) {
            System.out.println("Invalid input\n Try again:");
            sc.nextLine();
        }
    }
    switch(choice) {
        case 1: //to do
            break;
        case 2: //to do
            break;
        case 3: //to do
            break;
        case 4: break;
        default: throw new RuntimeException("Something went wrong");
    }
}
}
