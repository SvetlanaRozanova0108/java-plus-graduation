package ru.practicum.ewm.handler;


import ru.practicum.ewm.grpc.stats.InteractionsCountRequestProto;
import ru.practicum.ewm.grpc.stats.RecommendedEventProto;
import ru.practicum.ewm.grpc.stats.SimilarEventsRequestProto;
import ru.practicum.ewm.grpc.stats.UserPredictionsRequestProto;

import java.util.List;

public interface RecommendationsHandler {

    List<RecommendedEventProto> getRecommendationsForUser(UserPredictionsRequestProto request);

    List<RecommendedEventProto> getSimilarEvents(SimilarEventsRequestProto request);

    List<RecommendedEventProto> getInteractionsCount(InteractionsCountRequestProto request);
}
