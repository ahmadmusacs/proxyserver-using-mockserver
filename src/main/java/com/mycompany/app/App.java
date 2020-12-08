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
import static org.mockserver.model.OpenAPIDefinition.openAPI;
import static org.mockserver.model.Parameter.param;


public class App 
{
	private static ClientAndServer proxy;
	private static ClientAndServer mockServer;

    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );

        //mockServer = ClientAndServer.startClientAndServer(1080);
    }

    public static void startProxy() {
        proxy = ClientAndServer.startClientAndServer();
        System.setProperty("http.proxyHost", "127.0.0.1");
        System.setProperty("http.proxyPort", String.valueOf(proxy.getPort()));
        mockServer = startClientAndServer();
        System.setProperty("myService.port", String.valueOf(mockServer.getPort()));
    }

    public static void stopProxy() {
        stopQuietly(mockServer);
        stopQuietly(proxy);
        System.clearProperty("http.proxyHost");
        System.clearProperty("http.proxyPort");
    }

    public void createExpectationMockServerClient() {
        new MockServerClient("localhost", 1080)
            .when(
                request()
                    .withMethod("GET")
                    .withPath("/view/cart")
                    .withCookies(
                        cookie("session", "4930456C-C718-476F-971F-CB8E047AB349")
                    )
                    .withQueryStringParameters(
                        param("cartId", "055CA455-1DF7-45BB-8535-4F83E7266092")
                    )
            )
            .respond(
                response()
                    .withBody("some_response_body")
            );
    }


}
