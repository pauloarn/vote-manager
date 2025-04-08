package com.nt.votemanager.messaging.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nt.votemanager.utils.JsonUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.nio.charset.StandardCharsets;

import static com.nt.votemanager.utils.ExceptionUtils.logException;
import static java.lang.String.format;

@Log4j2
public abstract class AbstractProducer<T> {

    RabbitTemplate rabbitTemplate;

    private JsonUtils jsonUtils;

    abstract String getQueue();

    public void send(T data) {
        try {
            convertToJsonAndSend(getQueue(), data);
        } catch (Exception e) {
            log.error("Um erro ocorreu ao enviar mensagem");
            logException(e);
        }
    }

    private void convertToJsonAndSend(String queue, T data) throws JsonProcessingException {
        String json = convertToJson(data);
        log.info(format("Mensagem: %s", json));
        log.info(format("Enviando mensagem para a fila %s", getQueue()));
        rabbitTemplate.convertAndSend(queue, json.getBytes(StandardCharsets.UTF_8));
    }

    private String convertToJson(T data) throws JsonProcessingException {
        return jsonUtils.objectToJson(data);
    }

}
