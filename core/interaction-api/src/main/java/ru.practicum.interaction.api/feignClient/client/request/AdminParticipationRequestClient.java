package ru.practicum.interaction.api.feignClient.client.request;
import feign.FeignException;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import ru.practicum.interaction.api.enums.request.Status;
import ru.practicum.interaction.api.dto.request.ParticipationRequestDto;

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

    @PutMapping("/status/{id}/{status}")
    void setStatusRequest(@PathVariable Long id, @PathVariable Status status)  throws FeignException;
}
