package contorller;

import model.Complaint;
import model.DataBaseConnection;
import model.Management;
import view.VManagement;
import java.util.Scanner;

public class CManagment {
public static void menu(DataBaseConnection conn,String managmentId){
    Management logged= conn.SelectOneEmployee(managmentId);
    Scanner sc = new Scanner(System.in);
    VManagement.menu(logged.getLogin());
    int choice;
    while(true) {
        try {
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();
            if (choice>=1 && choice<=4) {
                break;
            }
        } catch(Exception e) {
            System.out.println("Invalid input\n Try again:");
            sc.nextLine();
        }
    }
    switch(choice) {
        case 1: CComplaints(logged,conn);
            break;
        case 2: //to do
            break;
        case 3: //to do
            break;
        case 4: break;
        default: throw new RuntimeException("Something went wrong");
    }
}
public static void CComplaints(Management logged,DataBaseConnection conn){
    Scanner sc = new Scanner(System.in);
    var complaints=conn.SelectBasicAllComplaint();
    VManagement.VComplaints(logged.getLogin(), complaints);
    System.out.print("Enter complaintId or 'q' to quit: ");
    String complaintId = sc.nextLine();
    while(!complaintId.equals("q")) {
        try{
            int id = Integer.parseInt(complaintId);

        }catch (NumberFormatException e){
            System.out.println("Invalid input\n Try again:");
        }
    }
}
public static void CComplaintResolve(DataBaseConnection conn, Complaint complaint,Management logged){
    Scanner sc = new Scanner(System.in);
    VManagement.VComplaintResolve(logged.getLogin(), complaint);
    String description="";
    float price=-1.0f;
    String answer = sc.nextLine();
    boolean flag=true;
    while(flag)
        {
            System.out.println("Do you want to resolve the complaint? (y/n)");
            if (answer.equals("y")) {
                System.out.println("Do you want to rule in favour of the Driver? (y/n)");
                answer = sc.nextLine();
                if (answer.equals("y")) {
                    while (description.isEmpty() || description.isBlank()) {
                        System.out.println("Provide explanation for your decision");
                        description = sc.nextLine();
                    }
                    while (price == -1.0f) {
                        try {
                            System.out.println("Set price of fine: ");
                            price = Float.parseFloat(description);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input\n Try again:");
                        }
                    }
                    System.out.println("Are you sure you want to resolve the complaint in favour of driver?" +
                            "\ndescription" + description + "\nprice" + price + "\n(y/n)");
                    if (sc.nextLine().equals("y")) {
                        complaint.setStatus("Resolved");
                        logged.complainResolved(conn,complaint, description, price, true);
                        flag=false;
                    }
                }
                else{
                    while (description.isEmpty() || description.isBlank()) {
                        System.out.println("Provide explanation for your decision");
                        description = sc.nextLine();
                    }
                   price=0f;
                    System.out.println("Are you sure you want to resolve the complaint in favour of passenger?" +
                            "\ndescription" + description + "\nprice" + price + "\n(y/n)");
                    if (sc.nextLine().equals("y")) {
                        complaint.setStatus("Resolved");
                        logged.complainResolved(conn,complaint, description, price, false);
                        flag=false;
                    }
                }
            } else if (answer.equals("n")) {
                flag=false;
            }
    }

}

    public static void CAddDriver(DataBaseConnection conn, Management logged){
    Scanner sc = new Scanner(System.in);

    }
}
