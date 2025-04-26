package ru.practicum.ewm.stats.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import ru.practicum.ewm.stats.dto.EndpointHitDto;
import ru.practicum.ewm.stats.dto.StatsDto;
import ru.practicum.ewm.stats.exceptions.RestClientRuntimeException;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class StatClientImpl implements StatClient {


    private final DiscoveryClient discoveryClient;
    @Value("${statsServiceId}")
    private String statsServiceId;

    @Autowired
    public StatClientImpl(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;

    }

    private ServiceInstance getInstance() {
        try {
            return discoveryClient
                    .getInstances(statsServiceId)
                    .getFirst();
        } catch (Exception exception) {
            throw new RuntimeException(
                    "Ошибка обнаружения адреса сервиса статистики с id: " + statsServiceId,
                    exception
            );
        }
    }

    public String saveHit(EndpointHitDto requestBody) {

        ServiceInstance instance = getInstance();
        var serverUrl = URI.create("http://" + instance.getHost() + ":" + instance.getPort()).toString();
        var restClient = RestClient.builder().baseUrl(serverUrl).build();

        return restClient.post()
                .uri("/hit")
                .contentType(MediaType.APPLICATION_JSON)
                .body(requestBody)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (request, response) -> {
                    throw new RestClientRuntimeException(response.getStatusCode(), response.getBody().toString());
                })
                .onStatus(HttpStatusCode::is5xxServerError, (request, response) -> {
                    throw new RestClientRuntimeException(response.getStatusCode(), response.getBody().toString());
                })
                .body(String.class);

    }

    public List<StatsDto> getStats(LocalDateTime start, LocalDateTime end, String uris, boolean unique) {

        Map<String, Object> requestParams = Map.of("start", start, "end", end, "uris", uris,
                "unique", unique);

        UriComponents uriComponents = UriComponentsBuilder
                .fromUriString("/ru/practicum/ewm/stats")
                .build().expand(requestParams);

        ServiceInstance instance = getInstance();
        var serverUrl = URI.create("http://" + instance.getHost() + ":" + instance.getPort()).toString();
        var restClient = RestClient.builder().baseUrl(serverUrl).build();

        return restClient.get()
                .uri(uriComponents.toUriString())
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (request, response) -> {
                    throw new RestClientRuntimeException(response.getStatusCode(), response.getBody().toString());
                })
                .onStatus(HttpStatusCode::is5xxServerError, (request, response) -> {
                    throw new RestClientRuntimeException(response.getStatusCode(), response.getBody().toString());
                })
                .body(new ParameterizedTypeReference<>() {
                });
    }
}
