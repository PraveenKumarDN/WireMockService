package com.kkpravee.wiremock;


import com.kkpravee.framework.wiremock.MockConfigurationsBase;
import com.kkpravee.framework.wiremock.MockServicesStubBase;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.kkpravee.framework.wiremock.MockServicesStubBase.postUrlRequestResponseBodyEqualsTransform;

public class ResponseTransformations extends MockConfigurationsBase {
    private String urlPath = "/v1/kkpravee/test/resource";

    @BeforeClass
    public void initializations(){
        initWireMockServer();
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

    @Test
    public void replaceVariablePlaceHolder(){
        String jsonStringRequestBody = "{\"var\":1111}";
        String jsonStringResponseBody = "{\"name\":$(name), \"got\":\"it\"}" ;
        int responseStatusCode = 200;
        String responseStatusMessage = "OK" ;

        MockServicesStubBase.postUrlRequestResponseBodyMatchRandomIntGen(urlPath, jsonStringRequestBody, responseStatusCode, responseStatusMessage, jsonStringResponseBody) ;

        System.out.println("success");
    }

    /**
     * 1. uri: http://localhost:8080/v1/kkpravee/test/resource
     * 2. httpMethod: POST
     * 3. requestBody:
     * <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:inc="http://www.service-now.com/incident">
     * <soapenv:Header/>
     * <soapenv:Body>
     * <name_id>kkpravee aakash</name_id>
     * <ser:transferCurrencyCode>INR</ser:transferCurrencyCode>
     * <sys_id>12345678</sys_id>
     * </soapenv:Body>
     * </soapenv:Envelope>
     * 4. response:
     * <soapenv:Header/>
     * <soapenv:Body>
     * 	<inc:get>
     *             <name_id>kkpravee aakash</name_id>
     *             <kkpravee_id>Praveen</kkpravee_id>
     *   </inc:get>
     * </soapenv:Body>
     * </soapenv:Envelope>
     * 5. response status code: 200 OK
     */
    @Test
    public void replaceVariableHolderSoapUiXml(){
        String xmlStringRequestBody = "<sys_id>12345678</sys_id>\n"  ;
        String xmlStringResponseBody = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:inc=\"http://www.service-now.com/incident\">\n" +
                "<soapenv:Header/>\n" +
                "<soapenv:Body>\n" +
                "<inc:get>\n" +
                "<name_id>$(Body.name_id.value)</name_id>\n" +
                "<kkpravee_id>Praveen</kkpravee_id>\n" +
                "</inc:get>\n" +
                "</soapenv:Body>\n" +
                "</soapenv:Envelope>" ;
        int responseStatusCode = 200;
        String responseStatusMessage = "OK" ;

        MockServicesStubBase.postUrlRequestResponseBodyMatchInterpolateSoapXml(urlPath, xmlStringRequestBody, responseStatusCode, responseStatusMessage, xmlStringResponseBody); ;

        System.out.println("success");
    }

}
