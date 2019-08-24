package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;

import data.Data;
import data.EmptyDatasetException;
import database.DatabaseConnectionException;
import database.DbAccess;
import database.EmptySetException;
import mining.ClusteringRadiusException;
import mining.QTMiner;

public class ServerOneClient extends Thread
{

	Data data = null;
	QTMiner qt = null;
	String tabella = "";
	double radius;

	private ObjectInputStream in;
	private ObjectOutputStream out;

	public ServerOneClient(Socket s) throws IOException
	{
		out = new ObjectOutputStream(s.getOutputStream());
		in = new ObjectInputStream(s.getInputStream());

		start();
	}

	public void run()
	{

		try
		{
			while (true)
			{
				switch ((int) in.readObject())
				{
				case 0:
					tabella = (String) in.readObject();
					try
					{
						DbAccess.initConnection();
						out.writeObject("OK");
					}
					catch (DatabaseConnectionException e2)
					{

						e2.printStackTrace();
					}

					data = new Data(tabella);
					// out.writeObject(data);
					DbAccess.closeConnection();

					break;

				case 1:
					radius = (double) in.readObject();
					qt = new QTMiner(radius);
					try
					{
						int numC = qt.compute(data);
						out.writeObject("OK");
						out.writeObject(numC);
						out.writeObject(qt.getC().toString(data));
					}
					catch (ClusteringRadiusException e)
					{
						e.printStackTrace();
					}

					break;

				case 2:
					try
					{
						String fileName = tabella + radius + ".dmp";
						qt.salva(fileName);
						out.writeObject("OK");
					}
					catch (IOException e)
					{
						e.printStackTrace();
					}
					break;

				case 3:
					String fileNameRead = null;
					try
					{
						String tableName = (String) in.readObject();
						double radiusFile = (double) in.readObject();
						fileNameRead = tableName + radiusFile + ".dmp";
						out.writeObject("OK");
						QTMiner output = new QTMiner(fileNameRead);
						out.writeObject(output.toString());
					}
					catch (IOException e)
					{
						out.writeObject("File doesnt't exists!!");
						e.printStackTrace();
					}
					break;
				}
			}

		}
		catch (ClassNotFoundException | IOException | EmptyDatasetException | SQLException | EmptySetException e)
		{

			e.printStackTrace();
		}

	}
}
