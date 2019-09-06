package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbAccess {
	/**
	 * @attribute DRIVER_CLASS_NAME is the driver used to get connection with SQL  
	 * @attribute DBMS is the protocol used
	 * @attribute SERVER is the identifier of the server of the database
	 * @attribute DATABASE is the name of the database
	 * @attribute PORT is the port on which DBMS MySQL accepts connections
	 * @attribute USER_ID is the login user name 
	 * @attribute PASSWORD is the login password for the user 'MapUser'
	 * @attribute conn manage a connection
	 */
	private final static String DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";
	private final static String DBMS = "jdbc:mysql";
	private final static String SERVER= "localhost";
	private final static String DATABASE = "MapDB";
	private final static String PORT= "3306";
	private final static String USER_ID = "MapUser";
	private final static String PASSWORD = "map";
	private static Connection conn;


	/**
	 * Impart to class loader the order of load MySQL's Driver,
	 * initialize the connection conn. 
	 * @throws DatabaseConnectionException when connection with database fails
	 * @throws ClassNotFoundException when class loader doesn't find a Driver 
	 * @throws SQLException for every SQL error
	 */
	public static void initConnection() throws DatabaseConnectionException, ClassNotFoundException, SQLException
	{	
		Class.forName(DRIVER_CLASS_NAME); 
		conn = DriverManager.getConnection(DBMS+"://"+SERVER+":"+PORT+"/"+DATABASE+"?useSSL=false",USER_ID,PASSWORD);
	}
	
	/**
	 * 
	 * @return the connection established with SQL database
	 */
	public static Connection getConnection()
	{
		return conn;
	}

	/**
	 * Close the connection with SQL database
	 */
	public static void closeConnection()
	{
		try {
			conn.close();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

}

