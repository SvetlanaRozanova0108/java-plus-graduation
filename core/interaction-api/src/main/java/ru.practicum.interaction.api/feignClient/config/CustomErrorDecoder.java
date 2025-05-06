package ru.practicum.interaction.api.feignClient.config;

import feign.Response;
import feign.codec.ErrorDecoder;
import jakarta.ws.rs.NotFoundException;
import main.java.api.exception.BadRequestException;
import main.java.api.exception.ServerErrorException;
import org.springframework.stereotype.Component;
import ru.practicum.interaction.api.exception.*;

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
