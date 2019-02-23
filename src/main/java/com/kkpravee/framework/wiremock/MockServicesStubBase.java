package com.kkpravee.framework.wiremock;

import com.github.tomakehurst.wiremock.client.MappingBuilder;
import resources.utils.Constants;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.any;
import static com.github.tomakehurst.wiremock.client.WireMock.containing;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.equalToJson;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.urlMatching;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathMatching;

/**
 * @author praveenkumar
 * This class is used to configure the required wiremock details.
 */
@SuppressWarnings("JavaDoc")
public class MockServicesStubBase extends MockConfigurationsBase{
    /**
     * This static method stubs any url at priority(10) by giving "404 Error" status with
     * custom message as "Rest API endpoint not found".
     */
    public static void defaultCatchAllStubs(){
        MappingBuilder stub = any(urlMatching(".*"))
                .atPriority(10)
                .willReturn(aResponse()
                        .withStatus(404)
                        .withStatusMessage("Error")
                        .withBody("Rest API endpoint not found")
                ) ;
        stubs.add(stubFor(stub));
    }

    /**
     * @param urlPath
     * @param responseStatusCode
     * @param responseStatusMessage
     * This static method stubs the url matching for json response with status and message
     * by ignoring the array order and extra elements if present in json request body.
     * Also this would be given least priority(10)
     */
    public static void postUrlRequestMatch(String urlPath, int responseStatusCode, String responseStatusMessage){
        MappingBuilder stub = post(urlMatching(urlPath))
                .atPriority(10)
                .willReturn(aResponse()
                        .withStatus(responseStatusCode)
                        .withStatusMessage(responseStatusMessage)
                ) ;
        stubs.add(stubFor(stub));
    }

    /**
     * @param urlPath
     * @param responseStatusCode
     * @param responseStatusMessage
     * This static method stubs the url matching for json response with status and message
     * by ignoring the array order and extra elements if present in json request body.
     * Also this would be given least priority(10)
     */
    public static void postUrlRequestMatch(String urlPath, int responseStatusCode, String responseStatusMessage, String jsonStringResponse){
        MappingBuilder stub = post(urlMatching(urlPath))
                .atPriority(10)
                .willReturn(aResponse()
                        .withStatus(responseStatusCode)
                        .withStatusMessage(responseStatusMessage)
                        .withBody(jsonStringResponse)
                ) ;
        stubs.add(stubFor(stub));
    }

    /**
     * @param urlPath
     * @param jsonStringRequestBody
     * @param responseStatusCode
     * @param responseStatusMessage
     * This static method stubs the url matching json string request body for json response with status and message
     * by ignoring the array order and extra elements if present in json request body.
     */
    public static void postUrlRequestBodyMatch(String urlPath, String jsonStringRequestBody, int responseStatusCode, String responseStatusMessage){
        MappingBuilder stub = post(urlMatching(urlPath))
                .withRequestBody(equalToJson(jsonStringRequestBody, Constants.BOOLEAN_TRUE, Constants.BOOLEAN_TRUE))
                .willReturn(aResponse()
                        .withStatus(responseStatusCode)
                        .withStatusMessage(responseStatusMessage)
                ) ;
        stubs.add(stubFor(stub));
    }

    /**
     * @param urlPath
     * @param jsonStringRequestBody
     * @param responseStatusCode
     * @param responseStatusMessage
     * This static method stubs the url matching json string request body equals for json response with status and message
     * by ignoring the array order and extra elements if present in json request body.
     */
    public static void postUrlRequestBodyEquals(String urlPath, String jsonStringRequestBody, int responseStatusCode, String responseStatusMessage){
        MappingBuilder stub = post(urlMatching(urlPath))
                .withRequestBody(equalToJson(jsonStringRequestBody, Constants.BOOLEAN_TRUE, Constants.BOOLEAN_FALSE))
                .willReturn(aResponse()
                        .withStatus(responseStatusCode)
                        .withStatusMessage(responseStatusMessage)
                ) ;
        stubs.add(stubFor(stub));
    }

