package com.freecharge.wiremock;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.springframework.web.client.RestTemplate;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;

/**
 * @author praveenkumar
 * This class is used to configure the required wiremock details.
 */
public class ConfigurationTestBase {

    public static RestTemplate restTemplate;
    private WireMockServer wireMockServer;

    @BeforeSuite
    void configureSystemUnderTest() {
        restTemplate = new RestTemplate();
        wireMockServer = new WireMockServer();
        wireMockServer.start();
        configureFor("10.251.32.153", 8080);
    }

    @AfterSuite
    void stopWireMockServer() {
        wireMockServer.stop();
    }
}
