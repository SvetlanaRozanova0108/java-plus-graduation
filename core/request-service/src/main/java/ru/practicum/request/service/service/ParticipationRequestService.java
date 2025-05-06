package ru.practicum.request.service.service;

import main.java.api.dto.request.ParticipationRequestDto;
import main.java.api.enums.request.Status;
import ru.practicum.request.service.model.ParticipationRequest;

import java.util.List;
import java.util.Map;

public interface ParticipationRequestService {

    ParticipationRequestDto addRequest(Long userId, Long eventId);

    ParticipationRequestDto cancelRequest(Long userId, Long requestId);

    List<ParticipationRequestDto> getAllUserRequests(Long userId);

    Map<Long, List<ParticipationRequest>> prepareConfirmedRequests(List<Long> eventIds);

    List<ParticipationRequestDto>  findAllByEventId(Long eventId);

    List<ParticipationRequestDto> findAllByIds(List<Long> ids);

    Map<Long, List<ParticipationRequestDto>> findAllConfirmedByEventId(List<Long> eventId);

    void setStatusRequest(Long id, Status status);
}
