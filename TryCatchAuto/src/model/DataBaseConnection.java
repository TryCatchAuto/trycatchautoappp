package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DataBaseConnection {
    private final String user;
    private final String pass;
    private final String url;


    public DataBaseConnection(String pass, String dbName) {
        this.user = "postgres";
        this.pass = pass;
        url = "jdbc:postgresql://localhost:5432/" + dbName;
    }

    public DataBaseConnection(int port, String user, String pass, String dbName) {
        this.user = user;
        this.pass = pass;
        url = "jdbc:postgresql://localhost:" + port + "/" + dbName;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, pass);
    }



    public static class DateAndTime {
        public static java.sql.Date GetCurrentDate() {
            java.util.Date date = new java.util.Date();
            return new java.sql.Date(date.getTime());
        }

        public static java.sql.Date GetRandomizeData(int year, int month, int day) {
            java.util.Calendar calendar = java.util.Calendar.getInstance();
            calendar.set(year, month - 1, day);
            java.util.Date date = calendar.getTime();
            return new java.sql.Date(date.getTime());
        }

        public static java.sql.Time GetRandomizeTime(String time) {
            return Time.valueOf(time);
        }
    }

    public static class ConvertToStringStream {

        //-------------------
        //
        // METODY DO KIEROWCY
        //
        //-------------------

        // returns: All
        //

        public static List<String> AllDriverDataToStringStream(List<Driver> drivers) {
            return drivers.stream()
                    .map(driver -> driver.getDriver_id()
                            + " | " + driver.getFirstName()
                            + " | " + driver.getLastName()
                            + " | " + driver.getEmail()
                            + " | " + driver.getLogin()
                            + " | " + driver.getRating()
                            + " | " + driver.getSalary()
                            + " | " + driver.getPassword())
                    .collect(Collectors.toList());
        }

        // returns: driver_id, firstName, lastName, rating
        //
        public static List<String> BasicDriverDataToStringStream(List<Driver> drivers) {
            return drivers.stream()
                    .map(driver -> driver.getDriver_id()
                            + " | " + driver.getFirstName()
                            + " | " + driver.getLastName()
                            + " | " + driver.getRating())
                    .collect(Collectors.toList());
        }

        public static List<String> BasicDriverDataToStringStreamV2(List<Driver> drivers) {
            return drivers.stream()
                    .map(driver -> driver.getDriver_id()
                            + " | " + driver.getFirstName()
                            + " | " + driver.getLastName()
                            + " | " + driver.getRating()
                            + " | " + driver.getEmploymentStatus())
                    .collect(Collectors.toList());
        }

        // returns: All, without: model.Driver.password
        //
        public static void SelectDriverAndCarToShow(List<PairDriverWithCar<Driver, Car>> pairs) {
            for (var pair : pairs) {
                Driver driver = pair.getFirst();
                Car car = pair.getSecond();
                System.out.print(driver.getDriver_id() + " | " +
                        driver.getFirstName() + " | " +
                        driver.getLastName() + " | " +
                        driver.getLogin() + " | " + driver.getEmail() + " | " + driver.getSalary() + " | " + driver.getRating() + " | " +
                        driver.getEmploymentStatus());
                System.out.println(car.getPlates() + " | " + car.getModel() + " | " + car.getColor() + " | " + car.getSeats() + " | " + car.getCar_id());
            }
        }


        //-------------------
        //
        // METODY DO PASAŻERA
        //
        //-------------------


        public static void AllPassengerDataToStringStream(Passenger passenger) {
            System.out.println(passenger.getPassenger_id() +
                    " | " + passenger.getFirstName() +
                    " | " + passenger.getLastName() +
                    " | " + passenger.getEmail() +
                    " | " + passenger.getLogin() +
                    " | " + passenger.getRating() +
                    " | " + passenger.getPassword());
        }


        //-------------------
        //
        // METODY DO SKARGI
        //
        //-------------------


        // returns: id, date, status, ride_id
        //
        public static List<String> AllBasicComplaintDataToStringStream(List<Complaint> complaints) {
            return complaints.stream()
                    .map(complaint -> complaint.getComplaint_id()
                            + " | " + complaint.getDate()
                            + " | " + complaint.getStatus()
                            + " | " + complaint.getRide_id())
                    .collect(Collectors.toList());
        }


        // returns: All
        //
        public static void AllComplaintDataToStringStream(Complaint complaint) {
            System.out.println(complaint.getComplaint_id() +
                    " | " + complaint.getDescription() +
                    " | " + complaint.getDate() +
                    " | " + complaint.getStatus() +
                    " | " + complaint.getRide_id() +
                    " | " + complaint.getDriver_id() +
                    " | " + complaint.getEmployee_id());
        }
    }


    public static class PairDriverWithCar<D, C> {
        private final D first;
        private final C second;

        public PairDriverWithCar(D first, C second) {
            this.first = first;
            this.second = second;
        }

        public D getFirst() {
            return first;
        }

        public C getSecond() {
            return second;
        }
    }


    private static final String INSERT_SQL_WALLET = "INSERT INTO Wallet (money, creditCard) VALUES(?, ?)";
    private static final String INSERT_SQL_PASSENGER = "INSERT INTO Passenger (lastName, firstName, email, login, password, rating, wallet_id) VALUES(?, ?, ?, ?, ?, ?, ?)";
    private static final String INSERT_SQL_CAR = "INSERT INTO Car (plates, model, color, seats) VALUES (?,?,?,?)";
    private static final String INSERT_SQL_DRIVER = "INSERT INTO Driver (firstName, lastName, email, login, password, salary, rating, car_id, employmentStatus) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String INSERT_SQL_MANAGEMENT_EMPLOYEE = "INSERT INTO Management (firstName, lastName, email, login, password, jobTitle) VALUES (?,?,?,?,?,?)";
    private static final String INSERT_SQL_RIDE = "INSERT INTO Ride (\"date\", pick_up, destination, price, rideLength, startTime, endTime, passenger_id, driver_id, ratingForPassenger, ratingForDriver) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
    private static final String INSERT_SQL_COMPLAINT = "INSERT INTO Complaint (description, \"date\", status, ride_id, driver_id, employee_id) VALUES(?,?,?,?,?,?)";
    private static final String INSERT_SQL_APPLICATION_FOR_EARLIER_SALARY = "INSERT INTO ApplicationForEarlierSalary (status, description, \"date\", driver_id, employee_id) VALUES (?,?,?,?,?)";


    private static final String SELECT_SQL_ALL_DRIVERS = "SELECT * FROM Driver";
    private static final String SELECT_SQL_BASIC_DATA_DRIVER = "SELECT driver_id, firstName, lastName, rating FROM Driver;";
    private static final String SELECT_SQL_BASIC_PASSENGER = "SELECT Passenger.passenger_id, Passenger.firstName, Passenger.lastName,passenger.email, passenger.rating FROM passenger WHERE passenger_id = ?;";
    private static final String SELECT_SQL_BASIC_EMPLOYMENT_STATUS_DATA_DRIVER = "SELECT driver_id, firstName, lastName, rating, employmentStatus FROM Driver;";
    private static final String SELECT_SQL_ALL_DRIVERS_AND_CARS =
            "SELECT \n" +
                    "    driver.driver_id,\n" +
                    "    driver.firstName,\n" +
                    "    driver.lastName,\n" +
                    "    driver.email,\n" +
                    "    driver.login,\n" +
                    "    driver.salary,\n" +
                    "    driver.rating, \n" +
                    "    driver.employmentStatus, \n" +
                    "    car.plates,\n" +
                    "    car.model,\n" +
                    "    car.color,\n" +
                    "    car.seats,\n" +
                    "    car.car_id\n" +
                    "FROM driver\n" +
                    "JOIN car ON driver.car_id = car.car_id;";
    private static final String SELECT_SQL_BASIC_COMPLAINT = "SELECT complaint_id, date, status, ride_id FROM Complaint;";
    private static final String SELECT_SQL_BASIC_COMPLAINT_FOR_ONE_EMPLOYEE = "SELECT complaint_id, date, status, ride_id FROM Complaint WHERE employee_id = ?;";
    private static final String SELECT_SQL_ALL_COMPLAINT = "SELECT * FROM Complaint WHERE complaint_id = ?;";
    private static final String SELECT_SQL_BASIC_AFES = "SELECT id, date, status FROM ApplicationForEarlierSalary WHERE employee_id = ?;";
    private static final String SELECT_SQL_ALL_DATA_AFES = "SELECT * FROM ApplicationForEarlierSalary WHERE id = ?;";


    private static final String UPDATE_SQL_WALLET = "UPDATE Wallet SET creditCard = ? WHERE wallet_id IN (SELECT wallet_id FROM Passenger WHERE passenger_id = ?);";
    private static final String UPDATE_SQL_MONEY_WALLET = "UPDATE Wallet SET money = money + ? WHERE wallet_id IN (SELECT wallet_id FROM Passenger WHERE passenger_id = ?);";
    private static final String UPDATE_SQL_STATUS_COMPLAINT = "UPDATE Complaint SET status = ? WHERE complaint_id = ?;";
    private static final String UPDATE_SQL_RATING_DRIVER = "UPDATE Driver SET rating = (\n SELECT ROUND(COALESCE(AVG(Ride.ratingForDriver), 0.0))\n FROM Ride \n WHERE Driver.driver_id = Ride.driver_id\n);";
    private static final String UPDATE_SQL_RATING_PASSENGER = "UPDATE Passenger SET rating = (SELECT ROUND(COALESCE(AVG(Ride.ratingForPassenger), 0.0)) FROM Ride WHERE Passenger.passenger_id = Ride.passenger_id);";
    private static final String UPDATE_SQL_LOGIN_FOR_AUTO_INSERT_PASSENGER = "UPDATE Passenger SET login = LOWER(LEFT(lastName, 1)) || LOWER(LEFT(firstName, 1)) || (currval('passenger_id_seq') + 10) || currval('passenger_id_seq')  WHERE passenger_id = ?;";
    private static final String UPDATE_SQL_LOGIN_FOR_AUTO_INSERT_DRIVER = "UPDATE Driver SET login = LOWER(LEFT(lastName, 1)) || LOWER(LEFT(firstName, 1)) || (currval('driver_id_seq') + 10) || currval('driver_id_seq')  WHERE driver_id = ?;";
    private static final String UPDATE_SQL_LOGIN_FOR_AUTO_INSERT_MANAGEMENT = "UPDATE Management SET login = LOWER(LEFT(lastName, 1)) || LOWER(LEFT(firstName, 1)) || (currval('management_id_seq') + 10) || currval('management_id_seq')  WHERE employee_id = ?;";

    //-------------------
    //
    // METODY DO PASAŻERA
    //
    //-------------------


    //------------------------------------------------------------------------------
    // inserts: All model.Passenger, All model.Wallet
    //
    // [!!!] Transaction

    public void InsertPassenger(Passenger passenger) {
        Wallet wallet = new Wallet(0.0f, false);
        try (Connection con = getConnection()) {
            con.setAutoCommit(false);
            try (PreparedStatement ps = con.prepareStatement(INSERT_SQL_WALLET, PreparedStatement.RETURN_GENERATED_KEYS);
                 PreparedStatement insertPassengerStatement = con.prepareStatement(INSERT_SQL_PASSENGER)) {


                ps.setFloat(1, wallet.getMoney());
                ps.setBoolean(2, wallet.isCreditCard());
                ps.executeUpdate();

                ResultSet rs = ps.getGeneratedKeys();
                String wallet_id = null;
                if (rs.next()) { // To sprawdza czy jest coś do odczytania w następnym rezultacie
                    wallet_id = rs.getString(1);
                }

                insertPassengerStatement.setString(1, passenger.getLastName());
                insertPassengerStatement.setString(2, passenger.getFirstName());
                insertPassengerStatement.setString(3, passenger.getEmail());
                insertPassengerStatement.setString(4, passenger.getLogin());
                insertPassengerStatement.setString(5, passenger.getPassword());
                insertPassengerStatement.setFloat(6, passenger.getRating());
                insertPassengerStatement.setString(7, wallet_id);
                insertPassengerStatement.executeUpdate();

                con.commit();
                //System.out.println("Transaction is commited successfully.");
            } catch (SQLException e) {
                con.rollback();
                //System.out.println("Transaction is being rolled back.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    //------------------------------------------------------------------------------
    // inserts: All model.Passenger, All model.Wallet, generate auto login
    //
    // [!!!] Transaction

    public void InsertPassengerAutoLogin(Passenger passenger) {
        Wallet wallet = new Wallet(0.0f, false);
        try (Connection con = getConnection()) {
            con.setAutoCommit(false);
            try (PreparedStatement ps = con.prepareStatement(INSERT_SQL_WALLET, PreparedStatement.RETURN_GENERATED_KEYS);
                 PreparedStatement insertPassengerStatement = con.prepareStatement(INSERT_SQL_PASSENGER, PreparedStatement.RETURN_GENERATED_KEYS)) {
                ps.setFloat(1, wallet.getMoney());
                ps.setBoolean(2, wallet.isCreditCard());
                ps.executeUpdate();

                ResultSet rs = ps.getGeneratedKeys();
                String wallet_id = null;
                if (rs.next()) { // To sprawdza czy jest coś do odczytania w następnym rezultacie
                    wallet_id = rs.getString(1);
                }

                insertPassengerStatement.setString(1, passenger.getLastName());
                insertPassengerStatement.setString(2, passenger.getFirstName());
                insertPassengerStatement.setString(3, passenger.getEmail());
                insertPassengerStatement.setString(4, "");
                insertPassengerStatement.setString(5, passenger.getPassword());
                insertPassengerStatement.setFloat(6, passenger.getRating());
                insertPassengerStatement.setString(7, wallet_id);
                insertPassengerStatement.executeUpdate();


                try(PreparedStatement updateGeneratedKeys = con.prepareStatement(UPDATE_SQL_LOGIN_FOR_AUTO_INSERT_PASSENGER)) {
                    ResultSet insertGeneratedKeys = insertPassengerStatement.getGeneratedKeys();
                    String key = null;
                    if (insertGeneratedKeys.next()) {
                        key = insertGeneratedKeys.getString(1);
                    }
                    updateGeneratedKeys.setString(1, key);
                    updateGeneratedKeys.executeUpdate();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
                con.commit();
            } catch (SQLException e) {
                con.rollback();
                System.out.println(e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    //------------------------------------------------------------------------------
    // updates: CreditCard model.Wallet for passenger

    public void UpdateWalletCreditCard(Boolean status, String passenger_id) {
        try (Connection con = getConnection()) {
            try (PreparedStatement ps = con.prepareStatement(UPDATE_SQL_WALLET)) {
                ps.setBoolean(1, status);
                ps.setString(2, passenger_id);
                ps.executeUpdate();
            } catch (SQLException e) {
                System.out.println("Something went wrong");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    //------------------------------------------------------------------------------
    // updates: Money model.Wallet for passenger

    public void UpdateWalletMoney(float money, String passenger_id) {
        try (Connection con = getConnection()) {
            try (PreparedStatement ps = con.prepareStatement(UPDATE_SQL_MONEY_WALLET)) {
                ps.setFloat(1, money);
                ps.setString(2, passenger_id);
                ps.executeUpdate();
            } catch (SQLException e) {
                System.out.println("Something went wrong");
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }


    //------------------------------------------------------------------------------
    // returns: All without password, login for one model.Passenger

    public Passenger SelectOnePassenger(String passenger_id) {
        Passenger passenger = new Passenger();
        try (Connection con = getConnection()) {
            try (PreparedStatement preparedStatement = con.prepareStatement(SELECT_SQL_BASIC_PASSENGER)) {
                preparedStatement.setString(1, passenger_id);

                ResultSet rs = preparedStatement.executeQuery();
                if (rs.next()) {
                    passenger.setPassenger_id(rs.getString(1));
                    passenger.setFirstName(rs.getString(2));
                    passenger.setLastName(rs.getString(3));
                    passenger.setEmail(rs.getString(4));
                    passenger.setRating(rs.getFloat(5));

                }
                rs.close();
            } catch (SQLException e) {
                System.out.println("Something went wrong");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return passenger;
    }


    //------------------------------------------------------------------------------
    // returns: String for special options

    public String CheckLoginPassenger(String login, String password){
        String goodResult = null;
        try(Connection con = getConnection()) {
            try(PreparedStatement pr = con.prepareStatement(SELECT_SQL_LOGIN_PASSENGER);
                PreparedStatement pr_pass = con.prepareStatement(SELECT_SQL_PASSWORD_PASSENGER)){
                pr.setString(1, login);
                ResultSet rs = pr.executeQuery();
                var ifGood = rs.next();
                if (!ifGood){
                    pr.close();
                    rs.close();
                    con.close();
                    return "badLogin";
                }

                pr_pass.setString(1, login);
                ResultSet rs_pass = pr_pass.executeQuery();
                String passwordFromDB = null;
                if (rs_pass.next()){
                    passwordFromDB = rs_pass.getString(1);
                    goodResult = rs_pass.getString(2);
                }
                if (!passwordFromDB.equals(password)) {
                    pr.close();
                    rs.close();
                    con.close();
                    return "badPassword";
                }
            } catch (SQLException e){
                System.out.println(e.getMessage());
            }
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(goodResult);
        return goodResult;
    }



    //-------------------
    //
    // METODY DO KIEROWCY
    //
    //-------------------


    //------------------------------------------------------------------------------
    // inserts: All model.Driver, All model.Car
    //
    // [!!!] Transaction

    public void InsertDriverWithCar(Driver driver, Car car) {
        try (Connection con = getConnection()) {
            con.setAutoCommit(false);
            try (PreparedStatement insertOnCarStatement = con.prepareStatement(INSERT_SQL_CAR, PreparedStatement.RETURN_GENERATED_KEYS);
                 PreparedStatement insertOnDriverStatement = con.prepareStatement(INSERT_SQL_DRIVER)) {

                insertOnCarStatement.setString(1, car.getPlates());
                insertOnCarStatement.setString(2, car.getModel());
                insertOnCarStatement.setString(3, car.getColor());
                insertOnCarStatement.setInt(4, car.getSeats());
                insertOnCarStatement.executeUpdate();


                ResultSet rs = insertOnCarStatement.getGeneratedKeys();
                String driver_id = null;
                if (rs.next()) {
                    driver_id = rs.getString(1);
                }

                insertOnDriverStatement.setString(1, driver.getFirstName());
                insertOnDriverStatement.setString(2, driver.getLastName());
                insertOnDriverStatement.setString(3, driver.getEmail());
                insertOnDriverStatement.setString(4, driver.getLogin());
                insertOnDriverStatement.setString(5, driver.getPassword());
                insertOnDriverStatement.setFloat(6, driver.getSalary());
                insertOnDriverStatement.setFloat(7, driver.getRating());
                insertOnDriverStatement.setString(8, driver_id);
                insertOnDriverStatement.setString(9, driver.getEmploymentStatus());
                insertOnDriverStatement.executeUpdate();

                con.commit();
                //System.out.println("Transaction is commited successfully.");
            } catch (SQLException e) {
                con.rollback();
                System.out.println("Transaction is being rolled back.");
                System.out.println(e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    //------------------------------------------------------------------------------
    // inserts: All model.Driver, All model.Car, generate auto login
    //
    // [!!!] Transaction

    public void InsertDriverWithCarWithAutoLogin(Driver driver, Car car) {
        try (Connection con = getConnection()) {
            con.setAutoCommit(false);
            try (PreparedStatement insertOnCarStatement = con.prepareStatement(INSERT_SQL_CAR, PreparedStatement.RETURN_GENERATED_KEYS);
                 PreparedStatement insertOnDriverStatement = con.prepareStatement(INSERT_SQL_DRIVER, PreparedStatement.RETURN_GENERATED_KEYS)) {

                insertOnCarStatement.setString(1, car.getPlates());
                insertOnCarStatement.setString(2, car.getModel());
                insertOnCarStatement.setString(3, car.getColor());
                insertOnCarStatement.setInt(4, car.getSeats());
                insertOnCarStatement.executeUpdate();


                ResultSet rs = insertOnCarStatement.getGeneratedKeys();
                String driver_id = null;
                if (rs.next()) {
                    driver_id = rs.getString(1);
                }

                insertOnDriverStatement.setString(1, driver.getFirstName());
                insertOnDriverStatement.setString(2, driver.getLastName());
                insertOnDriverStatement.setString(3, driver.getEmail());
                insertOnDriverStatement.setString(4, "");
                insertOnDriverStatement.setString(5, driver.getPassword());
                insertOnDriverStatement.setFloat(6, driver.getSalary());
                insertOnDriverStatement.setFloat(7, driver.getRating());
                insertOnDriverStatement.setString(8, driver_id);
                insertOnDriverStatement.setString(9, driver.getEmploymentStatus());
                insertOnDriverStatement.executeUpdate();

                try(PreparedStatement updateLogin = con.prepareStatement(UPDATE_SQL_LOGIN_FOR_AUTO_INSERT_DRIVER)) {
                    ResultSet insertGeneratedKeys = insertOnDriverStatement.getGeneratedKeys();
                    String key = null;
                    if (insertGeneratedKeys.next()) {
                        key = insertGeneratedKeys.getString(1);
                    }
                    updateLogin.setString(1, key);
                    updateLogin.executeUpdate();;
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
                con.commit();
                //System.out.println("Transaction is commited successfully.");
            } catch (SQLException e) {
                con.rollback();
                System.out.println("Transaction is being rolled back.");
                System.out.println(e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    //------------------------------------------------------------------------------
    // returns: All

    public List<Driver> SelectAllDriversToList() {
        List<Driver> drivers = new ArrayList<>();
        try (Connection con = getConnection()) {
            try (Statement stmt = con.createStatement()){
                ResultSet rs = stmt.executeQuery(SELECT_SQL_ALL_DRIVERS);
                while (rs.next()) {
                    Driver driver = new Driver();
                    driver.setDriver_id(rs.getString(1));
                    driver.setFirstName(rs.getString(2));
                    driver.setLastName(rs.getString(3));
                    driver.setEmail(rs.getString(4));
                    driver.setLogin(rs.getString(5));
                    driver.setPassword(rs.getString(6));
                    driver.setSalary(rs.getFloat(7));
                    driver.setRating(rs.getFloat(8));
                    driver.setEmploymentStatus(rs.getString(9));
                    drivers.add(driver);
                }
                rs.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return drivers;
    }


    //------------------------------------------------------------------------------
    // returns: id, firstName, lastName, rating

    public List<Driver> SelectBasicDataDrivers() {
        List<Driver> drivers = new ArrayList<>();
        try (Connection con = getConnection()) {
            try (Statement stmt = con.createStatement()) {
                ResultSet rs = stmt.executeQuery(SELECT_SQL_BASIC_DATA_DRIVER);
                while (rs.next()) {
                    drivers.add(mapResultSetToDriver(rs));
                }

                rs.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return drivers;
    }
    private static Driver mapResultSetToDriver(ResultSet rs) {
        Driver driver = new Driver();
        try{
            driver.setDriver_id(rs.getString(1));
            driver.setFirstName(rs.getString(2));
            driver.setLastName(rs.getString(3));
            driver.setRating(rs.getFloat(4));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return driver;
    }


    //------------------------------------------------------------------------------
    // returns: id, firstName, lastName, rating, employmentStatus

    public List<Driver> SelectBasicDataDriversV2() {
        List<Driver> drivers = new ArrayList<>();
        try (Connection con = getConnection()) {
            try (Statement stmt = con.createStatement()) {
                ResultSet rs = stmt.executeQuery(SELECT_SQL_BASIC_EMPLOYMENT_STATUS_DATA_DRIVER);
                while (rs.next()) {
                    drivers.add(mapResultSetToDriverV2(rs));
                }

                rs.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return drivers;
    }
    private static Driver mapResultSetToDriverV2(ResultSet rs) {
        Driver driver = new Driver();
        try {
            driver.setDriver_id(rs.getString(1));
            driver.setFirstName(rs.getString(2));
            driver.setLastName(rs.getString(3));
            driver.setRating(rs.getFloat(4));
            driver.setEmploymentStatus(rs.getString(5));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return driver;
    }

    //------------------------------------------------------------------------------
    // returns: All without: model.Driver.password for All drivers

    public List<PairDriverWithCar<Driver, Car>> SelectDriverAndCar() {
        List<PairDriverWithCar<Driver, Car>> pairArray = new ArrayList<>();
        try (Connection con = getConnection()) {
            try (Statement stmt = con.createStatement()) {
                try (ResultSet rs = stmt.executeQuery(SELECT_SQL_ALL_DRIVERS_AND_CARS)){
                    while (rs.next()) {
                        Driver driver = mapResultSetToDriverToPair(rs);
                        Car car = mapResultSetToCarToPair(rs);
                        pairArray.add(new PairDriverWithCar<>(driver, car));
                    }
                } catch (SQLException e){
                    System.out.println(e.getMessage());
                }
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return pairArray;
    }
    private static Driver mapResultSetToDriverToPair(ResultSet rs) {
        Driver driver = new Driver();
        try {
            driver.setDriver_id(rs.getString(1));
            driver.setFirstName(rs.getString(2));
            driver.setLastName(rs.getString(3));
            driver.setEmail(rs.getString(4));
            driver.setLogin(rs.getString(5));
            driver.setSalary(rs.getFloat(6));
            driver.setRating(rs.getFloat(7));
            driver.setEmploymentStatus(rs.getString(8));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return driver;
    }
    private static Car mapResultSetToCarToPair(ResultSet rs) {
        Car car = new Car();
        try{
            car.setPlates(rs.getString(9));
            car.setModel(rs.getString(10));
            car.setColor(rs.getString(11));
            car.setSeats(rs.getInt(12));
            car.setCar_id(rs.getString(13));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return car;
    }



    //------------------------------------------------------------------------------
    // returns: All without: model.Driver.password for One driver
    //
    // streaming

    public List<PairDriverWithCar<Driver, Car>> SelectOneDriverAndCar(String driver_id) {
        return SelectDriverAndCar().stream()
                .filter(pair -> pair.getFirst().getDriver_id().equals(driver_id))
                .collect(Collectors.toList());
    }


    //------------------------------------------------------------------------------
    // returns: String for special options

    public String CheckLoginDriver(String login, String password){
        String goodResult = "";
        try(Connection con = getConnection()) {
            try(PreparedStatement pr = con.prepareStatement(SELECT_SQL_LOGIN_DRIVER);
                PreparedStatement pr_pass = con.prepareStatement(SELECT_SQL_PASSWORD_DRIVER)){
                pr.setString(1, login);
                ResultSet rs = pr.executeQuery();
                var ifGood = rs.next();
                if (!ifGood){
                    pr.close();
                    rs.close();
                    con.close();
                    return "badLogin";
                }

                pr_pass.setString(1, login);
                ResultSet rs_pass = pr_pass.executeQuery();
                String passwordFromDB = null;
                if (rs_pass.next()){
                    passwordFromDB = rs_pass.getString(1);
                    goodResult = rs_pass.getString(2);
                }
                if (!passwordFromDB.equals(password)) {
                    pr.close();
                    rs.close();
                    con.close();
                    return "badPassword";
                }
            } catch (SQLException e){
                System.out.println(e.getMessage());
            }
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(goodResult);
        return goodResult;
    }



    //-------------------
    //
    // METODY DO ZARZĄDU
    //
    //-------------------


    //------------------------------------------------------------------------------
    // inserts: All Employees

    public void InsertManagementEmployee(Management employee) {
        try (Connection con = getConnection()) {
            PreparedStatement insertOnManagementStatement = con.prepareStatement(INSERT_SQL_MANAGEMENT_EMPLOYEE);
            insertOnManagementStatement.setString(1, employee.getFirstName());
            insertOnManagementStatement.setString(2, employee.getLastName());
            insertOnManagementStatement.setString(3, employee.getEmail());
            insertOnManagementStatement.setString(4, employee.getLogin());
            insertOnManagementStatement.setString(5, employee.getPassword());
            insertOnManagementStatement.setString(6, employee.getJobTitle());
            insertOnManagementStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    //------------------------------------------------------------------------------
    // inserts: All Employees, auto generate login
    //
    // [!!!] Transaction

    public void InsertManagementEmployeeWithAutoLogin(Management employee) {
        try (Connection con = getConnection()) {
            con.setAutoCommit(false);
            try(PreparedStatement insertOnManagementStatement = con.prepareStatement(INSERT_SQL_MANAGEMENT_EMPLOYEE, Statement.RETURN_GENERATED_KEYS)){
                insertOnManagementStatement.setString(1, employee.getFirstName());
                insertOnManagementStatement.setString(2, employee.getLastName());
                insertOnManagementStatement.setString(3, employee.getEmail());
                insertOnManagementStatement.setString(4, "");
                insertOnManagementStatement.setString(5, employee.getPassword());
                insertOnManagementStatement.setString(6, employee.getJobTitle());
                insertOnManagementStatement.executeUpdate();

                try(PreparedStatement updateLogin = con.prepareStatement(UPDATE_SQL_LOGIN_FOR_AUTO_INSERT_MANAGEMENT)){
                    ResultSet getKeys = insertOnManagementStatement.getGeneratedKeys();
                    String key = null;
                    if(getKeys.next()){
                        key = getKeys.getString(1);
                    }
                    updateLogin.setString(1, key);
                    updateLogin.executeUpdate();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            con.commit();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    //------------------------------------------------------------------------------
    // returns: All Complaints for One Employee

    public List<Complaint> SelectComplaint(String employee_id) {
        List<Complaint> complaints = new ArrayList<>();
        try (Connection con = getConnection()) {
            try (PreparedStatement ps = con.prepareStatement(SELECT_SQL_BASIC_COMPLAINT_FOR_ONE_EMPLOYEE)) {
                ps.setString(1, employee_id);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Complaint complaint = new Complaint();
                    complaint.setComplaint_id(rs.getString(1));
                    complaint.setDate(rs.getDate(2));
                    complaint.setStatus(rs.getString(3));
                    complaint.setRide_id(rs.getString(4));
                    complaints.add(complaint);
                }
                rs.close();
            } catch (SQLException e){
                System.out.println(e.getMessage());
            }
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return complaints;
    }


    //------------------------------------------------------------------------------
    // returns: All basic [data] model.ApplicationForEarlierSalary for One Employee

    public List<ApplicationForEarlierSalary> SelectApplicationForEarlierSalary(String employee_id) {
        List<ApplicationForEarlierSalary> applicationForEarlierSalary = new ArrayList<>();
        try (Connection con = getConnection()) {
            try (PreparedStatement ps = con.prepareStatement(SELECT_SQL_BASIC_AFES)){
                ps.setString(1, employee_id);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    ApplicationForEarlierSalary app = new ApplicationForEarlierSalary();
                    app.setEmployee_id(rs.getString(1));
                    app.setDate(rs.getDate(2));
                    app.setStatus(rs.getString(3));
                    applicationForEarlierSalary.add(app);
                }
                rs.close();
            } catch (SQLException e){
                System.out.println(e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return  applicationForEarlierSalary;
    }


    //------------------------------------------------------------------------------
    // returns: All [data] model.ApplicationForEarlierSalary for One Employee

    public List<ApplicationForEarlierSalary> SelectAllDataApplicationForEarlierSalary(String id)  {
        List<ApplicationForEarlierSalary> applicationForEarlierSalary = new ArrayList<>();
        try (Connection con = getConnection()) {
            try (PreparedStatement ps = con.prepareStatement(SELECT_SQL_ALL_DATA_AFES)){
                ps.setString(1, id);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    ApplicationForEarlierSalary app = new ApplicationForEarlierSalary();
                    app.setEmployee_id(rs.getString(1));
                    app.setDate(rs.getDate(2));
                    app.setDescription((rs.getString(3)));
                    app.setStatus(rs.getString(4));
                    app.setDriver_id(rs.getString(5));
                    app.setEmployee_id(rs.getString(6));
                    applicationForEarlierSalary.add(app);
                }
                rs.close();
            } catch (SQLException e){
                System.out.println(e.getMessage());
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return  applicationForEarlierSalary;
    }


    //------------------------------------------------------------------------------
    // returns: String for special options

    public String CheckLoginManagement(String login, String password){
        String goodResult = "";
        try(Connection con = getConnection()) {
            try(PreparedStatement pr = con.prepareStatement(SELECT_SQL_LOGIN_MANAGEMENT);
                PreparedStatement pr_pass = con.prepareStatement(SELECT_SQL_PASSWORD_MANAGEMENT)){
                pr.setString(1, login);
                ResultSet rs = pr.executeQuery();
                var ifGood = rs.next();
                if (!ifGood){
                    pr.close();
                    rs.close();
                    con.close();
                    return "badLogin";
                }

                pr_pass.setString(1, login);
                ResultSet rs_pass = pr_pass.executeQuery();
                String passwordFromDB = null;
                if (rs_pass.next()){
                    passwordFromDB = rs_pass.getString(1);
                    goodResult = rs_pass.getString(2);
                }
                if (!passwordFromDB.equals(password)) {
                    pr.close();
                    rs.close();
                    con.close();
                    return "badPassword";
                }
            } catch (SQLException e){
                System.out.println(e.getMessage());
            }
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(goodResult);
        return goodResult;
    }



    //-------------------
    //
    // METODY DO PRZEJAZDU
    //
    //-------------------


    //------------------------------------------------------------------------------
    // inserts: All model.Ride

    public void InsertRide(Ride ride) {
        try (Connection con = getConnection()) {
            PreparedStatement insertRideStatement = con.prepareStatement(INSERT_SQL_RIDE);
            insertRideStatement.setDate(1, ride.getDate());
            insertRideStatement.setString(2, ride.getPick_up());
            insertRideStatement.setString(3, ride.getDestination());
            insertRideStatement.setFloat(4, ride.getPrice());
            insertRideStatement.setFloat(5, ride.getRideLength());
            insertRideStatement.setTime(6, ride.getStartTime());
            insertRideStatement.setTime(7, ride.getEndTime());
            insertRideStatement.setString(8, ride.getPassenger_id());
            insertRideStatement.setString(9, ride.getDriver_id());
            insertRideStatement.setFloat(10, ride.getRatingForPassenger());
            insertRideStatement.setFloat(11, ride.getRatingForDriver());
            insertRideStatement.executeUpdate();
        } catch (SQLException E){
            System.out.println(E.getMessage());
        }
    }



    //-------------------
    //
    // METODY DO SKARGI
    //
    //-------------------


    //------------------------------------------------------------------------------
    // inserts: All Complaints

    public void InsertComplaint(Complaint complaint) {
        try (Connection con = getConnection()) {
            PreparedStatement insertComplaintStatement = con.prepareStatement(INSERT_SQL_COMPLAINT);
            insertComplaintStatement.setString(1, complaint.getDescription());
            insertComplaintStatement.setDate(2, complaint.getDate());
            insertComplaintStatement.setString(3, complaint.getStatus());
            insertComplaintStatement.setString(4, complaint.getRide_id());
            insertComplaintStatement.setString(5, complaint.getDriver_id());
            insertComplaintStatement.setString(6, complaint.getEmployee_id());
            insertComplaintStatement.executeUpdate();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }


    //------------------------------------------------------------------------------
    // update: model.Complaint Status

    public void UpdateComplaintStatus(String status, String complaint_id) {
        try (Connection con = getConnection()) {
            try (PreparedStatement updateStatus = con.prepareStatement(UPDATE_SQL_STATUS_COMPLAINT)){
                updateStatus.setString(1, status);
                updateStatus.setString(2, complaint_id);
                updateStatus.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    //------------------------------------------------------------------------------
    // returns: id, date, status, ride_id

    public List<Complaint> SelectBasicAllComplaint() {
        List<Complaint> complaints = new ArrayList<>();
        try (Connection con = getConnection()) {
            try(PreparedStatement ps = con.prepareStatement(SELECT_SQL_BASIC_COMPLAINT)){
              ResultSet rs = ps.executeQuery();
              while (rs.next()) {
                  Complaint complaint = new Complaint();
                  complaint.setComplaint_id(rs.getString(1));
                  complaint.setDate(rs.getDate(2));
                  complaint.setStatus(rs.getString(3));
                  complaint.setRide_id(rs.getString(4));
                  complaints.add(complaint);
              }
              rs.close();
            } catch(SQLException e) {
                System.out.println(e.getMessage());
            }
        }catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return complaints;
    }


    //------------------------------------------------------------------------------
    // returns: All for one model.Complaint

    public Complaint SelectBasicOneComplaint(String complaint_id) {
        Complaint complaint = new Complaint();
        try (Connection con = getConnection()) {
            try(PreparedStatement ps = con.prepareStatement(SELECT_SQL_ALL_COMPLAINT)){
                ps.setString(1, complaint_id);

                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    complaint.setComplaint_id(rs.getString(1));
                    complaint.setDescription(rs.getString(2));
                    complaint.setDate(rs.getDate(3));
                    complaint.setStatus(rs.getString(4));
                    complaint.setRide_id(rs.getString(5));
                    complaint.setDriver_id(rs.getString(6));
                    complaint.setEmployee_id(rs.getString(7));
                }
                rs.close();
            } catch(SQLException e) {
                System.out.println(e.getMessage());
            }
        }catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return complaint;
    }



    //-------------------
    //
    // METODY DO WNIOSKU
    //
    //-------------------


    public void InsertApplicationForEarlierSalary(ApplicationForEarlierSalary earlierSalary) {
        try (Connection con = getConnection()) {
            PreparedStatement insertApplicationForEarlierSalary = con.prepareStatement(INSERT_SQL_APPLICATION_FOR_EARLIER_SALARY);
            insertApplicationForEarlierSalary.setString(1, earlierSalary.getStatus());
            insertApplicationForEarlierSalary.setString(2, earlierSalary.getDescription());
            insertApplicationForEarlierSalary.setDate(3, earlierSalary.getDate());
            insertApplicationForEarlierSalary.setString(4, earlierSalary.getDriver_id());
            insertApplicationForEarlierSalary.setString(5, earlierSalary.getEmployee_id());
            insertApplicationForEarlierSalary.executeUpdate();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }



    //-------------------
    //
    // METODY DO RATINGU
    //
    //-------------------


    //------------------------------------------------------------------------------
    // updates: rating for drivers

    public void UpdateRatingForDriver() {
        try (Connection con = getConnection()){
            try(PreparedStatement ps = con.prepareStatement(UPDATE_SQL_RATING_DRIVER)){
                ps.executeUpdate();
            }catch(SQLException e) {
                System.out.println(e.getMessage());
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }


    //------------------------------------------------------------------------------
    // updates: rating for passengers

    public void UpdateRatingForPassenger() {
        try (Connection con = getConnection()){
            try(PreparedStatement ps = con.prepareStatement(UPDATE_SQL_RATING_PASSENGER)){
                ps.executeUpdate();
            }catch(SQLException e) {
                System.out.println(e.getMessage());
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}