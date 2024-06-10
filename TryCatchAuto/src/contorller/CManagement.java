package contorller;

import model.*;
import view.VManagement;
import java.util.Scanner;

/**
 * controls Employee side of System
 * gives option to add new driver,
 * resolve complaint and resolve application for earlier salary
 */

public class CManagement {
    /**
     * Controls management menu
     * @param conn connection to DB
     * @param managementId of Management Employee
     */
    public static void menu(DataBaseConnection conn,String managementId){
    Management logged= conn.SelectOneEmployee(managementId);
    Scanner sc = new Scanner(System.in);
    VManagement.menu(logged.getLogin());
    int choice=4;
    boolean flag=true;
    while(flag) {
        while (true) {
            try {
                System.out.print("Enter your choice: ");
                choice = sc.nextInt();
                sc.nextLine();
                if (choice >= 1 && choice <= 4) {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Invalid input\n Try again:");
                sc.nextLine();
            }
        }
        switch (choice) {
            case 1:
                CComplaints(logged, conn);
                break;
            case 2:
                CApplicationForEarlierSalary(conn, logged, managementId);
                break;
            case 3:
                CAddDriver(conn);
                break;
            case 4:
                flag = false;
                break;
            default:
                throw new RuntimeException("Something went wrong");
        }
    }
}

    /**
     * controls Complaints screen
     * @param logged management employee
     * @param conn connection to DB
     */
    public static void CComplaints(Management logged,DataBaseConnection conn){
    Scanner sc = new Scanner(System.in);
    var complaints=conn.SelectBasicAllComplaint().stream()
            .filter(complaint -> complaint.getStatus().equals("Open")).toList();
    VManagement.VComplaints(logged.getLogin(), complaints);

    //boolean flag=true;
    while(true) {
        System.out.print("Enter complaintId or 'q' to quit: ");
        String complaintId = sc.nextLine();
        try{
            if(complaintId.equals("q")) {
                //flag=false;
                break;
            }

            int id = Integer.parseInt(complaintId);
            if(id>=1&&id<= complaints.size()) {
                CComplaintResolve(conn, complaints.get(id - 1), logged);
                break;
            }
        }catch (NumberFormatException e){
            System.out.println("Invalid input\n Try again:");
        }
    }
}

    /**
     * controls Complaint resolvent process
     * @param conn connection to DB
     * @param complaint Complaint to be resolved
     * @param logged management employee
     */
    public static void CComplaintResolve(DataBaseConnection conn, Complaint complaint,Management logged){
    Scanner sc = new Scanner(System.in);
    VManagement.VComplaintResolve(logged.getLogin(), complaint);
    String description="";
    String fine="";
    float price=-1.0f;

    boolean flag=true;
    while(flag)
        {
            System.out.println("Do you want to resolve the complaint? (y/n)");
            String answer = sc.nextLine();
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
                            fine = sc.nextLine();
                            price = Float.parseFloat(fine);
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

    /**
     * Adds new driver and a car to DB
     * @param conn DB connection
     */
    public static void CAddDriver(DataBaseConnection conn){
        Scanner sc = new Scanner(System.in);
        Driver driver=new Driver();
        Car driverCar=new Car();
        //add driver
        System.out.print("Enter Driver Name: ");
        String driverName = sc.nextLine();
        driver.setFirstName(driverName);
        System.out.print("Enter Driver Surname: ");
        String driverSurname = sc.nextLine();
        driver.setLastName(driverSurname);
        System.out.print("Enter Driver Email: ");
        String driverEmail = sc.nextLine();
        driver.setEmail(driverEmail);
        System.out.print("Enter Driver Login: ");
        String driverLogin = sc.nextLine();
        driver.setLogin(driverLogin);
        boolean passwordsAreInCorrect = true;
        while(passwordsAreInCorrect) {
            System.out.print("Enter Driver Password: ");
            String driverPassword = sc.nextLine();
            System.out.print("Enter Driver Confirm Password: ");
            String driverConfirmPassword = sc.nextLine();
            if (driverConfirmPassword.equals(driverPassword)) {
                passwordsAreInCorrect = false;
                driver.setPassword(driverPassword);
            }else{
                System.out.println("Passwords do not match");
            }
        }
        driver.setEmploymentStatus("Zatrudniony");
        driver.setRating(0.0f);
        driver.setDriver_id("");
        //add car
        System.out.print("Enter Car Plates");
        String carPlates = sc.nextLine();
        driverCar.setPlates(carPlates);
        System.out.print("Enter Car Model");
        String carModel = sc.nextLine();
        driverCar.setModel(carModel);
        System.out.print("Enter Car color");
        String carColor = sc.nextLine();
        driverCar.setColor(carColor);
        while(true) {
            System.out.print("Enter Number of Seats");
            String seats = sc.nextLine();
            try {
                int seatsNum = Integer.parseInt(seats);
                if(seatsNum>=1 && seatsNum<=8) {
                    driverCar.setSeats(seatsNum);
                    break;
                }
            }catch (NumberFormatException e){
                System.out.println("Invalid input\n Try again:");
            }
        }
        driverCar.setCar_id("");
        driver.print();
        driverCar.print();
        while(true) {
            System.out.println("Are you sure you want to Add Driver with this car? (y/n)");
            if (sc.nextLine().equals("y")) {
                conn.InsertDriverWithCar(driver,driverCar);
                System.out.print("Driver Added Successfully");
                break;
            }else if (sc.nextLine().equals("n")) {
                break;
            }
            else{
                System.out.println("Invalid input\n Try again:");
            }
        }


    }

    /**
     * controls screen with applications for earlier salary
     * here you can pick one and then resolve it
     * @param conn DB connection
     * @param logged logged employee
     * @param key employee ID
     */
    public static void CApplicationForEarlierSalary(DataBaseConnection conn,Management logged,String key){
        Scanner sc = new Scanner(System.in);
        //gets all applications to resolve for this employee from DB
        var applications=conn.SelectAllDataApplicationForEarlierSalary(key).stream().
                filter(x->x.getStatus().equals("Pending")).toList();
        VManagement.VAFES(logged.getLogin(),applications);

        boolean flag=true;
        while(flag) {
            System.out.print("Enter applicationId or 'q' to quit: ");
            String applicationId = sc.nextLine();
            try{
                if(applicationId.equals("q")) {
                    flag=false;
                }

                int id = Integer.parseInt(applicationId);
                if(id>=1&&id<= applications.size()) {
                    CResolveApplicationForEarlierSalary(conn, applications.get(id - 1), logged);
                    break;
                }
            }catch (NumberFormatException e){
                System.out.println("Invalid input\n Try again:");
            }
        }


    }

    /**
     * Controls resolvent of Application for earlier salary
     * @param conn DB connection
     * @param applicationForEarlierSalary application to resolve
     * @param logged logged employee
     */
    private static void CResolveApplicationForEarlierSalary(DataBaseConnection conn, ApplicationForEarlierSalary applicationForEarlierSalary, Management logged) {
        Scanner sc = new Scanner(System.in);
        VManagement.VResolveAFES(logged.getLogin(), applicationForEarlierSalary);

        boolean flag=true;
        while(flag) {
            System.out.print("Do you approve the application for earlier salary? (y/n) ('q' to quit) ");
            String choice = sc.nextLine();
            if (choice.equals("y")) {
                applicationForEarlierSalary.updateStatus(true);
                flag=false;
            } else if (choice.equals("n")) {
                applicationForEarlierSalary.updateStatus(false);
                flag=false;
            } else if (choice.equals("q")) {
                flag=false;
            } else{
                System.out.println("Invalid input\n Try again:");
            }
        }
    }
}
