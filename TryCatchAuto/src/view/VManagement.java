package view;

import model.ApplicationForEarlierSalary;
import model.Complaint;
import model.Management;

/**
 * Screen displays for management account
 */
public class VManagement {
    /**
     * menu display for management account
     */
    public static void menu(String login){
        //maybe add management to function arguments
        System.out.println("Welcome " + login);
        System.out.println("1. Complaints");
        System.out.println("2. Driver's earlier payment requests");
        System.out.println("3. Add driver");
        System.out.println("4. Exit");
    }

    public static void VComplaints(String login, java. util. List<model. Complaint> complaints){
        System.out.println("Logged " + login);
        System.out.println("Complaints:");
        int index=1;
        for(var complaint : complaints){
            System.out.print(index++);
            System.out.print(". ");
            complaint.printComplaint();
        }
    }
    public static void VComplaintResolve(String login, Complaint complaint){
        System.out.println("Logged " + login);
        System.out.println("Complaint:");
        complaint.printComplaint();
        
    }

    public static void VAFES(String logged,java. util. List<ApplicationForEarlierSalary> applications){
        System.out.println("Logged : "+logged);
        System.out.println("Applications:");
        int index=1;
        for(var app : applications){
            System.out.print(index++);
            System.out.print(". ");
            app.print();
        }
    }

    public static void VResolveAFES(String logged, ApplicationForEarlierSalary application){
        System.out.println("Logged : "+logged);
        System.out.println("Application:");
        application.print();

    }

}
