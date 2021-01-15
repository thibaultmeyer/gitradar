package com.github.gitradar;

import io.micronaut.runtime.Micronaut;

/**
 * Main class.
 */
public class Application {

    /**
     * Application entry point.
     *
     * @param args application arguments
     */
    public static void main(final String[] args) {
        // Configure JVM
        System.setProperty("java.awt.headless", "true");
        System.setProperty("user.timezone", "UTC");
        System.setProperty("file.encoding", "UTF-8");
        System.setProperty("java.util.PropertyResourceBundle.encoding", "UTF-8");

        // H2 WebConsole (port = 8082)
        // To use, change to h2 to 'compile' in pom.xml
        //Server.createWebServer().start();

        // Micronaut
        Micronaut.build(args)
            .packages("gitradar")
            .eagerInitSingletons(true)
            .mainClass(Application.class)
            .start();
    }
}
