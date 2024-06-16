//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import model.*;

import java.sql.SQLException;

import static contorller.CLogin.login_start;


public class Main {
    public static void main(String[] args) throws SQLException {
        DataBaseConnection conn = new DataBaseConnection(5432, "postgres", "2hY76r", "trycatchauto");
        while(true){if(login_start(conn)) break;};
        //        AddPassenger();
//        AddDriverWithCar(
//                "TEST_NAME",
//                "TEST_LAST_NAME",
//                "TEST_EMAIL",
//                "TEST_LOGIN_2",
//                "TEST_PASS",
//                0.0f,
//                0.0f,
//                "EMPL_STATUS",
//                "ABCD1235",
//                "test - model",
//                "test-color",
//                4
//                );
//        AddEmployee();
//        AddRide("DR_3004_V", "P#_10004");
//        AddRide("DR_3004_V", "P#_10004", 2.0f, 4.2f);
//        AddRide("DR_3004_V", "P#_10004", 3.2f,2.1f);
//        AddRide("DR_3004_V", "P#_10004", 4.3f, 2.8f);
//        AddRide("DR_3004_V", "P#_10004", 3.2f, 3.4f);
//        conn.UpdateRatingForDriver();
//        conn.UpdateRatingForPassenger();
//        AddComplaint();
//        AddApplication();

//        var result = conn.SelectBasicDataDriversV2();
//        var array = model.DataBaseConnection.ConvertToStringStream.BasicDriverDataToStringStreamV2(result);
//        array.forEach(System.out::println);

//        var result = conn.SelectBasicDataDrivers();
//        var array = model.DataBaseConnection.ConvertToStringStream.BasicDriverDataToStringStream(result);
//        array.forEach(System.out::println);
//        System.out.println(array.get(3));



//        var result = conn.SelectDriverAndCar();
//        model.DataBaseConnection.ConvertToStringStream.SelectDriverAndCarToShow(result); // tu wyświetli "null" dla hasła, jest to wartośc oczekiwana, więc git

//        var result = conn.SelectOneDriverAndCar("DR_3004_V");
//        model.DataBaseConnection.ConvertToStringStream.SelectDriverAndCarToShow(result);

//        conn.UpdateWalletMoney(100.00f, "P#_10006"); // Zasilenie konta
//        conn.UpdateWalletMoney(-100.00f, "P#_10006"); // Uiszczenie opłaty za przejazd

//        var result = conn.SelectOnePassenger("P#_10006");
//        model.DataBaseConnection.ConvertToStringStream.AllPassengerDataToStringStream(result); wyświetla wszystkie dane, chyba, że w zaytaniu o coś nie pytamy, wtedy daje null

//        var result = conn.SelectBasicAllComplaint();
//        var toShow = model.DataBaseConnection.ConvertToStringStream.AllBasicComplaintDataToStringStream(result);
//        toShow.forEach(System.out::println);

//        var result = conn.SelectBasicOneComplaint("_CMP_4004_cmp");
//        model.DataBaseConnection.ConvertToStringStream.AllComplaintDataToStringStream(result);


//        conn.UpdateComplaintStatus("closed", "_CMP_4004_cmp");


//        var result = conn.SelectComplaint("MG_4");
//        var toShow = model.DataBaseConnection.ConvertToStringStream.AllBasicComplaintDataToStringStream(result);
//        toShow.forEach(System.out::println);

//        var result = conn.SelectApplicationForEarlierSalary("MG_4");

//        model.Passenger passenger = new model.Passenger();
//        passenger.setFirstName("Thomas");
//        passenger.setLastName("Anderson");
//        passenger.setEmail("neo@matrix.com");
//        passenger.setPassword("matrix");
//        conn.InsertPassengerAutoLogin(passenger);


//        model.Driver driver = new model.Driver();
//        driver.setFirstName("Agent");
//        driver.setLastName("Smith");
//        driver.setEmail("agentsmith@matrix.com");
//        driver.setPassword("dorwacneo");
//        driver.setRating(0.0f);
//        driver.setSalary(0.0f);
//        driver.setEmploymentStatus("Zatrudniony");
//
//        model.Car car = new model.Car("RTX4060","Mątwa", "Metaliczna śmierć", 1);
//        conn.InsertDriverWithCarWithAutoLogin(driver, car);



//        model.Driver driver_2 = new model.Driver();
//        driver_2.setFirstName("Ellen");
//        driver_2.setLastName("Ripley");
//        driver_2.setEmail("ellenripley@gmail.com");
//        driver_2.setPassword("alienalien");
//        driver_2.setRating(5.0f);
//        driver_2.setSalary(1000.0f);
//        driver_2.setEmploymentStatus("Zastępca kapitana");
//
//        model.Car car_2 = new model.Car("20922179","Nostromo", "Metaliczny metal", 10000);
//        conn.InsertDriverWithCarWithAutoLogin(driver_2, car_2);



//        model.Management employee = new model.Management();
//        employee.setFirstName("Woody");
//        employee.setLastName("Pride");
//        employee.setEmail("woodyt@toy.com");
//        employee.setPassword("najlepszyszeryf1234");
//        employee.setJobTitle("Szeryf");
//
//        conn.InsertManagementEmployeeWithAutoLogin(employee);
    }


