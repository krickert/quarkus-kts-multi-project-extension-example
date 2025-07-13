package io.quarkus.it.example;

import io.quarkus.test.junit.QuarkusIntegrationTest;

/**
 * Native integration test - runs the same tests but against the packaged application
 */
@QuarkusIntegrationTest
public class ExtensionIntegrationIT extends ExtensionIntegrationTest {
    // Execute the same tests but in native mode
}