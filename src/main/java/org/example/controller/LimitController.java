package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.RequestDto;
import org.example.dto.ResponseDto;
import org.example.service.LimitService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/limit")
@RequiredArgsConstructor
public class LimitController {

  private final LimitService limitService;

  @PostMapping(value = "/change")
  public ResponseDto requestChange(@RequestBody RequestDto requestDto) {
    return limitService.requestChange(requestDto);
 }
}
