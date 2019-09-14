package test.data;

import java.sql.SQLException;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.*;

import data.Data;
import data.EmptyDatasetException;
import database.DbAccess;

public class DataTest {

	private static Data data;
	private static String rightTable = "playtennis";
	private static String wrongTable = "playfootball";
	private static String inexistentTable = "playbasket";
	private static String emptyString = "";
	private static int minLimitLine = 1;
	private static int maxLimitLine = 14;
	private static int minLimitColumn = 1;
	private static int maxLimitColumn = 5;
	private static int extraLimitLine = 20;
	private static int extraLimitColumn = 10;
	private static int inLimitLine = 7;
	private static int inLimitColumn = 3;
	private static String dataOutput = "   Outlook,  Temperature,  Humidity,  Wind,  Playtennis\n" + 
			"0: sunny,     30.3,     high,     weak,     no,     \n" + 
			"1: sunny,     30.3,     high,     strong,     no,     \n" + 
			"2: overcast,     30.0,     high,     weak,     yes,     \n" + 
			"3: rain,     13.0,     high,     weak,     yes,     \n" + 
			"4: rain,     0.0,     normal,    weak,     yes,     \n" + 
			"5: rain,     0.0,     normal,     strong,     no,     \n" + 
			"6: overcast,     0.1,     normal,     strong,     yes,     \n" + 
			"7: sunny,    13.0 ,     high,     weak,     no,     \n" + 
			"8: sunny,     0.1,     normal,     weak,     yes,     \n" + 
			"9: rain,     12.0,     normal,     weak,     yes,     \n" + 
			"10: sunny,     12.5,     normal,     strong,     yes,     \n" + 
			"11: overcast,     12.5,     high,     strong,     yes,     \n" + 
			"12: overcast,     29.21,     normal,     weak,     yes,     \n" + 
			"13: rain,     12.5,     high,     strong,     no,     \n";
	
	/**
	 * Initializes all necessary attributes
	 */
	@BeforeAll
	public static void setUp() {
		try {
			DbAccess.initConnection();
			data = new Data(rightTable);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Test Data's constructor :
	 * check what happens if tableName has the name of a right,
	 * wrong and inexistent table
	 */
	@Test
	public void testData()
	{
		setUp();
		assertNotNull(data);
		assertEquals(5,data.getNumberOfAttributes());
		assertEquals(14,data.getNumberOfExamples());
		assertNotNull(data.getAttribute());

		Exception e = new EmptyDatasetException();
		assertThrows(e.getClass(),()-> new Data(wrongTable));

		Exception e1 = new SQLException();
		assertThrows(e1.getClass(),()-> new Data(inexistentTable));
		assertThrows(e1.getClass(),()-> new Data(emptyString));

	}

	/**
	 * Test Data getAttributeValue() method
	 */
	@Test
	public void testGetAttributeValue() {
		
		assertEquals((String) data.getAttributeValue(inLimitLine,inLimitColumn),"normal");
		assertEquals((String) data.getAttributeValue(minLimitLine,minLimitColumn),"sunny");
		assertEquals((String) data.getAttributeValue(maxLimitLine,minLimitColumn),"rain");
		assertEquals((String) data.getAttributeValue(minLimitLine,maxLimitColumn),"no");
		assertEquals((String) data.getAttributeValue(maxLimitLine,maxLimitColumn),"no");

		Exception e = new IndexOutOfBoundsException();
		assertThrows(e.getClass(),()-> data.getAttributeValue(inLimitLine,extraLimitColumn));
		assertThrows(e.getClass(),()-> data.getAttributeValue(extraLimitLine,inLimitColumn));
		assertThrows(e.getClass(),()-> data.getAttributeValue(extraLimitLine,extraLimitColumn));

	}

	/**
	 * Test Data getItemSet() method
	 */
	@Test
	public void testGetItemSet() {
		
		assertEquals(data.getItemSet(inLimitLine).toString(),
				" overcast 0.1 normal strong yes ");
		assertEquals(data.getItemSet(minLimitLine).toString(),
				" sunny 30.3 high weak no ");
		assertEquals(data.getItemSet(maxLimitLine).toString(),
				" rain 12.5 high strong no ");

		Exception e = new IndexOutOfBoundsException();
		assertThrows(e.getClass(),()-> data.getItemSet(extraLimitLine));
	}

	/**
	 * Test Data toString() method
	 */
	@Test
	public void testToString() {
		assertEquals(dataOutput,data.toString());
	}
}

