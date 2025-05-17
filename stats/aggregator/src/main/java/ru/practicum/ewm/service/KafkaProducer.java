package ru.practicum.ewm.service;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.stereotype.Component;
import ru.practicum.ewm.stats.avro.EventSimilarityAvro;

import java.time.Duration;
import java.util.List;

@Component
@RequiredArgsConstructor
public class KafkaProducer implements AutoCloseable {

    private final Producer<Long, EventSimilarityAvro> producer;

    public void send(List<EventSimilarityAvro> messages, String topic) {
        messages.stream()
                .map(message -> new ProducerRecord<>(topic, null,
                        message.getTimestamp().toEpochMilli(), message.getEventA(), message))
                .forEach(producer::send);
    }

    public void flush() {
        producer.flush();
    }

    @Override
    public void close() {
        producer.close(Duration.ofSeconds(10));
    }
}
