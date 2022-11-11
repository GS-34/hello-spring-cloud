package kr.co.study.cloud.organization.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/organization")
public class OrganizationController {

  @GetMapping
  public String get() {
    String response = "bar organization";

    return response;
  }

}
