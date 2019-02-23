package com.kkpravee.framework.wiremock;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.common.ConsoleNotifier;
import com.github.tomakehurst.wiremock.common.Notifier;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.stubbing.StubMapping;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;
import static com.github.tomakehurst.wiremock.client.WireMock.equalToJson;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.removeStub;
import static com.kkpravee.framework.wiremock.MockServicesStubBase.defaultCatchAllStubs;

import org.springframework.web.client.RestTemplate;
import resources.utils.Constants ;
import resources.utils.PropertiesUtility;

/**
 * @author praveenkumar
 * This class is used to configure the required wiremock details.
 */
@SuppressWarnings("JavaDoc")
public class MockConfigurationsBase{
    //Wiremock declarations
    public static WireMockServer wireMockServer;
    public static List<StubMapping> stubs;

    public static RestTemplate restTemplate;

    static {
        stubs = new ArrayList<>();
    }

    //Load the mocking config details.
    public static InputStream inputStream = MockConfigurationsBase.class.getResourceAsStream(
            Constants.ENV_PROPERTIES_FILE_LOCATION);
    public static String mockIp, mockPort ;

    /**
     * 1. This method loads the mock ip and port data from config files.
     * 2. Start the wiremock server on configured mock ip and port.
     */
    public static void initWireMockServer(){
        //load server details from properties file
        Properties properties = PropertiesUtility.readProperties(inputStream);
        mockIp = properties.getProperty("mockIp");
        mockPort = properties.getProperty("mockPort");

        //verbose logging by wiremock custom console notifier
        Notifier notifier = new ConsoleNotifier(true);
        WireMockConfiguration.wireMockConfig().notifier(notifier);

        restTemplate = new RestTemplate();

        //Start the wiremock server on configured ip and port.
        wireMockServer = new WireMockServer();
        wireMockServer.start();
        configureFor(mockIp, Integer.parseInt(mockPort));
        defaultCatchAllStubs() ;
    }

    /**
     * 1. Removes all the stubbed services.
     * 2. Stops the wiremock server
     */
    public static void unMockServicesAndStop(){
        for (StubMapping stub : stubs) {
            removeStub(stub);
        }
        if(!wireMockServer.isRunning()){
            System.out.println("Wiremock server has stopped intermittently.");
        } else
            wireMockServer.stop();
    }
}
