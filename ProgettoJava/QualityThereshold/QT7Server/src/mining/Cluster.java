package mining;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import data.Data;
import data.Tuple;

class Cluster implements Iterable<Integer>, Comparable<Cluster>, Serializable{

	private Tuple centroid;
	private Set<Integer> clusteredData; 


	Cluster(Tuple centroid){
		this.centroid=centroid;
		clusteredData=new HashSet<Integer>();

	}

	Tuple getCentroid(){
		return centroid;
	}

	//restituisce vero se la tupla cambia il cluster
	boolean addData(int id){
		return clusteredData.add(id);

	}

	//verifica se una transazione è clusterizzata nell'array corrente
	boolean contain(int id){
		return clusteredData.contains(id);
	}


	//rimuove la tupla che ha cambiato il cluster
	void removeTuple(int id){
		clusteredData.remove(id);

	}

	int  getSize(){
		return clusteredData.size();
	}


	public Iterator<Integer> iterator()
	{
		return clusteredData.iterator();
	}


	public String toString(){
		String str="Centroid=(";
		for(int i=0;i<centroid.getLength();i++)
			str+=centroid.get(i)+ " ";
		str+=")";
		return str;

	}

	public String toString(Data data){
		String str="Centroid=( ";
		for(int i=0;i<centroid.getLength();i++)
			str+=centroid.get(i)+ " ";
		str+=")\nExamples:\n";
		for (Integer it: clusteredData)
		{
			str+="[";
			str+= data.getItemSet(it);
			str+="] dist="+getCentroid().getDistance(data.getItemSet(it))+"\n";

		}
		str+= "\nAvgDistance="+getCentroid().avgDistance(data, clusteredData);
		return str;

	}

	@Override
	public int compareTo(Cluster o)
	{
		if (clusteredData.size()<this.clusteredData.size())
			return +1;
		else
			return -1;
	}

}

