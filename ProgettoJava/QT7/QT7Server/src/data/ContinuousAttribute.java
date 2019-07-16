package data;

public class ContinuousAttribute extends Attribute{

	// estremi dell'intervallo di valori (dominio) che l'attributo può assumere:
	private double max;
	private double min;



	ContinuousAttribute(String name, int index, double min, double max)
	{
		super(name,index);
		this.min = min;
		this.max = max;
	}

	//Calcola e restituisce il valore normalizzato V del parametro in input. La normalizzazione ha come codominio lo intervallo [0,1]
	double getScaledValue(double v)
	{
		double V;
		V=(v-min)/(max-min);
		return V;
	}



}


