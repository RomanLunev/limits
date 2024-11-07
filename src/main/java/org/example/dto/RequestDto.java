package org.example.dto;

import java.math.BigDecimal;

public record RequestDto(long userId, BigDecimal amount) {}
