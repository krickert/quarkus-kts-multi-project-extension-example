package org.acme.example.extension.deployment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import jakarta.inject.Inject;
import jakarta.enterprise.context.ApplicationScoped;

import org.acme.example.extension.runtime.QuarkusExampleExtensionConfig;
import org.acme.liba.LibA;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import io.quarkus.test.QuarkusUnitTest;

public class ExampleExtensionTest {

    @RegisterExtension
    static final QuarkusUnitTest unitTest = new QuarkusUnitTest()
            .setArchiveProducer(() -> ShrinkWrap.create(JavaArchive.class)
                    .addClass(TestBean.class))
            .withConfigurationResource("application.properties");

    @Inject
    TestBean testBean;

    @Inject
    QuarkusExampleExtensionConfig config;

    @Test
    public void testExtensionConfigInjection() {
        assertEquals(false, config.enabled);
    }

    @Test
    public void testLibABeanIsRegistered() {
        assertTrue(testBean.getLibAName().contains("LibA"));
    }

    @ApplicationScoped
    public static class TestBean {
        @Inject
        LibA libA;

        public String getLibAName() {
            return libA.getName();
        }
    }
}