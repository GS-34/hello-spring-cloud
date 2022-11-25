package kr.co.study.cloud.licensing.service;

import static io.github.resilience4j.bulkhead.annotation.Bulkhead.Type.SEMAPHORE;
import static io.github.resilience4j.bulkhead.annotation.Bulkhead.Type.THREADPOOL;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import java.util.concurrent.CompletableFuture;
import kr.co.study.cloud.licensing.client.OrganizationDiscoveryClient;
import kr.co.study.cloud.licensing.client.OrganizationFeignClient;
import kr.co.study.cloud.licensing.client.OrganizationRestTemplateClient;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class LicenseService {

  private final OrganizationDiscoveryClient discoveryClient;

  private final OrganizationRestTemplateClient restTemplateClient;

  private final OrganizationFeignClient feignClient;

  public String discovery() {

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

  @SneakyThrows
  @CircuitBreaker(name = "circuitLicenseService")
  public String circuit() {

    Thread.sleep(1100);
    if (true) {
//      throw new TimeoutException();
    }

    return "CircuitBreak~~~~~~~";
  }


  @Retry(name = "retryLicenseService", fallbackMethod = "fallback")
  public String retry() {

    log.info("retry~!");

    if (true) {
      throw new RuntimeException();
    }

    return "retry~~~~~";
  }

  @RateLimiter(name = "limiterLicenseService", fallbackMethod = "fallback")
  public String limiter() {

    log.info("rate Limiter~!");

    return "rate Limiter~~~";
  }

  private String fallback(Throwable t) {
    return "fallback~~!";
  }

  @Bulkhead(name = "bulkheadSemaphore", fallbackMethod = "fallback", type = SEMAPHORE)
  public String bulkheadSemaphore() {

    log.info("bulk head semaphore");

    return "BulkheadSemaphore~~~~~~";
  }

  @Bulkhead(name = "bulkheadThreadPool", fallbackMethod = "bulkheadFallback", type = THREADPOOL)
  public CompletableFuture<String> bulkheadThreadPool() {

    log.info("bulk head thread pool");

    return CompletableFuture.completedFuture("BulkheadThreadPool~~~~~~");
  }

  private CompletableFuture<String> bulkheadFallback(Throwable t) {
    log.error("fallback : ", t);
    return CompletableFuture.completedFuture("Bulkhead fallback~~!");
  }


}
