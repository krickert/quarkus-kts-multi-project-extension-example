package org.acme.quarkus.sample;

import io.quarkus.test.junit.QuarkusIntegrationTest;

@QuarkusIntegrationTest
public class HelloResourceIT extends HelloResourceTest {
    // Execute the same tests but in packaged mode (not dev mode)
}