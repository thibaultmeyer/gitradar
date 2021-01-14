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
        //Server.createWebServer().start();  // To use, change to h2 to 'compile' in pom.xml

        Micronaut.build(args)
            .packages("gitradar")
            .eagerInitSingletons(true)
            .mainClass(Application.class)
            .start();
    }
}
