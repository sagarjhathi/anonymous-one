package configManager;

import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {

	
	private static Properties prop= new Properties();
	
	static {
		
		try {
			InputStream input=ConfigManager.class.getClassLoader().getResourceAsStream("config.properties");
			
			prop.load(input);
		}catch(Exception e) {
            throw new RuntimeException("Failed to load config.properties");
		}
	}
	
	public static String get(String key) {
		return prop.getProperty(key);
	}
}
