package ru.practicum.analyzer.mapper;

import lombok.experimental.UtilityClass;
import ru.practicum.analyzer.model.EventSimilarity;
import ru.practicum.ewm.stats.avro.EventSimilarityAvro;

@UtilityClass
public class EventSimilarityMapper {

    public EventSimilarity mapToEventSimilarity(EventSimilarityAvro similarityAvro) {
        return EventSimilarity.builder()
                .eventA(similarityAvro.getEventA())
                .eventB(similarityAvro.getEventB())
                .score(similarityAvro.getScore())
                .timestamp(similarityAvro.getTimestamp())
                .build();
    }
}
