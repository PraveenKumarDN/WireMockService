# Mocking of rest services using wiremock simulator.
Steps to handle the wiremock service for mocking/unmocking the required apis in automation suite as part of regression tests execution:
The life cycle of stubbing(mocking) the wiremock services via automation regression suite involves below processes:
a. Start the wiremock server.
b. Stub the required services.
c. Use the stubbed services in regression tests.
d. Unstub the services.
e. Stop the wiremock server.

Prerequisites:
a. Download and install apache tomcat.
b. Run the Tomcat.
c. Download the wiremock jar.
d. Deploy the wiremock service on tomcat.

Note:
Above prerequisites step is already been done on machine: 10.251.32.153 port 8080.
Also Stubbing here in context means 'mocking the service'.

For more thearetical info. please refer the blog: https://kkpravee023tech.blogspot.com/2019/01/http-services-mocking-with-wiremock.html 



Implementation steps:
Implementation for Initialisation(Configuration/Start/Stub methods) and deinitialisation(Unstub/Stop) of wiremock services could be handled in generic framework. Just you need to extend the same in your project stubbing class.

Changes to be done in the maven project:

Step1:

Include below dependency in pom.xml:

<dependency>
   <groupId>com.github.tomakehurst</groupId>
   <artifactId>wiremock</artifactId>
   <version>2.16.0</version>
</dependency>

Step2:
Include the mockIp and mockPort config properties in env.properties file of project classpath.
i.e.
src/test/resources/config/env.properties
mockIp = xx.xxx.xx.xxx
mockPort = yyyy



Step3:
In BeforeSuite method, include initMockServices() as shown below:
@BeforeSuite(alwaysRun = true)
public void beforeSuite() throws Exception {
      loadEnvironmentData();
      testDataInitialization();
      clientDataInitialization();
      initMockServices();
}



Step4:
Create new mocking class(say MockTestServices.java) in util package of your project. Here you are going to stub the services.
Please find below template for same. Hope this will help you in ease of understanding, stubbing and handling project.


public class MockTestServices extends MockServices {
public static void initMockServices(){

          //Initialise the mock server(Handled in QE-Framework)
          initWireMockServer();

         //Call this method where apis to be stubbed(Handle this in your project local class as below)
          stubServices();
  }



public static void stubServices(){

        //Just call these suitable static methods with parameterised api details to be mocked.
        postUrlRequestMatch(urlPath, responseStatusCode, jsonStringResponse);
        postUrlRequestBodyMatch(urlPath, requestBody, responseStatusCode, jsonStringResponse);
        postUrlRequestHeaderBodyMatch(urlPath, headers, requestBody, responseStatusCode, jsonStringResponse);

        getUrlRequestMatch(urlPath, responseStatusCode, jsonStringResponse);
  }



public static void deInitMockServices(){

       //removes the stubs and stop the wiremock server(Handle in generic framework)
       unMockServicesAndStop();
  }
}



Step5:
In AfterSuite method, include deInitMockServices() as shown below:
@AfterSuite(alwaysRun = true)
public void afterSuite() throws Exception {
       closeDBConnections();
       deInitMockServices();
}



                                    Illustration of 'How to stub the service':
Lets illustrate the above stubServices method by considering one rest end point service that should be mocked.
Required api service details to be mocked:
Http Method: POST
Http Uri: http://xx.xxx.xx.xxx:yyyy/v1/wiremock/test/resource
Http Headers: 
Content-Type: application/json
Http RequestBody:
{
"key1": "value1",
"key2": "value2",
"name": "Praveen Kumar",
"key3": "ASDFQWERT"
}
Http Response:
Response with status "200 OK":
{
"responseId": "12345678",
"result": "success"
}

