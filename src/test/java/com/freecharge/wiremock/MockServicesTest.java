package com.freecharge.wiremock;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.givenThat;
import static com.github.tomakehurst.wiremock.client.WireMock.urlMatching;

/**
 * @author praveenkumar
 * This class is used to test the stub request response mock services.
 */
public class MockServicesTest extends ConfigurationTestBase{

    @Test
    void mockGetService() {
        givenThat(get(urlMatching("/v1/wiremock/test1")).willReturn(aResponse()
                .withStatus(200)
                .withBody("Wiremock response body in json string stubbed by java automation for test1 resource path match")
        ));

        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080/v1/wiremock/test0", String.class);
        int responseStatus = response.getStatusCode().value() ;
        int expectedStatus = HttpStatus.OK.value() ;
        Assert.assertEquals(responseStatus, expectedStatus);
    }

}
