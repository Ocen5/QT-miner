package data;

import java.io.Serializable;

public abstract class Item implements Serializable{

	public Attribute attribute;
	public Object value;

	Item(Attribute attribute, Object value)
	{
		this.attribute=attribute;
		this.value=value;
	}

	public Attribute getAttribute() {
		return attribute;//restituisce attribute
	}


	public Object getValue() {
		return value;//restituisce value
	}

	@Override
	public String toString() {
		return value.toString();//restituisce value
	}



	abstract double distance(Object a);//L’implementazione sarà diversa per item discreto e item continuo
}
