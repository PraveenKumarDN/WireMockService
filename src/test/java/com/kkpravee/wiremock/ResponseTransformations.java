package com.kkpravee.wiremock;


import com.kkpravee.framework.wiremock.MockConfigurationsBase;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.kkpravee.framework.wiremock.MockServicesStubBase.postUrlRequestMatchResponseCustom1;

public class ResponseTransformations extends MockConfigurationsBase {
    private String url;
    private String urlPath = "/v1/kkpravee/test/resource";

    @BeforeClass
    public void initializations(){
        initWireMockServer();
        url = "http://"+mockIp+":"+mockPort+urlPath ;
    }

    @AfterClass
    public void deInitializations(){
        unMockServicesAndStop();
    }

    @Test
    public void testResponseTransformations1() {
        int responseStatusCode = 200 ;
        String responseStatusMessage = "OK" ;

        String jsonStringRequestBody = "{\"smstext\":\"pp1234567890123456789\",\"result\":\"09243523789\"}";
        String jsonStringResponse = "{\"code\":\"00\",\"result\":\"$(result)\",\"metadata\":\"\",\"customerBankAccounts\":\"1234\",\"vpa\":true}";

        postUrlRequestMatchResponseCustom1(urlPath, jsonStringRequestBody, responseStatusCode, responseStatusMessage, jsonStringResponse) ;

        System.out.println("success");

        /*StubMapping stubMapping;
        stubMapping = stubFor(post(urlEqualTo("/test?foo=bar"))
                        .willReturn(aResponse()
                                .withStatus(200)
                                .withHeader("content-type", "application/json")
                                .withBody("{\"foo\": \"$(foo)\"}")
                                .withTransformers("body-transformer")));

        given()
                .contentType("application/json")
                .when()
                .get("/test?foo=bar")
                .then()
                .statusCode(200)
                .body("foo",equalTo("bar"));

        verify(getRequestedFor(urlEqualTo("/test?foo=bar")));*/
    }
}