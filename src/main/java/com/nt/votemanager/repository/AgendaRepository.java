package com.nt.votemanager.repository;

import com.nt.votemanager.model.Agenda;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface AgendaRepository extends GenericRepository<Agenda, Integer> {
    @Query("select a from Agenda a where a.endAt >= :processTime and a.hasSynced = false ")
    List<Agenda> findAgendaFinishedLastMinute(
            @Param("processTime") LocalDateTime processTime
    );
}
