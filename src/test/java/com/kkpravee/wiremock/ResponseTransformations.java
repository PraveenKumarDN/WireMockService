package com.kkpravee.wiremock;


import com.kkpravee.framework.wiremock.MockConfigurationsBase;
import com.kkpravee.framework.wiremock.MockServicesStubBase;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.kkpravee.framework.wiremock.MockServicesStubBase.postUrlRequestResponseBodyEqualsTransform;

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

        postUrlRequestResponseBodyEqualsTransform(urlPath, jsonStringRequestBody, responseStatusCode, responseStatusMessage, jsonStringResponse) ;

        System.out.println("success");
    }

    @Test
    public void testResponseTransformations2() {
        int responseStatusCode = 200 ;
        String responseStatusMessage = "OK" ;

        String jsonStringRequestBody = "{\"smstext\":\"pp1234567890123456789\",\"result\":\"09243523789\"}";
        String jsonStringResponse = "{\"code\":\"00\",\"result\":\"$(result)\",\"metadata\":\"\",\"customerBankAccounts\":\"1234\",\"vpa\":true}";
        String jsonStringResponse1 = "{\"randomNumber\":$(!RandomInteger), \"got\":\"it\"}" ;

        MockServicesStubBase.postUrlResponseRandomIntGen(urlPath, responseStatusCode, responseStatusMessage, jsonStringResponse1) ;

        System.out.println("success");
    }
}