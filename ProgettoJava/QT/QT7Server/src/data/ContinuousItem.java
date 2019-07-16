package data;


public class ContinuousItem extends Item
{

	ContinuousItem(Attribute attribute, double value)
	{
		super(attribute, value);
	}

	double distance(Object a)
	{

		double currVal = ((ContinuousAttribute) this.getAttribute()).getScaledValue((Double) this.getValue());

		double aVal = ((ContinuousAttribute) this.getAttribute()).getScaledValue((Double) a);

		return Math.abs(currVal - aVal);
	}
}
