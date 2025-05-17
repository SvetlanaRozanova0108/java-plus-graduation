package ru.practicum.ewm.producer;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.stereotype.Component;
import ru.practicum.ewm.stats.avro.UserActionAvro;

import java.time.Duration;
import java.time.Instant;

@Component
@RequiredArgsConstructor
public class KafkaProducer implements AutoCloseable {

    private final Producer<Long, UserActionAvro> producer;

    public void send(UserActionAvro message, Instant timestamp, Long eventId, String topic) {

        ProducerRecord<Long, UserActionAvro> record = new ProducerRecord<>(topic, null,
                timestamp.toEpochMilli(), eventId, message);

        producer.send(record);
        producer.flush();
    }

    @Override
    public void close() {
        producer.flush();
        producer.close(Duration.ofSeconds(10));
    }
}
