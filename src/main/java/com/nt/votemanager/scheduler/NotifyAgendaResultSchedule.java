package com.nt.votemanager.scheduler;

import com.nt.votemanager.messaging.producer.NotifyAgendaResultProducer;
import com.nt.votemanager.messaging.producer.dto.NotifyAgendaResultDTO;
import com.nt.votemanager.model.Agenda;
import com.nt.votemanager.service.AgendaService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Log4j2
@Component
public class NotifyAgendaResultSchedule {

    @Autowired
    NotifyAgendaResultProducer notifyAgendaResultProducer;

    @Autowired
    AgendaService agendaService;

    @Scheduled(cron = "${nt.scheduler.cron.notify-agenda}")
    public void notifyFinishedAgendas() {
        List<Agenda> agendas = agendaService.findFinishedAndNotSyncedAgendas();
        List<NotifyAgendaResultDTO> notifyDto = agendas.stream().map(NotifyAgendaResultDTO::new).toList();
        if (!notifyDto.isEmpty()) {
            notifyAgendaResultProducer.send(notifyDto);
        }
    }
}
