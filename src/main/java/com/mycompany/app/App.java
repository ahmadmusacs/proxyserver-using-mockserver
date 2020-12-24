package com.mycompany.app;

/**
 * Hello world!
 *
 */

import static org.mockserver.integration.ClientAndServer.startClientAndServer;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.client.MockServerClient;

import static org.mockserver.model.Cookie.cookie;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;
// import static org.mockserver.model.OpenAPIDefinition.openAPI;
import static org.mockserver.model.Parameter.param;
import org.mockserver.logging.*;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;
import org.mockserver.matchers.TimeToLive;
import org.mockserver.matchers.Times;

public class App 
{
	private static ClientAndServer proxy;
	private static MockServerClient mockServerClient;

    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );

        proxy = ClientAndServer.startClientAndServer(8081);
        mockServerClient = new MockServerClient("127.0.0.1", 8081);
        mockServerClient
            .when(
                    HttpRequest.request()
                            .withMethod("GET")
                            .withPath("/api/hello"),
                    Times.unlimited(),
                    TimeToLive.unlimited())
            .respond(
                    HttpResponse.response()
                            .withBody("Hello Musa")
                            .withStatusCode(200)
            );
    }

}
