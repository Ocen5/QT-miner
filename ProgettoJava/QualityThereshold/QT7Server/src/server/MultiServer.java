package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiServer {

	private static int PORT = 8080;

	public static void main(String[] args) throws IOException {
		run();
	}

	private static void run() throws IOException
	{
		ServerSocket s = new ServerSocket (PORT);
		System.out.println("Server Started: " + s);
		try 
		{
			Socket socket =s.accept();

			try 
			{
				System.out.println ("Connection accepted: "+socket);
				new ServerOneClient(socket);

			} 
			catch (IOException e)
			{
				socket.close();
			} 

		}
		finally
		{
			s.close();
		}

	}

}
