package data;
import java.io.Serializable;
import java.util.Set;

public class Tuple implements Serializable{

	private Item [] tuple;


	Tuple(int size)//numero di item della tupla
	{
		this.tuple=new Item[size];//costruisce l'oggetto riferito da tuple
	}

	public int getLength()
	{
		return tuple.length;//restituisce tuple.length
	}

	public Item get(int i)
	{
		return tuple[i];//restituisce lo Item in posizione i
	}

	void add(Item c,int i)
	{
		tuple[i]=c;//memorizza c in tuple[i]
	}


	/*determina la distanza tra la tupla riferita da obj e la tupla corrente (riferita da this). 
	 * La distanza è ottenuta come la somma delle distanze tra gli item in posizioni uguali nelle due tuple.
	 * Fare uso di double distance(Object a) di Item*/
	public double getDistance(Tuple obj)
	{
		double dist=0.0;
		for(int i=0;i<obj.getLength();i++)
		{
			if(tuple[i].getAttribute().getName().equals(obj.get(i).getAttribute().getName()))
			{
				dist+=this.get(i).distance(obj.get(i).getValue());
			}
		}
		return dist;
	}

	//restituisce la media delle distanze tra la tupla corrente e quelle ottenibili dalle righe della matrice DATA con indice in clusteredData.
	public double avgDistance(Data data, Set<Integer> clusteredData)
	{
		double p=0.0,sumD=0.0;
		for (Integer it : clusteredData)
		{
			double d= getDistance(data.getItemSet(it));
			sumD+=d;
		}
		p=sumD/clusteredData.size();
		return p;
	}

	public String toString ()
	{
		String str = " ";
		for (int i = 0; i < this.getLength(); i++)
		{
			str += this.get(i) + " ";
		}
		return str;
	}

}

