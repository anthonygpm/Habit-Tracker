package dev.anthony.habit_tracker.repository;

import dev.anthony.habit_tracker.model.HabitRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface HabitRecordRepository extends JpaRepository<HabitRecord, UUID> {
    List<HabitRecord> findAllByHabit_Uuid(UUID habitUuid);
    Optional<HabitRecord> findByHabit_UuidAndCompletedAt(UUID habitUuid, LocalDate completedAt);
}
