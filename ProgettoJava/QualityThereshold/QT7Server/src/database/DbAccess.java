package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbAccess {
	private final static String DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";// (Per utilizzare questo Driver scaricare e aggiungere al classpath il connettore mysql connector)
	private final static String DBMS = "jdbc:mysql";
	private final static String SERVER= "localhost"; // identificativo del server della base di dati
	private final static String DATABASE = "MapDB"; // nome della base di dati
	private final static String PORT= "3306"; // porta su cui il DBMS MySQL accetta le connessioni
	private final static String USER_ID = "MapUser"; //nome utente per l'accesso
	private final static String PASSWORD = "map"; // la password di USER_ID
	private static Connection conn; // gestisce una connessione


	/*impartisce al class loader l’ordine di caricare il driver mysql, 
	 * inizializza la connessione riferita da conn. Il metodo solleva e propaga una eccezione
	 *  di tipo DatabaseConnectionException in caso di fallimento nella connessione al database. 
	 *  Suggerimento: La stringa di connessione sarà: DBMS+"://" + SERVER + ":" + PORT + "/" + DATABASE
	 *  */
	public static void initConnection() throws DatabaseConnectionException, ClassNotFoundException, SQLException
	{	
		Class.forName(DRIVER_CLASS_NAME); 
		conn = DriverManager.getConnection(DBMS+"://"+SERVER+":"+PORT+"/"+DATABASE+"?useSSL=false",USER_ID,PASSWORD);
	}

	public static Connection getConnection()
	{
		return conn;
	}


	public static void closeConnection()// chiude la connessione conn
	{
		try {
			conn.close();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

}

