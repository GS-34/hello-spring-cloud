package kr.co.study.cloud.api.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

  @Value("${test.str}")
  private String str;

  @GetMapping("/test")
  public String test(){
    return str;
  }

}
