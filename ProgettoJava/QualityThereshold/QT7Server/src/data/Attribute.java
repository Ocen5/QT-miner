package data;

import java.io.Serializable;

public abstract class Attribute implements Serializable {
	/**
	 * @attribute name of the attribute
	 * @attribute index of the attribute
	 */
	private String name;
	private int index;
	
	/**
	 * Package constructor
	 * @param name of the attribute
	 * @param index of the attribute
	 */
	Attribute(String name, int index)
	{
		this.name = name;
		this.index = index;
	};
	
	/**
	 * Return name of Attribute 
	 * @return name of the attribute
	 */
	String getName() 
	{
		return name;
	};
	
	/**
	 * Return index of the Attribute
	 * @return index of the attribute
	 */
	int getIndex()
	{
		return index;
	};

	/**
	 * Overrides Object's toString
	 * @return the name of the attribute
	 */
	public String toString()
	{
		return name;
	};


}
