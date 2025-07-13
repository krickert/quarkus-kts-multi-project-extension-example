package org.acme.example.extension.deployment;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.containsString;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

import org.acme.example.extension.runtime.QuarkusExampleExtensionConfig;
import org.acme.liba.LibA;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import io.quarkus.test.QuarkusUnitTest;
import io.restassured.RestAssured;

/**
 * Integration test that verifies the extension works with REST endpoints
 */
public class ExampleExtensionRestTest {

    @RegisterExtension
    static final QuarkusUnitTest config = new QuarkusUnitTest()
            .setArchiveProducer(() -> ShrinkWrap.create(JavaArchive.class)
                    .addClasses(TestResource.class))
            .withConfigurationResource("rest-test-application.properties");

    @Test
    public void testExtensionWithRestEndpoint() {
        RestAssured.when().get("/test")
                .then()
                .statusCode(200)
                .body(containsString("Hello from LibA"));
    }

    @Test
    public void testExtensionConfigEndpoint() {
        RestAssured.when().get("/test/config")
                .then()
                .statusCode(200)
                .body(is("Extension is enabled: true"));
    }

    @Path("/test")
    public static class TestResource {
        
        @Inject
        LibA libA;
        
        @Inject
        QuarkusExampleExtensionConfig config;
        
        @GET
        public String hello() {
            return "Hello from " + libA.getName();
        }
        
        @GET
        @Path("/config")
        public String config() {
            return "Extension is enabled: " + config.enabled;
        }
    }
}