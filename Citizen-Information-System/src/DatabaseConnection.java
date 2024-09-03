
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseConnection {
	/*
     * Title: DatabaseConnection Class
     * Author: Arda Baran
     * 
     * Description:
     * The DatabaseConnection class is responsible for establishing connections to MySQL databases.
     * This class encapsulates the details required to connect to three specific databases: the 'citizienDatabase' database
     * the 'data'(address and tax no ) database and the 'gsm' database. These databases may store different kinds of data pertinent to the citizen information system.
     * 
     * For each database, the class provides methods that handle the connection process, ensuring that the appropriate
     * database credentials are used and any connection issues are properly handled. If a connection fails, the error
     * is caught and an appropriate message is displayed, along with a stack trace to aid in debugging.
     * 
     * The class provides the following key functionalities:
     * 1. `getConnectionTocitizienDatabase()`: Establishes and returns a connection to the 'citizienDatabase' database.
     * 2. `getConnectionToGsm()`: Establishes and returns a connection to the 'gsm' database.
     * 3. 'getConnectionToAddress()':Establishes and returns a connection to the 'data' database.
     *  
     * This setup allows for seamless database operations in applications where these databases need to be accessed concurrently,
     * ensuring data integrity and consistency.
     */
	
	// Database 1 (citizienDatabase) configuration
	private static final String DB_URL_citizienDatabase = ".../citizienDatabase";
	private static final String USERNAME_citizienDatabase = "root";
	private static final String PASSWORD_citizienDatabase = "root"; 

 // Database 2 (gsm) configuration
    private static final String DB_URL_GSM = ".../gsm";
    private static final String USERNAME_GSM = "root";
    private static final String PASSWORD_GSM = "root";

    //Database 3 (adress) configuration
    private static final String DB_URL_ADDRESS =".../data";
    private static final String USERNAME_ADDRESS = "root";
    private static final String PASSWORD_ADDRESS = "root";

    public static Connection getConnectionTocitizienDatabase() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DB_URL_citizienDatabase, USERNAME_citizienDatabase, PASSWORD_citizienDatabase);
        } catch (SQLException e) {
            System.out.println("Failed to connect to the citizienDatabase database.");
            e.printStackTrace();
        }
        return connection;
    }



    public static Connection getConnectionToGsm() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DB_URL_GSM, USERNAME_GSM, PASSWORD_GSM);
        } catch (SQLException e) {
            System.out.println("Failed to connect to the gsm database.");
            e.printStackTrace();
        }
        return connection;
    }
public static Connection getConnectionToAddress() {
	  Connection connection = null;
      try {
          connection = DriverManager.getConnection(DB_URL_ADDRESS, USERNAME_ADDRESS, PASSWORD_ADDRESS);
      } catch (SQLException e) {
          System.out.println("Failed to connect to the address database.");
          e.printStackTrace();
      }
      return connection;
}

}