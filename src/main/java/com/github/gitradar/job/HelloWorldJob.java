package com.github.gitradar.job;

import io.micronaut.context.annotation.Property;
import io.micronaut.core.util.StringUtils;
import io.micronaut.scheduling.annotation.Scheduled;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;

/**
 * Hello World Job.
 */
@Singleton
public class HelloWorldJob {

    private static final Logger LOG = LoggerFactory.getLogger(HelloWorldJob.class);

    @Property(name = "gitradar.working_directory")
    private String value;

    @Scheduled(fixedDelay = "60s", initialDelay = "5s")
    public void process() {
        if (StringUtils.isEmpty(value)) {
            value = System.getProperty("java.io.tmpdir");
        }

        LOG.info("Hello, your working directory is {}", value);
    }
}
