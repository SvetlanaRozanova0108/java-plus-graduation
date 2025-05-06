package main.java.api.feignClient.client.request;
import feign.FeignException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import main.java.api.dto.request.ParticipationRequestDto;
import main.java.api.enums.request.Status;

import java.util.List;
import java.util.Map;

@FeignClient(name = "request-service", path = "/requests")
public interface AdminParticipationRequestClient {

    @GetMapping("/event/{eventId}")
    List<ParticipationRequestDto> findAllByEventId(@PathVariable Long eventId) throws FeignException;

    @GetMapping("/{ids}")
    List<ParticipationRequestDto> findAllByIds(@PathVariable List<Long> ids) throws FeignException;

    @GetMapping("/event/confirmed/{eventId}")
    Map<Long, List<ParticipationRequestDto>> findAllConfirmedByEventId(@PathVariable List<Long> eventId) throws FeignException;

    @PatchMapping("/status/{id}")
    void setStatusRequest(@PathVariable Long id, Status status)  throws FeignException;
}
