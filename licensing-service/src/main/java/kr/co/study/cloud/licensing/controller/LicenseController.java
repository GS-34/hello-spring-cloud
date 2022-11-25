package kr.co.study.cloud.licensing.controller;

import java.util.concurrent.CompletableFuture;
import kr.co.study.cloud.licensing.service.LicenseService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/license")
public class LicenseController {

  private final LicenseService service;

  @GetMapping("/discovery")
  public String discovery() {

    String license = "foo license \n";

    String organization = service.discovery();

    return license + organization;
  }

  @GetMapping("/circuit")
  public String circuit() {
    return service.circuit();
  }

  @SneakyThrows
  @GetMapping("/bulkhead/semaphore")
  public String bulkheadSemaphore() {
    return service.bulkheadSemaphore();
  }

  @SneakyThrows
  @GetMapping("/bulkhead/pool")
  public String bulkheadThreadPool() {
    CompletableFuture<String> future = service.bulkheadThreadPool();

    return future.get();
  }


  @GetMapping("/retry")
  public String retry() {
    return service.retry();
  }

  @GetMapping("/limiter")
  public String limiter() {
    return service.limiter();
  }


}
