package com.nt.votemanager.model;

import com.nt.votemanager.config.Catalog;
import com.nt.votemanager.config.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "agenda", catalog = Catalog.NTDataBase, schema = Schema.VOTE_MANAGER_DATA_BASE)
public class Agenda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "startAt", nullable = true)
    private LocalDateTime startAt;

    @Column(name = "endAt", nullable = true)
    private LocalDateTime endAt;

    @CreationTimestamp
    @Column(name = "createdAt", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updatedAt")
    private LocalDateTime updatedAt;
}
