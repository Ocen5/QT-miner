package database;

public class NoValueException extends Exception{

	/**
	 * Public constructor of the exception; it prints on
	 * System.err if there are not a value in a resultset
	 */
	public NoValueException(String string) 
	{
		System.err.println(string+"founded");
	}
}
