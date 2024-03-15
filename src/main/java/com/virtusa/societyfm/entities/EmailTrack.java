package com.virtusa.societyfm.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;
import java.util.UUID;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "email_track")
public class EmailTrack {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "email_track_id", updatable = false, nullable = false, columnDefinition = "uuid")
    private UUID emailTrackId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private com.virtusa.societyfm.entities.SocietyUser user;

    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "email_from", nullable = false)
    private String from;

    @Column(name = "email_to", nullable = false)
    private String to;

    @Column(name = "subject", nullable = false)
    private String subject;

    @Column(name = "body", nullable = false)
    private String body;
    @PrePersist
    public void prePersist() {
        if (this.emailTrackId == null) {
            this.emailTrackId = UUID.randomUUID();
        }
    }
}
