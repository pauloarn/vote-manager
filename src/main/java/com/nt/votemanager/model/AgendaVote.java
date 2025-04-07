package com.nt.votemanager.model;

import com.nt.votemanager.config.Catalog;
import com.nt.votemanager.config.Schema;
import com.nt.votemanager.enums.VoteEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "agenda_vote", catalog = Catalog.NTDataBase, schema = Schema.VOTE_MANAGER_DATA_BASE)
public class AgendaVote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @Column(name = "vote")
    @Enumerated(EnumType.STRING)
    private VoteEnum vote;

    @Column(name = "voter")
    private String voter;

    @CreationTimestamp
    @Column(name = "createdAt", nullable = false, updatable = false)
    private LocalDateTime createdAt;


    @ManyToOne()
    @JoinColumn(name = "agenda_fk")
    private Agenda agenda;


}
