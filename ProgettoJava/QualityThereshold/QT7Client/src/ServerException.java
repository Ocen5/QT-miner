
public class ServerException extends Exception {

	/**
	 * Public constructor of the exception; it prints on
	 * System.err for every error with the Server connection
	 */
	public ServerException(String result)
	{
		System.err.println(result);
	}

}
