package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.configuration.ServiceProperties;
import org.example.dto.RequestDto;
import org.example.dto.ResponseDto;
import org.example.entity.UserLimit;
import org.example.repository.LimitRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class LimitService {

  private final LimitRepository limitRepository;
  private final ServiceProperties serviceProperties;

  public ResponseDto requestChange(RequestDto requestDto) {
    BigDecimal amountDto = requestDto.amount();
    int compareToZero = amountDto.compareTo(BigDecimal.ZERO);

    if (compareToZero == 0) {
      throw new IllegalArgumentException("Amount cannot be " + amountDto);
    }

    UserLimit userLimit = limitRepository.findByUserId(requestDto.userId())
            .orElse( new UserLimit(0, requestDto.userId(), serviceProperties.limits().amount()));

    BigDecimal limitAmount = userLimit.getAmount();
    if (limitAmount.compareTo(amountDto) < 0) {
      throw new IllegalArgumentException("UserId: " + userLimit.getUserId() + " UserLimit is not enough: " + limitAmount);
    }

    userLimit.setAmount(limitAmount.subtract(amountDto));
    limitRepository.save(userLimit);

    return new ResponseDto(ResultCode.SUCCESS, "UserLimit " + (compareToZero > 0 ? "decreased" : "increased")
            + " successfully. UserId: " + userLimit.getUserId() + " has new userLimit: " + userLimit.getAmount());
  }

  @Scheduled(cron = "${service.limits.reset-time}")
  @Transactional
  public void resetLimits() {
    BigDecimal amount = serviceProperties.limits().amount();
    limitRepository.resetAllAmount(amount);
  }
}
