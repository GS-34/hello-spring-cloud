package kr.co.study.cloud.licensing.client;

import static kr.co.study.cloud.common.Constants.ORGANIZATION_SERVICE;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(ORGANIZATION_SERVICE)
public interface OrganizationFeignClient {

  @GetMapping("/organization")
  String organization();
}
