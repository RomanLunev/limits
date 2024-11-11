package org.example.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "service")
public record ServiceProperties(ServiceLimitProperties limits) {}
