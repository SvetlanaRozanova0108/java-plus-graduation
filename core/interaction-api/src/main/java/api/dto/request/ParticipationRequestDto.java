package main.java.api.dto.request;
import main.java.api.enums.request.Status;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.*;
import java.time.LocalDateTime;

import static main.java.api.utils.date.DateTimeFormat.TIME_PATTERN;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class ParticipationRequestDto {
    Long id;
    @JsonFormat(pattern = TIME_PATTERN)
    LocalDateTime created;
    Long eventId;
    Long requesterId;
    Status status;
}