    /**
     * @param urlPath
     * @param jsonStringRequestBody
     * @param responseStatusCode
     * @param jsonStringResponse
     * This static method stubs the url matching json string request body for json response with status.
     * by ignoring the array order and extra elements if present in json request body.
     */
    public static void postUrlRequestBodyMatchResponse(String urlPath, String jsonStringRequestBody, int responseStatusCode, String jsonStringResponse){
        MappingBuilder stub = post(urlMatching(urlPath))
                .withRequestBody(equalToJson(jsonStringRequestBody, Constants.BOOLEAN_TRUE, Constants.BOOLEAN_TRUE))
                .willReturn(aResponse()
                        .withStatus(responseStatusCode)
                        .withBody(jsonStringResponse)
                ) ;
        stubs.add(stubFor(stub));
    }

    /**
     * @param urlPath
     * @param jsonStringRequestBody
     * @param responseStatusCode
     * @param jsonStringResponse
     * This static method stubs the url matching json string request body equals for json response with status.
     * by ignoring the array order and extra elements if present in json request body.
     */
    public static void postUrlRequestBodyEqualsResponse(String urlPath, String jsonStringRequestBody, int responseStatusCode, String jsonStringResponse){
        MappingBuilder stub = post(urlMatching(urlPath))
                .withRequestBody(equalToJson(jsonStringRequestBody, Constants.BOOLEAN_TRUE, Constants.BOOLEAN_FALSE))
                .willReturn(aResponse()
                        .withStatus(responseStatusCode)
                        .withBody(jsonStringResponse)
                ) ;
        stubs.add(stubFor(stub));
    }

    /**
     * @param urlPath
     * @param jsonStringRequestBody
     * @param responseStatusCode
     * @param jsonStringResponse
     * This static method stubs the url matching json string request body for json response with status and message
     * by ignoring the array order and extra elements if present in json request body.
     */
    public static void postUrlRequestBodyMatchResponse(String urlPath, String jsonStringRequestBody, int responseStatusCode, String responseStatusMessage, String jsonStringResponse){
        MappingBuilder stub = post(urlMatching(urlPath))
                .withRequestBody(equalToJson(jsonStringRequestBody, Constants.BOOLEAN_TRUE, Constants.BOOLEAN_TRUE))
                .willReturn(aResponse()
                        .withStatus(responseStatusCode)
                        .withStatusMessage(responseStatusMessage)
                        .withBody(jsonStringResponse)
                ) ;
        stubs.add(stubFor(stub));
    }

    /**
     * @param urlPath
     * @param jsonStringRequestBody
     * @param responseStatusCode
     * @param jsonStringResponse
     * This static method stubs the url matching json string request body equals for json response with status and message
     * by ignoring the array order and extra elements if present in json request body.
     */
    public static void postUrlRequestBodyEqualsResponse(String urlPath, String jsonStringRequestBody, int responseStatusCode, String responseStatusMessage, String jsonStringResponse){
        MappingBuilder stub = post(urlMatching(urlPath))
                .withRequestBody(equalToJson(jsonStringRequestBody, Constants.BOOLEAN_TRUE, Constants.BOOLEAN_FALSE))
                .willReturn(aResponse()
                        .withStatus(responseStatusCode)
                        .withStatusMessage(responseStatusMessage)
                        .withBody(jsonStringResponse)
                ) ;
        stubs.add(stubFor(stub));
    }

    /**
     * @param urlPath
     * @param headerKey
     * @param headerValue
     * @param jsonStringRequestBody
     * @param responseStatusCode
     * @param responseStatusMessage
     * This static method stubs the url matching single header, json string request body for status
     * by ignoring the array order and extra elements if present in json request body.
     */
    public static void postUrlRequestHeaderBodyMatch(String urlPath, String headerKey, String headerValue, String jsonStringRequestBody, int responseStatusCode, String responseStatusMessage){
        MappingBuilder stub =  post(urlMatching(urlPath))
                .withHeader(headerKey, containing(headerValue))
                .withRequestBody(equalToJson(jsonStringRequestBody, Constants.BOOLEAN_TRUE, Constants.BOOLEAN_TRUE))
                .willReturn(aResponse()
                        .withStatus(responseStatusCode)
                        .withStatusMessage(responseStatusMessage)) ;
        stubs.add(stubFor(stub));
    }

