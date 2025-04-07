package com.nt.votemanager.client.config.error;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nt.votemanager.exceptions.ExternalApiErrorException;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;

import java.io.Reader;
import java.util.Objects;

@Log4j2
public class FeignErrorRequestsConfiguration implements ErrorDecoder {

    @SneakyThrows
    @Override
    public Exception decode(String s, Response response) {
        ExceptionMessage exceptionMessage = null;
        Reader reader = null;
        String result = "";

        try {
            if (Objects.nonNull(response.body())) {
                reader = response.body().asReader();
                result = reader.toString();

                ObjectMapper mapper = new ObjectMapper();
                mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
                exceptionMessage = mapper.readValue(result,
                        ExceptionMessage.class);
                exceptionMessage.setResponseBody(mapper.readValue(result, Object.class));
            }

        } finally {
            if (Objects.nonNull(reader)) {
                reader.close();
            }
        }
        if (Objects.nonNull(exceptionMessage) && Objects.nonNull(exceptionMessage.getMessageCode())) {
            return erroExternoMapeadoEureka(response, exceptionMessage);
        }
        return erroExternoGenerico(response, exceptionMessage);

    }

    private ExternalApiErrorException erroExternoMapeadoEureka(Response response, ExceptionMessage exceptionMessage) throws JsonProcessingException {
        int status = response.status();
        var request = response.request().requestTemplate();


        log.error("Erro na chamada do método: {}", request.methodMetadata().configKey());
        log.error("URL: [ {} ] {}", request.method(), request.url());
        log.error("body: {}", new ObjectMapper().writeValueAsString(exceptionMessage));

        return ExternalApiErrorException.builder()
                .httpStatus(HttpStatus.resolve(status))
                .messageCode(exceptionMessage.getMessageCode())
                .message(exceptionMessage.getMessage())
                .body(exceptionMessage.getBody())
                .build();
    }

    private ExternalApiErrorException erroExternoGenerico(Response response, ExceptionMessage exceptionMessage) {
        int status = response.status();
        return ExternalApiErrorException.builder()
                .httpStatus(HttpStatus.resolve(status))
                .build();
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    public static class ExceptionMessage {
        private Object responseBody;
        private int statusCode;
        private String messageCode;
        private String message;
        private Object body;
    }

}
