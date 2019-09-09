package data;


public class DiscreteItem extends Item
{
	
	/**
	 * Package constructor that uses the constructor of superclass Item
	 * @param attribute 
	 * @param value
	 */
	DiscreteItem(Attribute attribute, Object value)
	{
		super(attribute, value);
	}

	/**
	 * Computes the distance between current Item and parameter a
	 * @return 0 if getValue.equals(a) is true, 1 otherwise
	 */
	double distance(Object a)
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
