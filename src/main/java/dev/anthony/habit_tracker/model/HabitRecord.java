package dev.anthony.habit_tracker.model;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonBackReference;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@Table(name = "habit_records_tb")
public class HabitRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @Column(nullable = false)
    private LocalDate completedAt = LocalDate.now();

    @ManyToOne
    @JoinColumn(name = "habit_uuid", nullable = false)
    @JsonBackReference
    private Habit habit;

}
