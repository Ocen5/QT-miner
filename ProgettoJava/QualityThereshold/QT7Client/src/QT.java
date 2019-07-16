
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;

import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import java.awt.event.ActionListener;

public class QT extends JApplet
{

	private ObjectOutputStream out;
	private ObjectInputStream in;

	public QT() 
	{
		TabbedPane tab=new TabbedPane();
		tab.init();
	}


	private class TabbedPane extends JPanel
	{

		private JPanelCluster panelDB;
		private JPanelCluster panelFile;

		TabbedPane()
		{
			super(new GridLayout(1, 1));
			JTabbedPane tabbedPane = new JTabbedPane();
			// copy img in src Directory and bin directory
			java.net.URL imgURL = getClass().getResource("img/DB.png");
			ImageIcon iconDB = new ImageIcon(imgURL);
			panelDB = new JPanelCluster("DB", new ActionListener()
			{

				public void actionPerformed(ActionEvent e)
				{
					try
					{
						learningFromDBAction();
					}
					catch (SocketException e1)
					{
						System.out.println(e1);

					}
					catch (FileNotFoundException e1)
					{
						System.out.println(e1);

					}
					catch (IOException e1)
					{
						System.out.println(e1);

					}
					catch (ClassNotFoundException e1)
					{
						System.out.println(e1);

					}

				}
			});
			tabbedPane.addTab("DB", iconDB, panelDB, "Does nothing");

			imgURL = getClass().getResource("img/file.png");
			ImageIcon iconFile = new ImageIcon(imgURL);
			panelFile = new JPanelCluster("STORE FROM FILE", new ActionListener()
			{

				public void actionPerformed(ActionEvent e)
				{
					try
					{
						learningFromFileAction();
					}
					catch (SocketException e1)
					{
						System.out.println(e1);

					}
					catch (FileNotFoundException e1)
					{
						System.out.println(e1);

					}
					catch (IOException e1)
					{
						System.out.println(e1);

					}
					catch (ClassNotFoundException e1)
					{
						System.out.println(e1);

					}

				}
			});
			tabbedPane.addTab("FILE", iconFile, panelFile, "Does nothing");
			// Add the tabbed pane to this panel.
			add(tabbedPane);
			// The following line enables to use scrolling tabs.
			tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		}

		private void learningFromDBAction() throws SocketException, IOException, ClassNotFoundException
		{
			double radius;
			String tableName;
			try
			{
				radius = new Double(panelDB.parameterText.getText()).doubleValue();
				if (radius <= 0)
					throw new NumberFormatException("Radius <= 0");
			}
			catch (NumberFormatException e)
			{
				JOptionPane.showMessageDialog(this, e.toString());
				return;
			}

			tableName = panelDB.tableText.getText();
			out.writeObject(0);
			out.writeObject(tableName);
			String result = (String) in.readObject();
			if (!result.equals("OK"))
			{
				JOptionPane.showMessageDialog(this, "errore dal Server 1");
				return;
			}
			else
			{
				JOptionPane.showMessageDialog(this, "Operazione avvenuta con successo!");

				out.writeObject(1);
				out.writeObject(radius);
				String result2 = (String) in.readObject();
				if (!result2.equals("OK"))
				{
					JOptionPane.showMessageDialog(this, "errore dal Server 2");
					return;
				}
				else
				{
					int numCluster = (int) in.readObject();
					String clusters = (String) in.readObject();
					panelDB.clusterOutput.setText("numero Cluster: " + numCluster + "\n" + clusters);

					out.writeObject(2);
					String result3 = (String) in.readObject();
					if (!result3.equals("OK"))
					{
						JOptionPane.showMessageDialog(this, "errore dal Server 3 ");
						return;
					}
					else
					{
						JOptionPane.showMessageDialog(this, "Operazione di salvataggio avvenuta con successo!");
					}

				}

			}

		}

		private void learningFromFileAction() throws SocketException, IOException, ClassNotFoundException
		{
			out.writeObject(3);

			String fileName = panelFile.tableText.getText();
			out.writeObject(fileName);

			double radius = new Double(panelFile.parameterText.getText()).doubleValue();
			out.writeObject(radius);
			String result = (String) in.readObject();
			if (!result.equals("OK"))
			{
				JOptionPane.showMessageDialog(this, "errore dal server 4");
				return;
			}
			else
			{
				JOptionPane.showMessageDialog(this, "Operazione avvenuta con successo!");
				panelFile.clusterOutput.setText((String)in.readObject());
			}

		}

		public void init()
		{

			TabbedPane tab = new TabbedPane();
			getContentPane().setLayout(new GridLayout(1, 1));
			getContentPane().add(tab);

			String ip ="127.0.0.1"; //TODO getParameter("ServerIP");

			int port = 8080;//TODO new Integer(getParameter("Port")).intValue();
			try
			{
				InetAddress addr = InetAddress.getByName(ip); //ip
				System.out.println("addr = " + addr);
				Socket socket = new Socket(addr, port); //Port
				System.out.println(socket);

				out = new ObjectOutputStream(socket.getOutputStream());
				in = new ObjectInputStream(socket.getInputStream());
			}
			catch (IOException e)
			{
				System.out.println("errore connessione!");
				System.exit(0);
			}

		}
	}
}
