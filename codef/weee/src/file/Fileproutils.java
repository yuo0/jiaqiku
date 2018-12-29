package file;

import java.io.FileInputStream;
import java.util.Properties;

public class Fileproutils
{
	public static   String  getImageUtilPath()
	{
		Properties  p = new Properties();
		try
		{
			p.load(new FileInputStream("./application.properties"));
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return p.getProperty("imgurl");
		
		
	}

}
