package org.example.configuration;

import lombok.RequiredArgsConstructor;
import org.example.repository.LimitRepository;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Configuration
@EnableConfigurationProperties(ServiceProperties.class)
@EnableScheduling
@RequiredArgsConstructor
public class SpringConfiguration {
  private final LimitRepository limitRepository;
  private final ServiceProperties serviceProperties;

  @Scheduled(cron = "${service.limits.reset-time}")
  @Transactional
  public void reserLimits() {
    BigDecimal amount = serviceProperties.limits().amount();
    limitRepository.resetAllAmount(amount);
  }
}
