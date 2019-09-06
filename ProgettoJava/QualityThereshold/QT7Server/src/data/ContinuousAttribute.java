package data;

public class ContinuousAttribute extends Attribute{

	/**
	 * Limits of values' domain that attribute can be
	 * @attribute max is the max limit
	 * @attribute min is the min limit
	 */
	private double max;
	private double min;


	/**
	 * Package constructor
	 * @param name of the Continuous Attribute
	 * @param index of the Continuous Attribute
	 * @param min is the max limit of the domain
	 * @param max is the max limit of the domain
	 */
	ContinuousAttribute(String name, int index, double min, double max)
	{
		super(name,index);
		this.min = min;
		this.max = max;
	}

	/**
	 * Computes and returns a normalization of the input into a domain [0,1]
	 * @param v is values that will be normalized
	 * @return values normalized
	 */
	double getScaledValue(double v)
	{
		double V;
		V=(v-min)/(max-min);
		return V;
	}



}