Java version of stubbing above required service:
public static void stubServices(){

     //Construct the above api details
      String urlPath: "/v1/wiremock/test/resource" ;
      String headerKey = "Content-Type" ;
      String headerValue = "application/json" ;
      String requestBodyEquals = "{\"key1\":\"value1\",\"key2\":\"value2\",\"name\":\"Praveen Kumar\",\"key3\":\"ASDFQWERT\"}";
      String requestBodyContains = "{\"key1\":\"value1\",\"key2\":\"value2\"}";
      int responseStatusCode = 200 ;
      String responseStatusMessage = "OK" ;
      String responseBody: "{\"responseId\":\"12345678\",\"result\":\"success\"}" ;           

      //Call the suitable static methods as per api service requirement as below which will mock the service.

      postUrlRequestHeaderBodyEqualsResponse(urlPath, headerKey, headerValue, requestBodyEquals, responseStatusCode, responseStatusMessage, responseBody) ;
      postUrlRequestHeaderBodyMatchResponse(urlPath, headerKey, headerValue, requestBodyContains, responseStatusCode, responseStatusMessage, responseBody) ;
}



Note:

All the stub mappings could be found here: http://xx.xxx.xx.xxx:yyyy/__admin/mappings  



Dynamic interpolation of request-response transformation mocking implementations:
Wiremock Body Transformer is a Wiremock extension that can take the request body and interpolates the variable into the response. Built on the extensions platform of Wiremock, it allows your wiremock response to be dynamic and dependent on the request for a smarter testing process.

Please find below steps:

Step1:

Run the wiremock service in the classpath as below
java -cp "wiremock-body-transformer-1.1.6.jar:wiremock-standalone-2.20.0.jar" com.github.tomakehurst.wiremock.standalone.WireMockServerRunner --verbose --extensions com.opentable.extension.BodyTransformer

Step2:

Include the wiremock body transformation dependency in pom.xml:
<dependency>
<groupId>com.opentable</groupId>
<artifactId>wiremock-body-transformer</artifactId>
<version>1.1.6</version>
</dependency>

Step3:

Instantiate the Wiremock server with the BodyTransformer instance:
WireMockConfiguration.wireMockConfig().notifier(notifier).extensions(new com.kkpravee.Transformer.BodyTransformer());

Now from above setup of wiremock and BobyTransformer, we are good to handle the dynamic request-response stubs. All the required classes and methods should be handled in Generic framework.


Illustration of ResponseBodyTransformation cases:
Case1: The same requested input value would be returned in the response upon execution of api
public static void stubDynamicRequestResponseServices(){
//Construct the above api details
String urlPath: "/v1/wiremock/test/resource" ;
String headerKey = "Content-Type" ;
String headerValue = "application/json" ;
String requestBody = "{\"key1\":\"value1\",\"key2\":\"value2\",\"name\":\"Praveen Kumar\",\"key3\":\"value3\"}";
int responseStatusCode = 200 ;
String responseStatusMessage = "OK" ;
String responseBody: "{\"key1\":\"$(key1)\",\"result\":\"success\"}" ;

//Call the suitable static methods as per api service requirement as below which will mock the service.
postUrlHeaderRequestResponseBodyEqualsTransform(urlPath, headerKey, headerValue, jsonStringRequestBody, responseStatusCode, responseStatusMessage, jsonStringResponse) ;
}

Case2: Dynamically random integer value would be returned in the response upon execution of api:
public static void stubDynamicRequestResponseRandomIntGen(){
//Construct the above api details
String urlPath: "/v1/wiremock/test/resource" ;
String headerKey = "Content-Type" ;
String headerValue = "application/json" ;
String requestBody = "{\"key1\":\"value1\",\"key2\":\"value2\",\"name\":\"Praveen Kumar\",\"key3\":\"value3\"}";
int responseStatusCode = 200 ;
String responseStatusMessage = "OK" ;
String responseBody: "{\"dynamicRandomNumber\":$(!RandomInteger),\"responseid\":\"1234\",\"responseTrueOrFalse\":true}" ;

//Call the suitable static methods as per api service requirement as below which will mock the service.
postUrlHeaderRequestResponseBodyEqualsTransform(urlPath, headerKey, headerValue, jsonStringRequestBody, responseStatusCode, responseStatusMessage, jsonStringResponse) ;
}

Also have implemented and added the PUT, DELETE http method services.

   Thus we could now be able to mock GET, POST, PUT, DELETE https services with dynamic interpolation of request response transformations in java automation projects.
