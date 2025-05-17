package ru.practicum.ewm.runner;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.practicum.ewm.service.AggregatorService;

@Component
@RequiredArgsConstructor
public class AggregatorRun implements CommandLineRunner {

    private final AggregatorService aggregatorService;

    @Override
    public void run(String... args) {
        aggregatorService.run();
    }
}
