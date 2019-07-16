package data;

import java.util.Iterator;
import java.util.TreeSet;

public class DiscreteAttribute extends Attribute implements Iterable<String>
{
	private TreeSet<String> values;

	public DiscreteAttribute(String name, int index, TreeSet<String> values) 
	{
		super(name,index);
		this.values=values;

		/*this.values=new TreeSet<String>();
		 for(String s:values)
		 {
		 this.values.add(s);
		 }
		 */
	}

	int getNumberOfDistinctValues()//restituisce la dimensione di values
	{
		return values.size();
	}

	@Override
	public Iterator<String> iterator()//restituisce il valore in posizione i del vettore di stringhe values
	{
		return values.iterator();
	}






}
