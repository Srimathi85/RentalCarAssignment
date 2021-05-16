package api.rentalcar.utility;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class CommonUtility {
    Properties oProperties=new Properties();
    FileInputStream oFileInStream=null;
    Logger log=Logger.getLogger(getClass().getSimpleName());

    public static String readFileReturnInString(String sPathOfJson) throws Exception{
        byte[] encoded= Files.readAllBytes(Paths.get(sPathOfJson));
        return new String(encoded, StandardCharsets.UTF_8);

    }
    public void loadLog4jProperty(String PropertiesFilePath) throws IOException {
        log.info("Log4j Property File Path : "+PropertiesFilePath);
        oFileInStream=new FileInputStream(PropertiesFilePath);
        oProperties.load(oFileInStream);
        PropertyConfigurator.configure(oProperties);

    }
}
