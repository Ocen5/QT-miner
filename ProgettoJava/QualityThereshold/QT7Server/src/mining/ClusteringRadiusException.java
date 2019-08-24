package mining;

public class ClusteringRadiusException extends Exception{

	public ClusteringRadiusException()
	{
		System.err.println("more than 14 tuples in one cluster!");
	}

}
