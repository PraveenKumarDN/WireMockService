package com.kkpravee.wiremock;

import com.kkpravee.framework.wiremock.MockServicesStubBase;
import org.springframework.http.ResponseEntity;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * @author praveenkumar
 * This class is used to stub the request response mock services.
 */
public class MockServices extends MockServicesStubBase {
    //declare the required rest api test data
    private String urlPath;
    private String headerKey;
    private String headerValue;
    private String queryParam;
    private String queryParamValue;
    private String requestBodyEquals;
    private String requestBodyContains;
    private int responseStatusCode;
    private String responseStatusMessage;
    private String responseBody;

    private String url;

    //initialise the required test data.
    public MockServices() {
        urlPath = "/v1/kkpravee/test/resource";
        headerKey = "Content-Type";
        headerValue = "application/json";
        queryParam = "query";
        queryParamValue = "queryValue" ;
        requestBodyEquals = "{\"key1\":\"value1\",\"key2\":\"value2\",\"name\":\"Praveen Kumar\",\"key3\":\"ASDF\"}";
        requestBodyContains = "{\"key1\":\"value1\",\"key2\":\"value2\"}";
        responseStatusMessage = "OK";
        responseBody = "{\"responseid\":\"10\",\"result\":\"success\",\"asdfgh\":true}";
        responseStatusCode = 200;
        url = null;
    }

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
    void mockGetService() {
        ResponseEntity<String> response;

        //call static method which will mock the basic http get service.
        getUrlRequestMatchResponse(urlPath, responseStatusCode, responseStatusMessage, responseBody);

        //access the mocked http get service using RestTemplate ClientHttpRequestFactory.
        response = restTemplate.getForEntity(url, String.class);
        if(null != response){
            //validate the mocked response status
            int status = response.getStatusCode().value() ;
            if(status == responseStatusCode){
                System.out.println("GET response status mock is success");
            }else{
                System.out.println("GET response status mock is not success");
            }

            //validate the mocked response body
            String body = response.getBody() ;
            if(body.equals(responseBody)){
                System.out.println("GET response body mock is success");
            }else{
                System.out.println("GET response body mock is not success");
            }
        }

        /*//similarly other required get stubs could be done
        getUrlRequestHeaderMatch(urlPath, headerKey, headerValue, responseStatusCode, responseStatusMessage);
        getUrlRequestHeaderParamMatch(urlPath, queryParam, queryParamValue, headerKey, headerValue, responseStatusCode, responseStatusMessage);
        getUrlRequestHeaderParamMatchResponse(urlPath, queryParam, queryParamValue, headerKey, headerValue, responseStatusCode, responseStatusMessage, responseBody);*/
    }

    @Test
    void mockPostService() {
        ResponseEntity<String> response;

        //call static method which will mock the basic http post service.
        postUrlRequestBodyEqualsResponse(urlPath, requestBodyEquals, responseStatusCode, responseBody);

        //access the mocked http post service using RestTemplate ClientHttpRequestFactory.
        response = restTemplate.postForEntity(url, requestBodyEquals, String.class) ;
        if(null != response){
            //validate the mocked response status
            int status = response.getStatusCode().value() ;
            if(status == responseStatusCode){
                System.out.println("POST response status mock is success");
            }else{
                System.out.println("POST response status mock is not success");
            }

            String body = response.getBody() ;
            if(body.equals(responseBody)){
                System.out.println("POST response body mock is success");
            }else{
                System.out.println("POST response body mock is not success");
            }
        }

       /* //similarly other required get stubs could be done
        postUrlRequestBodyEquals(urlPath, requestBodyEquals, responseStatusCode, responseStatusMessage);
        postUrlRequestBodyMatch(urlPath, requestBodyEquals, responseStatusCode, responseStatusMessage);
        postUrlRequestHeaderBodyEquals(urlPath, headerKey, headerValue, requestBodyEquals, responseStatusCode, responseStatusMessage);
        postUrlRequestHeaderBodyEqualsResponse(urlPath, headerKey, headerValue, requestBodyEquals, responseStatusCode, responseStatusMessage, responseBody);*/
    }

}
