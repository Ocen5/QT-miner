package data;

import java.io.Serializable;

public abstract class Item implements Serializable{
	
	/**
	 * @attribute Attribute is the type of attribute stored in the Item
	 * @attribute Value is the value of attribute stored in the Item
	 */
	private Attribute attribute;
	private Object value;
	
	/**
	 * Package constructor
	 * @param Attribute is the type of attribute stored in the Item 
	 * @param Value is the value of attribute stored in the Item
	 */
	Item(Attribute attribute, Object value)
	{
		this.attribute=attribute;
		this.value=value;
	}

	/**
	 * Return the Attribute stored in the Item
	 * @return the attribute stored in the Item 
	 */
	Attribute getAttribute() {
		return attribute;
	}

	/**
	 * Return the value of the attribute stored in the Item
	 * @return value of the attribute stored in the Item
	 */
	Object getValue() {
		return value;
	}
	
	/**
	 * Overrides Object's toString
	 * @return the string by the value's toString()
	 */
	@Override
	public String toString() {
		return value.toString();
	}


	/**
	 * abstract method which will be implemented differently in subclasses
	 */
	abstract double distance(Object a);
}
