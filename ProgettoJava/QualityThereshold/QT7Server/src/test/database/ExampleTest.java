package test.database;

import database.DatabaseConnectionException;
import database.DbAccess;
import database.Example;
import data.Data;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

public class ExampleTest {

	private static Example testExample;
	private static Example equalExample;
	private static Example minorExample;
	private static Example majorExample;
	private static String testOutput;
	
	/**
	 * Initializes all necessary attributes
	 */
	@BeforeAll
	public static void setUp() {
		testExample = new Example();
		equalExample = new Example();
		minorExample = new Example();
		majorExample = new Example();
		testExample.add("sunny");
		testExample.add(30.3);
		testExample.add("strong");
		testExample.add(false);
		equalExample.add("sunny");
		equalExample.add(30.3);
		equalExample.add("strong");
		equalExample.add(false);
		minorExample.add("sunny");
		minorExample.add(12.5);
		minorExample.add("weak");
		minorExample.add(true);
		majorExample.add("sunny");
		majorExample.add(30.3);
		majorExample.add("weak");
		majorExample.add(true);
		
		testOutput = "sunny 30.3 strong false ";
	}
	
	/**
	 * Test compareTo() method of Example
	 */
	@Test
	public void testCompareTo() {
		assertEquals(0,testExample.compareTo(equalExample));
		assertEquals(-1,testExample.compareTo(minorExample));
		assertEquals(4,testExample.compareTo(majorExample));
	}
	
	/**
	 * Test toString() method of Example
	 */
	@Test
	public void testToString() {
		setUp();
		assertEquals(testExample.toString(),testOutput);
	}
	
}
