package database;

public class DatabaseConnectionException extends Exception{

	public DatabaseConnectionException()
	{
		System.err.println("Connection failed");
	}
}
