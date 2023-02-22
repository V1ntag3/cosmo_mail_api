package br.com.email_jax_ws;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;

/**
 * Main class.
 *
 */
public class Main {
    // Base URI the Grizzly HTTP server will listen on
    public static final String BASE_URI = "http://localhost:8080/";

    /**
     * Starts Grizzly HTTP server exposing JAX-RS resources defined in this
     * application.
     * 
     * @return Grizzly HTTP server.
     */
    public static HttpServer startServer() {
        // create a resource config that scans for JAX-RS resources and providers
        // in br.com.email_jax_ws package
        final ResourceConfig rc = new ResourceConfig().packages("br.com.email_jax_ws");

        // create and start a new instance of grizzly http server
        // exposing the Jersey application at BASE_URI
        final String HEADERS = "Origin, Content-Type, Accept, Authorization";
        final String ALLOW_ORIGIN = "Access-Control-Allow-Origin";
        final String ALLOW_HEADERS = "Access-Control-Allow-Headers";
        final String ALLOW_METHODS = "Access-Control-Allow-Methods";
        
        rc.register(new ContainerResponseFilter() {
            @Override
            public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
                responseContext.getHeaders().add(ALLOW_ORIGIN, "*");
                responseContext.getHeaders().add(ALLOW_HEADERS, HEADERS);
                responseContext.getHeaders().add(ALLOW_METHODS, "GET, POST, PUT, DELETE, OPTIONS, HEAD");
            }
        });
        rc.property("jersey.config.server.wadl.disableWadl", true);
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    /**
     * Main method.
     * 
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        
 
        final HttpServer server = startServer();
        System.out.println(String.format("Jersey app started with endpoints available at "
                + "%s%nHit Ctrl-C to stop it...", BASE_URI));
        System.in.read();
        server.shutdownNow();
    }
}
