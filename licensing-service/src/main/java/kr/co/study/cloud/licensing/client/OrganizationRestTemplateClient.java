package kr.co.study.cloud.licensing.client;

import static kr.co.study.cloud.common.Constants.ORGANIZATION_SERVICE;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Component
public class OrganizationRestTemplateClient {

  private final RestTemplate restTemplate;

  public String organization() {

    ResponseEntity<String> restExchange =
        restTemplate.exchange(
            String.format("http://%s/organization", ORGANIZATION_SERVICE),
            HttpMethod.GET,
            null, String.class);

    return restExchange.getBody();
  }

}
