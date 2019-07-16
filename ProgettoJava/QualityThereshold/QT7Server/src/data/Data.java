package data;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

import database.DbAccess;
import database.EmptySetException;
import database.Example;
import database.TableData;

public class Data implements Serializable{

	private List<Example> data; //tabella per contenere i valori 
	//private int numberOfExamples;//numero di righe della tabella
	private List<Attribute> attributeSet;//lista di 5 stringhe ovvero le colonne
	public Data(String table) throws EmptyDatasetException, SQLException, EmptySetException{

		//Costruire e inizializzare la matrice data
		data=new ArrayList<Example>();

		//Attribute Set
		attributeSet = new LinkedList<Attribute>();


		//memorizzo le transazioni secondo lo schema della tabella nelle specifiche:

		TreeSet<String> outLookValues=new TreeSet<String>();//vettore dominio di valori che assume la colonna OutLook
		outLookValues.add("overcast");
		outLookValues.add("rain");
		outLookValues.add("sunny");
		//avvalorare ciascune elemento di attributeSet con un oggetto della classe DiscreteAttribute che modella il corrispondente attributo (e.g. outlook, temperature,etc)
		attributeSet.add(0, new DiscreteAttribute("Outlook",0, outLookValues));

		//TreeSet<Double> temperatureValues=new TreeSet<Double>();
		attributeSet.add(1, new ContinuousAttribute("Temperature",1, 0, 30.0));


		TreeSet<String> humidityValues=new TreeSet<String>();
		humidityValues.add("high");
		humidityValues.add("normal");
		attributeSet.add(2, new DiscreteAttribute("Humidity",2, humidityValues));


		TreeSet<String> windValuesValues=new TreeSet<String>();
		windValuesValues.add("weak");
		windValuesValues.add("strong");
		attributeSet.add(3, new DiscreteAttribute("Wind",3, windValuesValues));


		TreeSet<String> playTennisValues=new TreeSet<String>();
		playTennisValues.add("no");
		playTennisValues.add("si");
		attributeSet.add(4, new DiscreteAttribute("PlayTennis",4, playTennisValues));

		Statement statement;

		String query = "select * FROM " + table;

		statement = DbAccess.getConnection().createStatement();
		ResultSet rs = statement.executeQuery(query);
		ResultSetMetaData rsmd = rs.getMetaData();

		try {
		while (rs.next())
		{
			Example currentTuple = new Example();

			for (int i = 0; i < rsmd.getColumnCount(); i++)
			{
				if (rsmd.getColumnTypeName(i + 1).equals("DOUBLE"))
					currentTuple.add(rs.getDouble(i + 1));
				else
					currentTuple.add(rs.getString(i + 1));

			}
			data.add(currentTuple);
		}
		rs.close();
		statement.close();


		} catch (SQLException SQLexc) {
			SQLexc.printStackTrace();
		} /*catch (ClassNotFoundException e) {
			e.printStackTrace();
		} */

		if(data.size()==0)
		{
			throw new EmptyDatasetException();
		}
		if(attributeSet.isEmpty())
		{
			throw new EmptyDatasetException();
		}
	}

	public int getNumberOfExamples(){//restituisce il numero di righe (14)
		return data.size();
	}

	public int getNumberOfAttributes(){//restituisce il numero di colonne (5)
		return attributeSet.size();
	}



	public Object getAttributeValue(int exampleIndex, int attributeIndex) {//restituisce il valore in posizione (exampleIndex,attributeIndex)


		return data.get(exampleIndex).get(attributeIndex);

	}

	List<Attribute> getAttribute(){//restituisce il vettore colonna

		return attributeSet;
	}


	/*stringa che modella lo stato dell'oggetto, 
	creando una stringa per memorizzare la tabella (vedi explanatorySet)
	e le transazioni in data[][], opportunamente enumerate. Restituisce tale stringa: */
	public String toString(){

		String output=" ";

		for(int i=0; i<getNumberOfAttributes(); i++){

			output = output +"  "+((Attribute) attributeSet.get(i)).getName() + ",";

		}
		output = output+"\n";

		for(int i=0; i<data.size(); i++) {
			output = output + i + ": ";

			for(int j=0; j<getNumberOfAttributes(); j++) {

				output = output + data.get(i).get(j)+ ",     ";

			}

			output = output+"\n";
		}

		return output;

	}
	//Crea e restituisce un oggetto di Tuple che modella come sequenza di coppie Attributo-valore la i-esima riga in data.
	public Tuple getItemSet(int index)
	{
		Tuple tuple = new Tuple(attributeSet.size());
		for (int i = 0; i < attributeSet.size(); i++)
		{

			if (attributeSet.get(i) instanceof ContinuousAttribute)
			{
				tuple.add(new ContinuousItem((ContinuousAttribute) attributeSet.get(i),new Double(data.get(index).get(i).toString())),i);
			}
			else
			{
				tuple.add(new DiscreteItem((DiscreteAttribute) attributeSet.get(i), (String) data.get(index).get(i)),i);
			}

		}

		return tuple;
	}

}
