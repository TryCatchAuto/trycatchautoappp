package model;

public class Driver {
    private String driver_id;
    private String lastName;
    private String firstName;
    private String email;
    private String login;
    private String password;
    private float salary;
    private float rating;
    private String employmentStatus;

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

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public String getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(String driver_id) {
        this.driver_id = driver_id;
    }

    public String getEmploymentStatus() {
        return employmentStatus;
    }

    public void setEmploymentStatus(String employmentStatus) {
        this.employmentStatus = employmentStatus;
    }

    /**
     * prints driver to standard output
     */
    public void print(){
        System.out.print("Driver ID: " + driver_id + " ");
        System.out.print("Last Name: " + lastName + " ");
        System.out.print("First Name: " + firstName + " ");
        System.out.print("Email: " + email + " ");
        System.out.print("Login: " + login + " ");
        System.out.print("Emp Status: " + employmentStatus + " ");
        System.out.println();
    }

    /**
     * prints driver to standard output
     */
    public void print(){
        System.out.println("Driver ID: " + driver_id);
        System.out.println("Last Name: " + lastName);
        System.out.println("First Name: " + firstName);
        System.out.println("Email: " + email);
        System.out.println("Login: " + login);
        System.out.println("Emp Status: " + employmentStatus);
    }
    public static String checkLogIn(DataBaseConnection conn,String login,String password){
        //String result=conn.CheckLoginDriver(login,password);


        return conn.CheckLoginDriver(login,password);
    }


}
