package database;

//modella l’assenza di un valore all’interno di un resultset
public class NoValueException extends Exception{

	public NoValueException(String string) 
	{
		System.err.println(string+"founded");
	}

}
