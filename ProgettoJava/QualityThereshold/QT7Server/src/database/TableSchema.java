package database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


public class TableSchema {
	
	/**
	 * @attribute db is the database on which make queries
	 */
	DbAccess db;
	
	/**
	 *Inner class
	 */
	public class Column{
		
		/**
		 * @attribute name is the name of the column
		 * @attribute type identifies type of column
		 */
		private String name;
		private String type;
		
		/**
		 * Public constructor
		 * @attribute name is the name of the column
		 * @attribute type identifies type of column
		 */
		Column(String name,String type){
			this.name=name;
			this.type=type;
		}
		
		/**
		 * @return name
		 */
		public String getColumnName(){
			return name;
		}
		
		/**
		 * @return true if column's type is number
		 */
		public boolean isNumber(){
			return type.equals("number");
		}
		
		/**
		 * Overrides Object's toString
		 * @return the string which models the status of the column
		 */
		public String toString(){
			return name+":"+type;
		}
	}
	
	
	List<Column> tableSchema=new ArrayList<Column>();

	/**
	 * Public constructor
	 * @param db is the database on which make queries
	 * @param tableName is the identifier of the table in database
	 * @throws SQLException for every errors in the query's execution
	 */
	public TableSchema(DbAccess db, String tableName) throws SQLException{
		this.db=db;
		HashMap<String,String> mapSQL_JAVATypes=new HashMap<String, String>();
		//http://java.sun.com/j2se/1.3/docs/guide/jdbc/getstart/mapping.html
		mapSQL_JAVATypes.put("CHAR","string");
		mapSQL_JAVATypes.put("VARCHAR","string");
		mapSQL_JAVATypes.put("LONGVARCHAR","string");
		mapSQL_JAVATypes.put("BIT","string");
		mapSQL_JAVATypes.put("SHORT","number");
		mapSQL_JAVATypes.put("INT","number");
		mapSQL_JAVATypes.put("LONG","number");
		mapSQL_JAVATypes.put("FLOAT","number");
		mapSQL_JAVATypes.put("DOUBLE","number");

		Connection con=db.getConnection();
		DatabaseMetaData meta = con.getMetaData();
		ResultSet res = meta.getColumns(null, null, tableName, null);

		while (res.next()) {

			if(mapSQL_JAVATypes.containsKey(res.getString("TYPE_NAME")))
				tableSchema.add(new Column(
						res.getString("COLUMN_NAME"),
						mapSQL_JAVATypes.get(res.getString("TYPE_NAME"))));

		}
		res.close();

	}

	/**
	 * 
	 * @return number of attributes in the schema
	 */
	public int getNumberOfAttributes(){
		return tableSchema.size();
	}

	/**
	 * 
	 * @param index of the column to get
	 * @return the column in schema in position 'index'
	 */
	public Column getColumn(int index){
		return tableSchema.get(index);
	}


}




