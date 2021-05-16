package api.rentalcar.utility;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;

//import com.github.tomakehurst.*;
public class TestBase {
    public static int iPort = 8088;
   public WireMockServer wireMockServer;

    public static String sLog4jPath=System.getProperty("user.dir")+"\\testdata\\log4j.properties";
    Logger log=Logger.getLogger(getClass().getSimpleName());
    public static CommonUtility oComUtility=new CommonUtility();
    public static ResponseUtility oResponseUtility=new ResponseUtility();
    @BeforeSuite
    public void setupWireMockServer() throws IOException {
        wireMockServer=new WireMockServer(iPort);
        WireMock wireMock=new WireMock("localhost",1999);
        wireMockServer.start();
        log.info("Server is started");
         oComUtility.loadLog4jProperty(sLog4jPath);
    }
    @AfterSuite
    public void teardownWireMockServer(){

        wireMockServer.stop();
        log.info("Server is stopped");
    }

}