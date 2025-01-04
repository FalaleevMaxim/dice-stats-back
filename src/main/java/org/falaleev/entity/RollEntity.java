package org.falaleev.entity;

import jakarta.persistence.*;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "roll")
public class RollEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column
    private UUID id;

    @Min(1)
    @Max(20)
    @Column(nullable = false)
    private int result;

    @Column
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "character_id", nullable = false)
    private CharacterEntity character;

    @ManyToOne
    @JoinColumn(name = "dice_id", nullable = false)
    private DiceEntity dice;

    @PrePersist
    void setDate() {
        date = LocalDateTime.now();
    }
}
