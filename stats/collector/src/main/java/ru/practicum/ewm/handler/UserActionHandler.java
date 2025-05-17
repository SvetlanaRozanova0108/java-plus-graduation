package ru.practicum.ewm.handler;
import ru.practicum.ewm.grpc.stats.UserActionProto;

public interface UserActionHandler {

    void handle(UserActionProto userActionProto);
}
