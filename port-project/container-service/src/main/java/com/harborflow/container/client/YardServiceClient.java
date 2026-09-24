package com.harborflow.container.client;

import com.harborflow.container.dto.AllocateRequest;
import com.harborflow.container.dto.YardSlotResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Feign client resolved via Eureka's logical service name "yard-service".
 * Spring Cloud LoadBalancer automatically load-balances across any
 * registered instances of yard-service.
 */
@FeignClient(name = "yard-service")
public interface YardServiceClient {

    @PostMapping("/api/yard/allocate")
    YardSlotResponse allocateSlot(@RequestBody AllocateRequest request);
}
