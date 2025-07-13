package org.acme.quarkus.sample;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.TestProfile;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

@QuarkusTest
@TestProfile(ExtensionConfigTest.EnabledProfile.class)
public class ExtensionConfigTest {

    @Test
    public void testExtensionEnabledConfig() {
        given()
          .when().get("/hello")
          .then()
             .statusCode(200)
             .body(containsString("extension enabled: true"));
    }

    public static class EnabledProfile implements io.quarkus.test.junit.QuarkusTestProfile {
        @Override
        public Map<String, String> getConfigOverrides() {
            return Map.of("quarkus.example.extension.enabled", "true");
        }
    }
}