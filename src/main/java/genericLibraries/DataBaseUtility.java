package genericLibraries;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.jdbc.Driver;

/**
 * This class contains methods to perform actions on DataBase
 * @author AMARSRAVANI
 *
 */

public class DataBaseUtility 
{
	Connection connection;  // we are making it as global
	
	/**
	 * This method is used to establish the connection
	 * @param url
	 * @param username
	 * @param password
	 */
	
	public void openDatabaseConnection(String url, String username,String password)
	{
		Driver dbDriver =  null;
		
		try {
			dbDriver = new Driver();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			DriverManager.registerDriver(dbDriver);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	/**
	 * This method is used to fetch the column data from database
	 * @param query
	 * @param ColumnName
	 * @param columnName
	 * @return
	 * @throws SQLException
	 */
	
	public List<String> fetchDataFromDatabase(String query, String ColumnName, String columnName) throws SQLException
	{
		Statement statement = null;
		ResultSet result = null;
		
		try {
			statement = connection.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			result = statement.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<String> list = new ArrayList<>();
		while(result.next())
		{
			list.add(result.getString(columnName));
		}
		return list;
		
		
	}
	
	/**
	 * This method is used to modify the database
	 * @param query
	 * @return
	 * @throws SQLException
	 */
	
	public int modifyDataInDatabase(String query) throws SQLException
	{
		Statement statement = connection.createStatement();
		int result = statement.executeUpdate(query);
		return result;
	}
	
	/**
	 * This method is used to close database connection
	 */
	
	public void closeDataBase()
	{
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
