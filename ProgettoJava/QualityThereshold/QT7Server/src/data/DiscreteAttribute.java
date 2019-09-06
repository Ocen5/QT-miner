package data;

import java.util.Iterator;
import java.util.TreeSet;

public class DiscreteAttribute extends Attribute implements Iterable<String>
{	
	/**
	 * @attribute Set of values 
	 */
	private TreeSet<String> values;

	/**
	 * Package constructor
	 * @param name of the Discrete Attribute
	 * @param index of the Discrete Attribute
	 * @param values is a Set of values for attribute's domain
	 */
	DiscreteAttribute(String name, int index, TreeSet<String> values) 
	{
		super(name,index);
		this.values=values;
	}

	/**
	 * @return size of the set "values"
	 */
	int getNumberOfDistinctValues()
	{
		return values.size();
	}

	/**
	 * @return an iterator from the set "values"
	 */
	@Override
	public Iterator<String> iterator()
	{
		return values.iterator();
	}
}
