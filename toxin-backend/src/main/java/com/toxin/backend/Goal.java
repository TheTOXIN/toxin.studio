package com.toxin.backend;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "goals")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@EntityListeners(AuditingEntityListener.class)
public class Goal {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue
    private UUID id;

    @Column(name = "title", length = 254, nullable = false)
    private String title;

    @Column(name = "value", nullable = false)
    private BigDecimal value;

    @Column(name = "donate", nullable = false)
    private BigDecimal donate;

    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "created", insertable = false, updatable = false, nullable = false)
    private LocalDateTime created;
}
