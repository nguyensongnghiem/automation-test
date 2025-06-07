package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Configuration {
    private static Configuration instance;
    private Properties configProps = new Properties();
    private Logger logger = LoggerFactory.getLogger(Configuration.class);
    public static String propertiesPath = "src/test/resources/properties/local.properties";

    public void loadTestProperties() {
        try {
            configProps.load(new FileInputStream(System.getProperty("user.dir") + "/" + propertiesPath));
        } catch (IOException ex) {
            logger.info(ex.getMessage());
        }
    }

    public String getTestProperty(String property) {
        String result = configProps.getProperty(property);
        if (result == null) {
            throw new RuntimeException("Could not resolve config key " + property + ". Please check it is defined");
        }
        return result.trim();
    }

    public Configuration() {
        loadTestProperties();
    }

    public static Configuration get() {
        if (instance == null) {
            instance = new Configuration();
        }
        return instance;
    }
}
