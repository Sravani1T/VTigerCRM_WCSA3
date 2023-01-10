package genericLibraries;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * This class contains the methods  to perform actions on properties file
 * @author AMARSRAVANI
 *
 */

public class PropertyFileUtility 
{
	Properties property; // to made global we are keeping here
	
	/**
	 * This method is used to initialize property file
	 * @param filepath
	 */
	public void propertyFileInitialization(String filepath)
	{
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(filepath);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		property = new Properties();
		try
		{
			property.load(fis);
			
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		
	}
	
	/**
	 *  This method is used to fetch data from properties file
	 * @param key
	 * @return
	 */
	
	public String getDataFromProperties(String key)
	{
		return property.getProperty(key);
	}

}
