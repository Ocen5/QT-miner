package data;

import java.io.Serializable;

public abstract class Attribute implements Serializable {

	private String name;
	private int index;

	Attribute(String name, int index)
	{
		this.name = name;
		this.index = index;
	};

	String getName() 
	{
		return name;
	};

	int getIndex()
	{
		return index;
	};

	public String toString()//restituisce la stringa rappresentante lo stato dell'oggetto
	{
		return name;
	};


}
