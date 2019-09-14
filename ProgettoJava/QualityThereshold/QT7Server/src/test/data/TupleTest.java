package test.data;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import data.Data;
import data.Tuple;
import database.DbAccess;


public class TupleTest {

	private static Data data;
	private static Tuple testTuple;
	private static Tuple emptyTuple;
	private static int maxIndex = 4;
	private static int minIndex = 0;
	private static int inIndex = 2;
	private static int extraIndex = 10;
	private static Set<Integer> emptyClusteredData;
	private static Set<Integer> fullClusteredData;
	private static Set<Integer> testClusteredData;
	
	/**
	 * Initializes all necessary attributes
	 */
	@BeforeAll
	public static void setUp() {
		try {
			DbAccess.initConnection();
			data = new Data("playtennis");
		}catch(Exception e) {
			e.printStackTrace();
		}
		testTuple=data.getItemSet(9);
		emptyTuple = new Tuple(0);
		emptyClusteredData = new HashSet<Integer>();	//doesn't insert any value
		fullClusteredData = new HashSet<Integer>();
		for (int i=1; i<15; i++) {
			fullClusteredData.add(i);	//insert all values from 1 to 14
		}
		testClusteredData = new HashSet<Integer>();
		for (int i=1; i<15; i++) {
			testClusteredData.add(i);	//insert odd values only from 1 to 13
			i++;
		}
	}
	
	/**
	 * Test Tuple's constructor
	 */
	@Test
	public void testTuple() {
		Tuple t = new Tuple(5);
		assertEquals(5,t.getLength());
	}
	
	/**
	 * Test Tuple getLength() method
	 */
	@Test
	public void testGetLength() {
		setUp();
		assertEquals(5,testTuple.getLength());
		assertEquals(0,emptyTuple.getLength());
	}
	
	/**
	 * Test Tuple get() method
	 */
	@Test
	public void testGet() {
		assertEquals(testTuple.get(inIndex).toString(),"0.1");
		assertEquals(testTuple.get(maxIndex).toString(),"yes");
		assertEquals(testTuple.get(minIndex).toString(),"sunny");
		
		Exception e = new IndexOutOfBoundsException();
		assertThrows(e.getClass(),()->testTuple.get(extraIndex));
		
		assertNull(emptyTuple.get(inIndex));
	}
	
	/**
	 * Test Tuple getDistance() method
	 */
	@Test
	public void testGetDistance() {
		double value = testTuple.getDistance(data.getItemSet(inIndex));
		assertTrue(value<4.007 && value>4.005);
		
		Exception e = new IndexOutOfBoundsException();
		assertThrows(e.getClass(),()->testTuple.getDistance(emptyTuple));
	}
	
	/**
	 * Test Tuple avgDistance() method
	 */
	@Test
	public void testAvgDistance() {
		double value = testTuple.avgDistance(data, fullClusteredData);
		assertTrue(value<35.48 && value>35.46);   //approximately 35.47
		value = testTuple.avgDistance(data, testClusteredData);
		assertTrue(value<14.39 && value>14.37);  //approximately 14.38
		
		Exception e = new ArithmeticException();
		assertThrows(e.getClass(),()->testTuple.avgDistance(data,emptyClusteredData));

		e = new IndexOutOfBoundsException();
		assertThrows(e.getClass(),()->emptyTuple.avgDistance(data,testClusteredData));
	}
	
	/**
	 * Test Tuple toString() method
	 */
	@Test
	public void testToString() {
		assertEquals(testTuple.toString()," sunny 0.1 normal weak yes ");
		assertEquals(emptyTuple.toString()," ");
	}
	
	/**
	 * Test Tuple add() method
	 */
	@Test
	public void testAdd() {
		emptyTuple.add(testTuple.get(inIndex), inIndex);
		assertEquals(emptyTuple.get(inIndex).toString(),"0.1");
	}
}


