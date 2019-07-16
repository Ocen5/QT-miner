package mining;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import data.Data;

public class ClusterSet implements Iterable<Cluster>, Serializable{

	private Set<Cluster> C=new TreeSet<Cluster>();

	public ClusterSet()
	{
		C = new TreeSet<Cluster>();
	}

	void add(Cluster c)
	{
		C.add(c);
	}

	public String toString(){
		String output="";
		int j=1;
		for(Cluster it: C)
		{
			output+=j+": "+it+"\n";
			j++;
		}
		return output;//Restituisce una stringa fatta da ciascun centroide dell’insieme dei cluster C
		
	}

	public String toString(Data data)
	{
		String output = "";
		int i = 0;
		Iterator<Cluster> CIterator = C.iterator();
		while (CIterator.hasNext())
		{
			output += i+1 + ":" + CIterator.next().toString(data) + "\n";
			i++;
		}

		return output;
	}

	@Override
	public Iterator<Cluster> iterator()
	{
		return C.iterator();
	}

}
