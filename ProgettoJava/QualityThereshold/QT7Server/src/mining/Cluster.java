package mining;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import data.Data;
import data.Tuple;

class Cluster implements Iterable<Integer>, Comparable<Cluster>, Serializable{

	/**
	 * @attribute centroid is the tuple that identified the cluster
	 * @attribute clusteredData is a Set that models which tuples are clustered and which not
	 */
	private Tuple centroid;
	private Set<Integer> clusteredData; 

	/**
	 * Package constructor
	 * @param centroid is the tuple that identified the cluster
	 */
	Cluster(Tuple centroid){
		this.centroid=centroid;
		clusteredData=new HashSet<Integer>();
	}

	/**
	 * 
	 * @return centroid
	 */
	Tuple getCentroid(){
		return centroid;
	}
	
	/**
	 * 
	 * @param id is the identifier of the new tuple
	 * @return true if adding a tuple change the cluster
	 */
	boolean addData(int id){
		return clusteredData.add(id);

	}

	/**
	 * 
	 * @param id is the identifier of the new tuple
	 * @return true if the transition identified by 'id' is clustered in current Set
	 */
	boolean contain(int id){
		return clusteredData.contains(id);
	}

	/**
	 * Removes tuple that change the cluster
	 * @param id is the identifier of the new tuple
	 */
	void removeTuple(int id){
		clusteredData.remove(id);
	}

	/**
	 * 
	 * @return number clustered tuples
	 */
	int  getSize(){
		return clusteredData.size();
	}

	/**
	 * Implements iterator() from Iterable by using Set.iterator()
	 * @return an instance of Iterator
	 */
	public Iterator<Integer> iterator()
	{
		return clusteredData.iterator();
	}

	/**
	 * Overrides Object's toString
	 * @return a string that models the state of the cluster
	 */
	public String toString(){
		String str="Centroid=(";
		for(int i=0;i<centroid.getLength();i++)
			str+=centroid.get(i)+ " ";
		str+=")";
		return str;

	}

	/**
	 * Overrides Object's toString
	 * @return a string that models the state of the cluster
	 */
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
	/**
	 * implement Comparable's compareTo() by comparing current cluster with cluster 'o'
	 * @param o is the cluster that will be compared with current cluster 
	 */
	public int compareTo(Cluster o)
	{
		if (clusteredData.size()<this.clusteredData.size())
			return +1;
		else
			return -1;
	}

}

