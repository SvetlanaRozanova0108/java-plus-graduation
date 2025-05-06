package ru.practicum.interaction.api.feignClient.client.event;
import feign.FeignException;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import main.java.api.dto.event.EventFullDto;
import main.java.api.dto.event.UpdateEventAdminRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import main.java.api.UpdateObject;
import main.java.api.enums.event.State;

import java.time.LocalDateTime;
import java.util.List;

import static main.java.api.utils.date.DateTimeFormat.TIME_PATTERN;

@FeignClient(name = "event-service", path = "/admin/events")
public interface AdminEventClient {

    @GetMapping
    List<EventFullDto> getEventsForAdmin(@RequestParam(required = false) List<Long> users,
                                         @RequestParam(required = false) List<State> states,
                                         @RequestParam(required = false) List<Long> categories,
                                         @RequestParam(required = false) @DateTimeFormat(pattern = TIME_PATTERN) LocalDateTime rangeStart,
                                         @RequestParam(required = false) @DateTimeFormat(pattern = TIME_PATTERN) LocalDateTime rangeEnd,
                                         @PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
                                         @Positive @RequestParam(defaultValue = "10") Integer size) throws FeignException;

    @PatchMapping("/{eventId}")
    EventFullDto updateEvent(@PositiveOrZero @PathVariable Long eventId,
                             @Validated(UpdateObject.class) @RequestBody UpdateEventAdminRequest updateEventAdminRequest) throws FeignException;

    @GetMapping("/{id}")
    EventFullDto findById(@PathVariable("id") @Positive Long id) throws FeignException;

    @PatchMapping("/request/{eventId}")
    EventFullDto setConfirmedRequests(Long eventId, Integer count) throws FeignException;
}
