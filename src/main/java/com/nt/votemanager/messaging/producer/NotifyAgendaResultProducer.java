package com.nt.votemanager.messaging.producer;

import com.nt.votemanager.messaging.producer.dto.NotifyAgendaResultDTO;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NotifyAgendaResultProducer extends AbstractProducer<List<NotifyAgendaResultDTO>> {

    @Value("${nt.rabbitmq.queue.producer.notify-agenda}")
    @Getter
    private String queue;
}
