package test.mining;

import data.Data;
import mining.ClusteringRadiusException;
import mining.QTMiner;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.*;

public class QTMinerTest {

	private static Data data;
	private static int minRadius;
	private static int inRadius;
	private static int maxRadius;
	private static int extraRadius;
	private static QTMiner minLimit;
	private static QTMiner maxLimit;
	private static QTMiner inLimit;
	private static QTMiner extraLimit;
	private static String minOutput;
	private static String inOutput;
	private static String maxOutput;
	
	/**
	 * Initializes all necessary attributes
	 */
	@BeforeAll
	public static void setUp() {
		try {
			data = new Data("playtennis");
			minRadius = 1;
			inRadius = 2;
			maxRadius = 3;
			extraRadius = 10;
			minLimit = new QTMiner(minRadius);
			maxLimit = new QTMiner(maxRadius);
			inLimit = new QTMiner(inRadius);
			extraLimit = new QTMiner(extraRadius);
			
			minOutput = "1:Centroid=( rain 12.5 high strong no )\n" + 
					"Examples:\n" + 
					"[ rain 12.5 high strong no ] dist=0.0\n" + 
					"\nAvgDistance=0.0\n" + 
					"2:Centroid=( overcast 29.21 normal weak yes )\n" + 
					"Examples:\n" + 
					"[ overcast 29.21 normal weak yes ] dist=0.0\n" + 
					"\nAvgDistance=0.0\n" + 
					"3:Centroid=( overcast 12.5 high strong yes )\n" + 
					"Examples:\n" + 
					"[ overcast 12.5 high strong yes ] dist=0.0\n" + 
					"\nAvgDistance=0.0\n" + 
					"4:Centroid=( sunny 12.5 normal strong yes )\n" + 
					"Examples:\n" + 
					"[ sunny 12.5 normal strong yes ] dist=0.0\n" + 
					"\nAvgDistance=0.0\n" + 
					"5:Centroid=( sunny 0.1 normal weak yes )\n" + 
					"Examples:\n" + 
					"[ sunny 0.1 normal weak yes ] dist=0.0\n" + 
					"\nAvgDistance=0.0\n" + 
					"6:Centroid=( overcast 0.1 normal strong yes )\n" + 
					"Examples:\n" + 
					"[ overcast 0.1 normal strong yes ] dist=0.0\n" + 
					"\nAvgDistance=0.0\n" + 
					"7:Centroid=( rain 0.0 normal strong no )\n" + 
					"Examples:\n" + 
					"[ rain 0.0 normal strong no ] dist=0.0\n" + 
					"\nAvgDistance=0.0\n" + 
					"8:Centroid=( rain 13.0 high weak yes )\n" + 
					"Examples:\n" + 
					"[ rain 13.0 high weak yes ] dist=0.0\n" + 
					"\nAvgDistance=0.0\n" + 
					"9:Centroid=( overcast 30.0 high weak yes )\n" + 
					"Examples:\n" + 
					"[ overcast 30.0 high weak yes ] dist=0.0\n" + 
					"\nAvgDistance=0.0\n" + 
					"10:Centroid=( rain 0.0 normal weak yes )\n" + 
					"Examples:\n" + 
					"[ rain 0.0 normal weak yes ] dist=0.0\n" + 
					"[ rain 12.0 normal weak yes ] dist=0.4\n" + 
					"\nAvgDistance=0.2\n" + 
					"11:Centroid=( sunny 30.3 high weak no )\n" + 
					"Examples:\n" + 
					"[ sunny 30.3 high weak no ] dist=0.0\n" + 
					"[ sunny 30.3 high strong no ] dist=1.0\n" + 
					"[ sunny 13.0 high weak no ] dist=0.5766666666666667\n" +
					"\nAvgDistance=0.5255555555555556\n";
			
			inOutput = "1:Centroid=( sunny 30.3 high weak no )\n" + 
					"Examples:\n" + 
					"[ sunny 30.3 high weak no ] dist=0.0\n" + 
					"[ sunny 30.3 high strong no ] dist=1.0\n" + 
					"[ sunny 13.0 high weak no ] dist=0.5766666666666667\n" + 
					"\nAvgDistance=0.5255555555555556\n" + 
					"2:Centroid=( overcast 12.5 high strong yes )\n" + 
					"Examples:\n" + 
					"[ overcast 30.0 high weak yes ] dist=1.5833333333333333\n" + 
					"[ overcast 0.1 normal strong yes ] dist=1.4133333333333333\n" + 
					"[ sunny 12.5 normal strong yes ] dist=2.0\n" + 
					"[ overcast 12.5 high strong yes ] dist=0.0\n" + 
					"[ rain 12.5 high strong no ] dist=2.0\n" + 
					"\nAvgDistance=1.3993333333333333\n" + 
					"3:Centroid=( rain 0.0 normal weak yes )\n" + 
					"Examples:\n" + 
					"[ rain 13.0 high weak yes ] dist=1.4333333333333333\n" + 
					"[ rain 0.0 normal weak yes ] dist=0.0\n" + 
					"[ rain 0.0 normal strong no ] dist=2.0\n" + 
					"[ sunny 0.1 normal weak yes ] dist=1.0033333333333334\n" + 
					"[ rain 12.0 normal weak yes ] dist=0.4\n" + 
					"[ overcast 29.21 normal weak yes ] dist=1.9736666666666667\n" + 
					"\nAvgDistance=1.1350555555555557\n";
			
			maxOutput = "1:Centroid=( sunny 12.5 normal strong yes )\n" + 
					"Examples:\n" + 
					"[ sunny 30.3 high strong no ] dist=2.5933333333333333\n" + 
					"[ rain 0.0 normal strong no ] dist=2.416666666666667\n" + 
					"[ sunny 12.5 normal strong yes ] dist=0.0\n" + 
					"[ rain 12.5 high strong no ] dist=3.0\n" + 
					"\nAvgDistance=2.0025\n" + 
					"2:Centroid=( overcast 30.0 high weak yes )\n" + 
					"Examples:\n" + 
					"[ sunny 30.3 high weak no ] dist=2.01\n" + 
					"[ overcast 30.0 high weak yes ] dist=0.0\n" + 
					"[ rain 13.0 high weak yes ] dist=1.5666666666666667\n" + 
					"[ rain 0.0 normal weak yes ] dist=3.0\n" + 
					"[ overcast 0.1 normal strong yes ] dist=2.9966666666666666\n" + 
					"[ sunny 13.0 high weak no ] dist=2.5666666666666664\n" + 
					"[ sunny 0.1 normal weak yes ] dist=2.9966666666666666\n" + 
					"[ rain 12.0 normal weak yes ] dist=2.6\r\n" + 
					"[ overcast 12.5 high strong yes ] dist=1.5833333333333333\n" + 
					"[ overcast 29.21 normal weak yes ] dist=1.0263333333333333\n" +
					"\nAvgDistance=2.034633333333333\n";
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Test compute() method and toString(Data data) method of ClusterSet
	 */
	@Test
	public void testCompute() {
		setUp();
		int numClusters;
		try {
			numClusters = minLimit.compute(data);
			assertEquals(numClusters,11);
			assertEquals(minLimit.getC().toString(data),minOutput);
			
			numClusters = inLimit.compute(data);
			assertEquals(numClusters,3);
			assertEquals(inLimit.getC().toString(data),inOutput);
			
			numClusters = maxLimit.compute(data);
			assertEquals(numClusters,2);
			assertEquals(maxLimit.getC().toString(data),maxOutput);
			
			Exception e = new ClusteringRadiusException();
			assertThrows(e.getClass(),()->extraLimit.compute(data));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
