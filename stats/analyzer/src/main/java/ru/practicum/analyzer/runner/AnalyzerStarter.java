package ru.practicum.analyzer.runner;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.practicum.analyzer.service.EventSimilarityService;
import ru.practicum.analyzer.service.UserActionService;

@Component
@RequiredArgsConstructor
public class AnalyzerStarter implements CommandLineRunner {

private final UserActionService userActionService;
private final EventSimilarityService eventSimilarityService;


    @Override
    public void run(String... args) {
        Thread userActionThread = new Thread(userActionService);
        userActionThread.setName("userActionHandlerThread");
        userActionThread.start();

        eventSimilarityService.run();
    }
}