    /**
     * @param urlPath
     * @param headerKey
     * @param headerValue
     * @param jsonStringRequestBody
     * @param responseStatusCode
     * @param responseStatusMessage
     * This static method stubs the url matching single header, json string request body for status
     * by ignoring the array order and extra elements if present in json request body.
     */
    public static void postUrlRequestHeaderBodyEquals(String urlPath, String headerKey, String headerValue, String jsonStringRequestBody, int responseStatusCode, String responseStatusMessage){
        MappingBuilder stub =  post(urlMatching(urlPath))
                .withHeader(headerKey, containing(headerValue))
                .withRequestBody(equalToJson(jsonStringRequestBody, Constants.BOOLEAN_TRUE, Constants.BOOLEAN_FALSE))
                .willReturn(aResponse()
                        .withStatus(responseStatusCode)
                        .withStatusMessage(responseStatusMessage)) ;
        stubs.add(stubFor(stub));
    }

    /**
     * @param urlPath
     * @param headerKey
     * @param headerValue
     * @param jsonStringRequestBody
     * @param responseStatusCode
     * @param responseStatusMessage
     * @param jsonStringResponse
     * This static method stubs the url matching single header, json string request body for json response with status
     * by ignoring the array order and extra elements if present in json request body.
     */
    public static void postUrlRequestHeaderBodyMatchResponse(String urlPath, String headerKey, String headerValue, String jsonStringRequestBody, int responseStatusCode, String responseStatusMessage, String jsonStringResponse){
        MappingBuilder stub =  post(urlMatching(urlPath))
                .withHeader(headerKey, containing(headerValue))
                .withRequestBody(equalToJson(jsonStringRequestBody, Constants.BOOLEAN_TRUE, Constants.BOOLEAN_TRUE))
                .willReturn(aResponse()
                        .withStatus(responseStatusCode)
                        .withStatusMessage(responseStatusMessage)
                        .withBody(jsonStringResponse)) ;
        stubs.add(stubFor(stub));
    }

    /**
     * @param urlPath
     * @param headerKey
     * @param headerValue
     * @param jsonStringRequestBody
     * @param responseStatusCode
     * @param responseStatusMessage
     * @param jsonStringResponse
     * This static method stubs the post url matching single header, json string request body for json response with status
     * by ignoring the array order and extra elements if present in json request body.
     */
    public static void postUrlRequestHeaderBodyEqualsResponse(String urlPath, String headerKey, String headerValue, String jsonStringRequestBody, int responseStatusCode, String responseStatusMessage, String jsonStringResponse){
        MappingBuilder stub =  post(urlMatching(urlPath))
                .withHeader(headerKey, containing(headerValue))
                .withRequestBody(equalToJson(jsonStringRequestBody, Constants.BOOLEAN_TRUE, Constants.BOOLEAN_FALSE))
                .willReturn(aResponse()
                        .withStatus(responseStatusCode)
                        .withStatusMessage(responseStatusMessage)
                        .withBody(jsonStringResponse)) ;
        stubs.add(stubFor(stub));
    }


    /**
     * @param urlPath
     * @param responseStatusCode
     * @param responseStatusMessage
     * This static method stubs the get urlpath for response status with message.
     */
    public static void getUrlRequestMatch(String urlPath, int responseStatusCode, String responseStatusMessage){
        MappingBuilder stub = get(urlEqualTo(urlPath))
                .atPriority(10)
                .willReturn(aResponse()
                        .withStatus(responseStatusCode)
                        .withStatusMessage(responseStatusMessage)) ;
        stubs.add(stubFor(stub));
    }

