package geocoding;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConfigUtils {

    static public String getPropertyFromConfig(String property) {

        try (InputStream input = ConfigUtils.class.getClassLoader().getResourceAsStream("config.properties")) {

            Properties prop = new Properties();

            if (input == null) {
                throw new RuntimeException("Unable to find config.properties");
            }

            //load a properties file from class path, inside static method
            prop.load(input);

            //get the property value and print it out
            return prop.getProperty(property);

        } catch (IOException ex) {
            Logger.getLogger(ConfigUtils.class.getName()).log(Level.SEVERE, "error reading from config", ex);
            throw new RuntimeException("unable to get properties from config");
        }
    }
}
