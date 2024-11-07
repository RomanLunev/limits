package org.example.dto;

import org.example.service.ResultCode;

public record ResponseDto (ResultCode resultCode, String resultMessage) {}
