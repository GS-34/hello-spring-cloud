package kr.co.study.cloud.licensing.controller;

import kr.co.study.cloud.licensing.service.LicenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/license")
public class LicenseController {

  private final LicenseService service;

  @GetMapping
  public String get() {

    String license = "foo license \n";

    String organization = service.getOrganization();

    return license + organization;
  }

}
