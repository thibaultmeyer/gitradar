package com.github.gitradar;


import io.micronaut.context.MessageSource;
import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;
import io.micronaut.context.i18n.ResourceBundleMessageSource;

/**
 * Application configuration.
 */
@Factory
class AppConfig {

    @Bean
    MessageSource messageSource() {
        return new ResourceBundleMessageSource("i18n/messages");
    }
}
