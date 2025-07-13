package io.quarkus.it.example;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.containsString;

/**
 * Integration test for the example extensions
 */
@QuarkusTest
public class ExtensionIntegrationTest {

    @Test
    public void testExtensionsWorkTogether() {
        given()
            .when().get("/extension-test")
            .then()
                .statusCode(200)
                .body(is("LibA: LibA, LibB: LibB, Extension enabled: true"));
    }

    @Test
    public void testLibrariesIntegration() {
        given()
            .when().get("/extension-test/libs")
            .then()
                .statusCode(200)
                .body(is("LibB uses LibA"));
    }
}