package data;

public class EmptyDatasetException extends Exception{

	public EmptyDatasetException ()
	{
		System.err.println("Empty dataset");
	}
}