    /**
     * @param urlPath
     * @param responseStatusCode
     * @param responseStatusMessage
     * @param jsonStringResponse
     * This static method stubs the get urlpath for json string response with status and message.
     */
    public static void getUrlRequestMatchResponse(String urlPath, int responseStatusCode, String responseStatusMessage, String jsonStringResponse){
        MappingBuilder stub = get(urlEqualTo(urlPath))
                .willReturn(aResponse()
                        .withStatus(responseStatusCode)
                        .withStatusMessage(responseStatusMessage)
                        .withBody(jsonStringResponse)) ;
        stubs.add(stubFor(stub));
    }

    /**
     * @param urlPath
     * @param headerKey
     * @param headerValue
     * @param responseStatusCode
     * @param responseStatusMessage
     * This static method stubs the get urlpath by headers for response: status with message
     */
    public static void getUrlRequestHeaderMatch(String urlPath, String headerKey, String headerValue, int responseStatusCode, String responseStatusMessage){
        MappingBuilder stub = get(urlEqualTo(urlPath))
                .withHeader(headerKey, containing(headerValue))
                .willReturn(aResponse()
                        .withStatus(responseStatusCode)
                        .withStatusMessage(responseStatusMessage)) ;
        stubs.add(stubFor(stub));
    }

    /**
     * @param urlPath
     * @param headerKey
     * @param headerValue
     * @param responseStatusCode
     * @param responseStatusMessage
     * @param jsonStringResponse
     * This static method stubs the get urlpath by headers for json string response with status and message.
     */
    public static void getUrlRequestHeaderMatchResponse(String urlPath, String headerKey, String headerValue, int responseStatusCode, String responseStatusMessage, String jsonStringResponse){
        MappingBuilder stub = get(urlEqualTo(urlPath))
                .withHeader(headerKey, containing(headerValue))
                .willReturn(aResponse()
                        .withStatus(responseStatusCode)
                        .withStatusMessage(responseStatusMessage)
                        .withBody(jsonStringResponse)) ;
        stubs.add(stubFor(stub));
    }

    /**
     * @param urlPath
     * @param queryParam
     * @param queryParamValue
     * @param headerKey
     * @param headerValue
     * @param responseStatusCode
     * @param responseStatusMessage
     * This static method stubs the get urlpath by single header and single query parameter for response: status with message
     */
    public static void getUrlRequestHeaderParamMatch(String urlPath, String queryParam, String queryParamValue, String headerKey, String headerValue, int responseStatusCode, String responseStatusMessage){
        MappingBuilder stub =  get(urlPathMatching(urlPath))
                .withQueryParam(queryParam, equalTo(queryParamValue))
                .withHeader(headerKey, containing(headerValue))
                .willReturn(aResponse()
                        .withStatus(responseStatusCode)
                        .withStatusMessage(responseStatusMessage)) ;
        stubs.add(stubFor(stub));
    }

    /**
     * @param urlPath
     * @param queryParam
     * @param queryParamValue
     * @param headerKey
     * @param headerValue
     * @param responseStatusCode
     * @param responseStatusMessage
     * @param jsonStringResponse
     * This static method stubs the get urlpath by single header and single query parameter for json string response with status and message.
     */
    public static void getUrlRequestHeaderParamMatchResponse(String urlPath, String queryParam, String queryParamValue, String headerKey, String headerValue, int responseStatusCode, String responseStatusMessage, String jsonStringResponse){
        MappingBuilder stub =  get(urlPathMatching(urlPath))
                .withQueryParam(queryParam, equalTo(queryParamValue))
                .withHeader(headerKey, containing(headerValue))
                .willReturn(aResponse()
                        .withStatus(responseStatusCode)
                        .withStatusMessage(responseStatusMessage)
                        .withBody(jsonStringResponse)) ;
        stubs.add(stubFor(stub));
    }
}
