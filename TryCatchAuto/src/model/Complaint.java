package model;

import java.sql.*;

public class Complaint {
    private String complaint_id;
    private String description;
    private Date date;
    private String status;
    private String ride_id;
    private String driver_id;
    private String employee_id;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRide_id() {
        return ride_id;
    }

    public void setRide_id(String ride_id) {
        this.ride_id = ride_id;
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

    public String getComplaint_id() {
        return complaint_id;
    }

    public void setComplaint_id(String complaint_id) {
        this.complaint_id = complaint_id;
    }

    public void printComplaint() {
        System.out.print("Complaint ID: " + complaint_id);
        System.out.print("Description: " + description);
        System.out.print("Date: " + date);
        System.out.print("Status: " + status);
        System.out.print("Ride ID: " + ride_id);
        System.out.print("Driver ID: " + driver_id);
        System.out.print("Employee ID: " + employee_id);
        System.out.println();
    }
}
