package org.example.configuration;

import lombok.RequiredArgsConstructor;
import org.example.repository.LimitRepository;
import org.example.service.LimitService;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableConfigurationProperties(ServiceProperties.class)
@EnableScheduling
@RequiredArgsConstructor
public class SpringConfiguration {
  private final LimitRepository limitRepository;
  private final ServiceProperties serviceProperties;
  private final LimitService limitService;

  @Scheduled(cron = "${service.limits.reset-time}")
  public void resetLimits() {
    limitService.resetLimits();
  }
}
