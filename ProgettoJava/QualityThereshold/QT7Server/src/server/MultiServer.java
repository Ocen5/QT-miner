package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiServer {

	/**
	 * @attribute PORT is the port that ServerSocket will use to accept Clients' connection 
	 * 			  and build Socket
	 * @attribute s is the ServerSocket that manages MultiServer
	 */
	private static int PORT = 8080;
	private static ServerSocket s;

	public static void main(String[] args) throws IOException {
		run();
	}

	/**
	 * Builds ServerSocket to the PORT 8080 and accept every client building 
	 * an instance of ServerOneClient (subClass of Thread) that manage the connection
	 * with a single Client through its own Thread. 
	 * @throws IOException when connection between ServerSocket and PORT fails
	 */
	private static void run() throws IOException
	{
		s = new ServerSocket (PORT);
		System.out.println("Server Started : " + s);
		{
			try {
				while (true) {
					Socket socket = s.accept();
					System.out.println("accepting " + socket);
					try {
						new ServerOneClient(socket);
					} catch (IOException e) {
						System.err.println(e.getMessage());
					}
				}
			} catch (IOException e) {
				System.err.println(e.getMessage());
			} finally {
				close();
			}
		}
	}
	
	/**
	 * Close the ServerSocket and every Socket still connected to some Client
	 */
	public static void close() {
		try {
			s.close();
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}
}

