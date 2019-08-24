package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiServer {

	private static int PORT = 8080;
	private static ServerSocket s;

	public static void main(String[] args) throws IOException {
		run();
	}

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
	
	public static void close() {
		try {
			s.close();
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}
}

