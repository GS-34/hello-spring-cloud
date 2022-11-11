package kr.co.study.cloud.licensing.service;

import kr.co.study.cloud.licensing.client.OrganizationDiscoveryClient;
import kr.co.study.cloud.licensing.client.OrganizationFeignClient;
import kr.co.study.cloud.licensing.client.OrganizationRestTemplateClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LicenseService {

  private final OrganizationDiscoveryClient discoveryClient;

  private final OrganizationRestTemplateClient restTemplateClient;

  private final OrganizationFeignClient feignClient;

  public String getOrganization() {

    StringBuilder sb = new StringBuilder();
    sb.append("from discovery : ")
        .append(discoveryClient.organization().block())
        .append("\n")
        .append("from restTemplate : ")
        .append(restTemplateClient.organization())
        .append("\n")
        .append("from feign : ")
        .append(feignClient.organization());

    return sb.toString();
  }

}
