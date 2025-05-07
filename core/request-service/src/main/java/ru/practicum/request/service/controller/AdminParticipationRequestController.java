package ru.practicum.request.service.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.interaction.api.enums.request.Status;
import ru.practicum.interaction.api.dto.request.ParticipationRequestDto;
import ru.practicum.request.service.service.ParticipationRequestService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/requests")
@Slf4j
@RequiredArgsConstructor
public class AdminParticipationRequestController {

    private final ParticipationRequestService requestService;

    @GetMapping("/event/{eventId}")
    public List<ParticipationRequestDto> findAllByEventId(@PathVariable Long eventId) {
        var result = requestService.findAllByEventId(eventId);
        return result;
    }

    @GetMapping("/event/confirmed/{eventId}")
    public Map<Long, List<ParticipationRequestDto>> findAllConfirmedByEventId(@PathVariable List<Long> eventId) {
        var result = requestService.findAllConfirmedByEventId(eventId);
        return result;
    }

    @GetMapping("/{ids}")
    public List<ParticipationRequestDto> findAllByIds(@PathVariable List<Long> ids) {
        var result = requestService.findAllByIds(ids);
        return result;
    }

    @PatchMapping("/status/{id}")
    public void setStatusRequest(@PathVariable Long id, Status status) {
        requestService.setStatusRequest(id, status);
    }
}
