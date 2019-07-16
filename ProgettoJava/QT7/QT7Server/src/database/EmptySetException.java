package database;

//modella la restituzione di un resultset vuoto
public class EmptySetException extends Exception {
	
	public EmptySetException()
	{
		System.err.println("Empty ResultSet! ");
	}

}
