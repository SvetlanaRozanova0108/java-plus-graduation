package main.java.api.feignClient.client.event;
import feign.FeignException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import main.java.api.dto.event.EventFullDto;
import main.java.api.dto.event.EventShortDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import ru.practicum.interaction.api.dto.event.*;
import main.java.api.enums.event.SortType;

import java.time.LocalDateTime;
import java.util.List;

import static main.java.api.utils.date.DateTimeFormat.TIME_PATTERN;

@FeignClient(name = "event-service", path = "/events")
public interface PublicEventClient {

    @GetMapping
    List<EventShortDto> getEventsByFilter(HttpServletRequest httpServletRequest,
                                          @RequestParam(name = "text", defaultValue = "") String text,
                                          @RequestParam(name = "categories", required = false) List<Long> categories,
                                          @RequestParam(name = "paid", required = false) Boolean paid,
                                          @RequestParam(required = false) @DateTimeFormat(pattern = TIME_PATTERN) LocalDateTime rangeStart,
                                          @RequestParam(required = false) @DateTimeFormat(pattern = TIME_PATTERN) LocalDateTime rangeEnd,
                                          @RequestParam(name = "onlyAvailable", defaultValue = "false") Boolean onlyAvailable,
                                          @RequestParam(name = "sort", defaultValue = "EVENT_DATE") SortType sort,
                                          @RequestParam(name = "from", defaultValue = "0") @PositiveOrZero Integer from,
                                          @RequestParam(name = "size", defaultValue = "10") @Positive Integer size) throws FeignException;

    @GetMapping("/{id}")
    EventFullDto getEventById(HttpServletRequest httpServletRequest, @PathVariable("id") @Positive Long id) throws FeignException;
}
