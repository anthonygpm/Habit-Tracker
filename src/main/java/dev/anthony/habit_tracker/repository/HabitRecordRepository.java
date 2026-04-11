package dev.anthony.habit_tracker.repository;

import dev.anthony.habit_tracker.model.HabitRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface HabitRecordRepository extends JpaRepository<HabitRecord, UUID> {
    public List<HabitRecord> findAllByHabitUuid(UUID habitUuid);
}
