package org.example.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableConfigurationProperties(ServiceProperties.class)
@EnableScheduling
@RequiredArgsConstructor
public class SpringConfiguration {
}
