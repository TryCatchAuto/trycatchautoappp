package model;

public class Management {
    private String lastName;
    private String firstName;
    private String email;
    private String login;
    private String password;
    private String jobTitle;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    /**
     * Sends information to driver and in case complaint was accepted also to Passenger
     * with price and explanation of the decision
     * @param complaint complain which was resolved
     * @param Description explanation of the decision
     * @param price price of the fine
     * @param accepted indication if complained was accepted or not
     */
    public void complainResolved(Complaint complaint, String Description, float price, boolean accepted){
        // not implemented in prototype
    }
}
