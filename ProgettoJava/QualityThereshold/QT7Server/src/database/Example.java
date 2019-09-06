package database;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Example implements Comparable<Example>, Serializable{
	
	/**
	 * @attribute example is a list of Objects that models a transition from database
	 */
	private List<Object> example=new ArrayList<Object>();
	
	/**
	 * Add object 'o' to example 
	 * @param o is the object to add
	 */
	public void add(Object o){
		example.add(o);
	}
	
	/**
	 * 
	 * @param i is the index of the object to return
	 * @return return the object in position i from example
	 */
	public Object get(int i){
		return example.get(i);
	}
	
	/**
	 * implements method compareTo() of Comparable.
	 * @param ex is the example to compare with current example
	 */
	public int compareTo(Example ex) {
		
		int i=0;
		for(Object o:ex.example){
			if(!o.equals(this.example.get(i)))
				return ((Comparable)o).compareTo(example.get(i));
			i++;
		}
		return 0;
	}
	
	/**
	 * Overrides Object's toString
	 * @return the string made from concatenation of example's objects' toString()
	 */
	public String toString(){
		String str="";
		for(Object o:example)
			str+=o.toString()+ " ";
		return str;
	}
	
}