package database;

public class DatabaseConnectionException extends Exception{

	/**
	 * Public constructor of the exception; it prints on
	 * System.err if connection to SQL database fails
	 */
	public DatabaseConnectionException()
	{
		System.err.println("Connection failed");
	}
}
