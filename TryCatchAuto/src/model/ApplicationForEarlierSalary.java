package model;

import java.sql.Date;

public class ApplicationForEarlierSalary {
    private String status;
    private String description;
    private Date date;
    private String driver_id;
    private String employee_id;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(String driver_id) {
        this.driver_id = driver_id;
    }

    public String getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(String employee_id) {
        this.employee_id = employee_id;
    }

    /**
     * prints Application for earlier salary to std out
     */
    public void print(){
        System.out.print("Status: " + status + "  ");
        System.out.print("Description: " + description + "  ");
        System.out.print("Date: " + date+"  ");
        System.out.print("Driver ID: " + driver_id + "  ");
        System.out.print("Employee ID: " + employee_id + "  ");
        System.out.println();
    }

    /**
     * prints basic info about AFES
     */
    public void printBasicInfo(){
        System.out.print("Status: " + status + "  ");
        System.out.print("Date: " + date+"  ");
        System.out.print("Driver ID: " + driver_id + "  ");
        System.out.println();
    }

    /**
     * update status of Application for earlier salary in DB
     * @param approved indication if application was approved or not
     */
    public void updateStatus(boolean approved){
        String status ="";
        if(approved){
            status = "Approved";
        }else{
            status = "Rejected";
        }
        //DB update status

    }
}
