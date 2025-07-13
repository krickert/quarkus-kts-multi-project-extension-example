package io.quarkus.it.example;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import org.acme.example.extension.runtime.QuarkusExampleExtensionConfig;
import org.acme.liba.LibA;
import org.acme.libb.LibB;

@Path("/extension-test")
public class ExtensionTestResource {

    @Inject
    LibA libA;
    
    @Inject
    LibB libB;
    
    @Inject
    QuarkusExampleExtensionConfig config;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String testExtensions() {
        return String.format("LibA: %s, LibB: %s, Extension enabled: %s", 
            libA.getName(), 
            libB.getName(), 
            config.enabled);
    }
    
    @GET
    @Path("/libs")
    @Produces(MediaType.TEXT_PLAIN)
    public String testLibraries() {
        // In the actual example, LibB doesn't use LibA, but we can demonstrate both work
        return libB.getName() + " uses " + libA.getName();
    }
}