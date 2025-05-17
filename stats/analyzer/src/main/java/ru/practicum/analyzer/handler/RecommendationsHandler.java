package ru.practicum.analyzer.handler;

import ru.practicum.grpc.stats.InteractionsCountRequestProto;
import ru.practicum.grpc.stats.RecommendedEventProto;
import ru.practicum.grpc.stats.SimilarEventsRequestProto;
import ru.practicum.grpc.stats.UserPredictionsRequestProto;

import java.util.List;

public interface RecommendationsHandler {

    List<RecommendedEventProto> getRecommendationsForUser(UserPredictionsRequestProto request);

    List<RecommendedEventProto> getSimilarEvents(SimilarEventsRequestProto request);

    List<RecommendedEventProto> getInteractionsCount(InteractionsCountRequestProto request);
}
