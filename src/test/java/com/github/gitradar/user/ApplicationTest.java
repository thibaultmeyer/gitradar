package com.github.gitradar.user;

import io.micronaut.runtime.EmbeddedApplication;
import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

@MicronautTest
public class ApplicationTest {

    @Inject
    EmbeddedApplication application;

    @Test
    void test_isRunning() {
        Assertions.assertTrue(application.isRunning());
    }

    @Test
    void test_isServer() {
        Assertions.assertTrue(application.isServer());
    }
}
