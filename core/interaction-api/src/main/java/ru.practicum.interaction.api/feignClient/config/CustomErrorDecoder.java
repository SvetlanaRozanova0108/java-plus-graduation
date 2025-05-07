package ru.practicum.interaction.api.feignClient.config;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.stereotype.Component;
import ru.practicum.interaction.api.exception.BadRequestException;
import ru.practicum.interaction.api.exception.NotFoundException;
import ru.practicum.interaction.api.exception.ServerErrorException;

@Component
public class CustomErrorDecoder implements ErrorDecoder {
    private final ErrorDecoder errorDecoder = new Default();

    @Override
    public Exception decode(String s, Response response) {
        if (response.status() == 400) {
            throw new BadRequestException("Bad request".formatted(s));
        } else if (response.status() == 404) {
            throw new NotFoundException("Not Found".formatted(s));
        } else if (response.status() == 500) {
            throw new ServerErrorException("Internal server error".formatted(s));
        } else {
            return errorDecoder.decode(s, response);
        }
    }
}
