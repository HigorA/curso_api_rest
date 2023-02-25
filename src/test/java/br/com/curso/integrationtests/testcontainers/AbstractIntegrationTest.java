package br.com.curso.integrationtests.testcontainers;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.Container;

import org.testcontainers.containers.GenericContainer;
import org.testcontainers.lifecycle.Startable;
import org.testcontainers.lifecycle.Startables;

import java.util.Collection;
import java.util.stream.Stream;

@ContextConfiguration(inheritInitializers = AbstractIntegrationTest.Initializer.class);
public class AbstractIntegrationTest {

    public class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        static Container<?> mysql = new GenericContainer<>("mysql:8.0.32");

        private static void startContainers() {
            Startables.deepStart(Stream.of(mysql)).join();
        }

        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {
            startContainers();
            ConfigurableEnvironment environment = applicationContext.getEnvironment();

        }
    }
}
