package utility;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {
    Properties pro;
    public  ConfigReader() {
        try {
            File file = new File("./Config/config.properties");
            FileInputStream fis = new FileInputStream(file);
            pro=new Properties();
            pro.load(fis);
        } catch (Exception ex) {
            System.out.println("ConfigReader error");
        }
    }

    public String readDataFromConfig(String keytoSearch)
    {
        return pro.getProperty(keytoSearch);
    }
    public String getBrowser()
    {
        return pro.getProperty("Browser");
    }

    public String getUrl()
    {
        return pro.getProperty("Url");
    }
}
