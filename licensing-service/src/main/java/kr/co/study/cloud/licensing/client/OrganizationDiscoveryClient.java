package kr.co.study.cloud.licensing.client;


import java.util.List;
import kr.co.study.cloud.common.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Component
public class OrganizationDiscoveryClient {

  public final DiscoveryClient discoveryClient;

  public Mono<String> organization() {

    List<ServiceInstance> instances = discoveryClient.getInstances(Constants.ORGANIZATION_SERVICE);

    ServiceInstance instance = instances.get(0);

    String url = instance.getUri().toString();

    String uri = "/organization";

    return WebClient.create(url)
        .get().uri(uri)
        .retrieve().bodyToMono(String.class);
  }

}
