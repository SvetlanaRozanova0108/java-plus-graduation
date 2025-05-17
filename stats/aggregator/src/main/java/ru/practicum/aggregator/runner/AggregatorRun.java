package ru.practicum.aggregator.runner;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.practicum.aggregator.service.AggregatorService;

@Component
@RequiredArgsConstructor
public class AggregatorRun implements CommandLineRunner {

    private final AggregatorService aggregatorService;

    @Override
    public void run(String... args) {
        aggregatorService.run();
    }
}
