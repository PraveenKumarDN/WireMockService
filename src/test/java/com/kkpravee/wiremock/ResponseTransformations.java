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

        String jsonStringRequestBody = "{\"key1\":\"value1\",\"key2\":\"value2\"}";
        String jsonStringResponse = "{\"responseId\":\"123456\",\"result\":\"$(result)\",\"uniqueId\":true}";

        postUrlRequestResponseBodyEqualsTransform(urlPath, jsonStringRequestBody, responseStatusCode, responseStatusMessage, jsonStringResponse) ;

        System.out.println("success");
    }

    @Test
    public void testResponseTransformations2() {
        int responseStatusCode = 200 ;
        String responseStatusMessage = "OK" ;

        String jsonStringRequestBody = "{\"key1\":\"value1\",\"key2\":\"value2\"}";
        String jsonStringResponse = "{\"responseId\":\"123456\",\"result\":\"$(result)\",\"uniqueId\":true}";
        String jsonStringResponse1 = "{\"randomNumber\":$(!RandomInteger), \"got\":\"it\"}" ;

        MockServicesStubBase.postUrlResponseRandomIntGen(urlPath, responseStatusCode, responseStatusMessage, jsonStringResponse1) ;

        System.out.println("success");
    }
}
