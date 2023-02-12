package utils;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ReadProperty {
    public String getPropertyValue(String file, String propertyName) throws IOException {
        FileReader reader = new FileReader("src\\main\\resources\\"+file+".properties");
        Properties p = new Properties();
        p.load(reader);
        return p.getProperty(propertyName);
    }
}
