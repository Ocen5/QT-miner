package data;


public class DiscreteItem extends Item
{
	public DiscreteItem(Attribute attribute, Object value)
	{
		super(attribute, value);//Invoca il costruttore della superclasse Item
	}

	double distance(Object a)//Restituisce 0 se (getValue().equals(a)) , 1 altrimenti
	{
		if(getValue().equals(a))
		{
			return 0;
		}
		else 
		{
			return 1;
		}

	}
}