    private static void AddDriverWithCar(
            String Test_First_Name_Driver,
            String Test_Last_Name_Driver,
            String Test_Email_Driver,
            String Test_Login_Driver,
            String Test_Password_Driver,
            float Test_Rating,
            float Test_Salary,
            String Test_Employment_Status,
            String TEST_REJESTRACJA,
            String TEST_MODEL,
            String TEST_COLOR,
            int TEST_SETAS
            ) throws SQLException
    {
        Driver driver = new Driver();
        driver.setFirstName(Test_First_Name_Driver);
        driver.setLastName(Test_Last_Name_Driver);
        driver.setEmail(Test_Email_Driver);
        driver.setLogin(Test_Login_Driver);
        driver.setPassword(Test_Password_Driver);
        driver.setRating(Test_Rating);
        driver.setSalary(Test_Salary);
        driver.setEmploymentStatus(Test_Employment_Status);

        Car car = new Car(TEST_REJESTRACJA,TEST_MODEL, TEST_COLOR, TEST_SETAS);
        DataBaseConnection conn = new DataBaseConnection("2hY76r", "trycatchauto");
        conn.InsertDriverWithCar(driver, car);
    }

    private static void AddPassenger() throws SQLException {
        Passenger passenger = new Passenger();
        passenger.setFirstName("Test_First_Name");
        passenger.setLastName("Test_Last_Name");
        passenger.setEmail("test@test.com");
        passenger.setLogin("Test_Login");
        passenger.setPassword("Test_Password");
        DataBaseConnection conn = new DataBaseConnection("2hY76r", "trycatchauto");
        conn.InsertPassenger(passenger);
    }
    private static void AddEmployee() throws SQLException {
        Management employee = new Management();
        employee.setFirstName("Test_First_Name");
        employee.setLastName("Test_Last_Name");
        employee.setEmail("test@test.com");
        employee.setLogin("Test_Login");
        employee.setPassword("Test_Password");
        employee.setJobTitle("Test_JobTitle");


        DataBaseConnection conn = new DataBaseConnection("2hY76r", "trycatchauto");
        conn.InsertManagementEmployee(employee);
    }
    private static void AddRide(String driver_id, String passenger_id) throws SQLException {
        Ride ride = new Ride();
        String timerStart = "0:0:0";
        String timerEnd = "23:59:59";
        //ride.setDate(model.DataBaseConnection.DateAndTime.GetCurrentDate());
        ride.setDate(DataBaseConnection.DateAndTime.GetRandomizeData(2024, 1, 1));
        ride.setPick_up("Test_Pick_Up");
        ride.setDestination("Test_Destination");
        ride.setPrice(0.0f);
        ride.setRideLength(0.0f);
        ride.setStartTime(DataBaseConnection.DateAndTime.GetRandomizeTime(timerStart));
        ride.setEndTime(DataBaseConnection.DateAndTime.GetRandomizeTime(timerEnd));
        ride.setPassenger_id(passenger_id);
        ride.setDriver_id(driver_id);
        ride.setRatingForPassenger(4.5f);
        ride.setRatingForDriver(4.5f);
        DataBaseConnection conn = new DataBaseConnection("2hY76r", "trycatchauto");
        conn.InsertRide(ride);
    }
    private static void AddRide(String driver_id, String passenger_id, float rating_pass, float rating_dr) throws SQLException {
        Ride ride = new Ride();
        String timerStart = "0:0:0";
        String timerEnd = "23:59:59";
        //ride.setDate(model.DataBaseConnection.DateAndTime.GetCurrentDate());
        ride.setDate(DataBaseConnection.DateAndTime.GetRandomizeData(2024, 1, 1));
        ride.setPick_up("Test_Pick_Up");
        ride.setDestination("Test_Destination");
        ride.setPrice(0.0f);
        ride.setRideLength(0.0f);
        ride.setStartTime(DataBaseConnection.DateAndTime.GetRandomizeTime(timerStart));
        ride.setEndTime(DataBaseConnection.DateAndTime.GetRandomizeTime(timerEnd));
        ride.setPassenger_id(passenger_id);
        ride.setDriver_id(driver_id);
        ride.setRatingForPassenger(rating_pass);
        ride.setRatingForDriver(rating_dr);

        DataBaseConnection conn = new DataBaseConnection("2hY76r", "trycatchauto");
        conn.InsertRide(ride);
    }
    private static void AddComplaint() throws SQLException {
        Complaint complaint = new Complaint();
        complaint.setDescription("Test_Description");
        complaint.setDate(DataBaseConnection.DateAndTime.GetCurrentDate());
        complaint.setStatus("Test_Status");
        complaint.setRide_id("RiD100009");
        complaint.setDriver_id("DR_3004_V");
        complaint.setEmployee_id("MG_4");
        DataBaseConnection conn = new DataBaseConnection("2hY76r", "trycatchauto");
        conn.InsertComplaint(complaint);
    }
    private static void AddApplication() throws SQLException {
        ApplicationForEarlierSalary application = new ApplicationForEarlierSalary();
        application.setStatus("Test_Status");
        application.setDriver_id("DR_3004_V");
        application.setEmployee_id("MG_4");
        application.setDescription("Test_Description");
        application.setDate(DataBaseConnection.DateAndTime.GetCurrentDate());
        DataBaseConnection conn = new DataBaseConnection("2hY76r", "trycatchauto");
        conn.InsertApplicationForEarlierSalary(application);
    }


}


