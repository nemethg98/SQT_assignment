import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader
{
	private Properties properties;
	private static ConfigFileReader configReader;

    private ConfigFileReader()
    {
		BufferedReader reader;
		String propertyFilePath = "resource/Configuration.properties";
		
		try {
			reader = new BufferedReader(new FileReader(propertyFilePath));
			properties = new Properties();
			try {
				properties.load(reader);
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
		}		
	}

    public static ConfigFileReader getInstance( ) {
    	if(configReader == null) {
    		configReader = new ConfigFileReader();
    	}
        return configReader;
    }

    public String getUsername() {
        String username = properties.getProperty("username");
        if (username != null)
        {
			return username;
		}
        else
        {
			throw new RuntimeException("username not specified in the Configuration.properties file.");
		}
    }

    public String getPassword() {
        String password = properties.getProperty("password");
        if (password != null)
        {
			return password;
		}
        else
        {
			throw new RuntimeException("password not specified in the Configuration.properties file.");
		}
    }

    public String getCode() {
        String code = properties.getProperty("code");
        if (code != null)
        {
			return code;
		}
        else
        {
			throw new RuntimeException("code not specified in the Configuration.properties file.");
		}
    }
}
