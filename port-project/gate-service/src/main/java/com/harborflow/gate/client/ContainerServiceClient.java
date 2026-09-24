package com.harborflow.gate.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/** Resolved via Eureka's logical service name "container-service". */
@FeignClient(name = "container-service")
public interface ContainerServiceClient {

    @PostMapping("/api/containers/checkin/{containerCode}")
    Object checkIn(@PathVariable("containerCode") String containerCode);
}
