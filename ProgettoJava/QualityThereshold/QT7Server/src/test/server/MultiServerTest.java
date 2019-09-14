package test.server;

import org.junit.Test;

import server.MultiServer;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.net.BindException;

public class MultiServerTest {

	private static MultiServer serverOne;
	private static MultiServer serverTwo;
	
	/**
	 * Test what happens if someone try to run Server program
	 * while another one is running
	 * @throws IOException 
	 */
	@Test
	public void testRun() throws Exception{
		try {
			 serverOne = new MultiServer();
		} catch (IOException e) {
			fail(e.toString());
		}
		try {
			 serverTwo = new MultiServer();
			fail();
		} catch (IOException e) {
			assertTrue(true);
		} finally {
			MultiServer.close();
		}
	}
	
}






