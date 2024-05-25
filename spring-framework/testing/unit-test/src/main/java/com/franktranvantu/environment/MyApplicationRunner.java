package com.franktranvantu.environment;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.env.StandardEnvironment;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class MyApplicationRunner implements ApplicationRunner {
    private final Environment environment;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (environment instanceof StandardEnvironment standardEnvironment) {
            for (var propertySource : standardEnvironment.getPropertySources()) {
                log.info("PropertySource: {}", propertySource.getName());
                if (propertySource instanceof EnumerablePropertySource enumerablePropertySource) {
                    for (String propertyName : enumerablePropertySource.getPropertyNames()) {
                        log.info("{} = {}", propertyName, environment.getProperty(propertyName));
                    }
                } else {
                    log.warn("Property source [{}] is not enumerable and cannot be iterated.", propertySource.getName());
                }
            }
        }
    }

    public void getConfigurationPropertySourcesPropertySource() {

    }

    public void getSystemPropertiesPropertySource() {

    }

    public void getSystemEnvironmentPropertySource() {

    }

    public void getRandomPropertySource() {

    }

    public void getCustomConfigPropertySource() {

    }
}
