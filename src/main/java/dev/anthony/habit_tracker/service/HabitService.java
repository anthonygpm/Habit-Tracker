package dev.anthony.habit_tracker.service;

import dev.anthony.habit_tracker.model.Habit;
import dev.anthony.habit_tracker.model.HabitRecord;
import dev.anthony.habit_tracker.repository.HabitRecordRepository;
import dev.anthony.habit_tracker.repository.HabitRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class HabitService {

    private final HabitRepository habitRepository;
    private final HabitRecordRepository habitRecordRepository;

    public List<Habit> getAllHabits() {
        return habitRepository.findAll();
    }

    public Integer getHabitStreak(UUID uuid) {
        List<HabitRecord> records = habitRecordRepository.findAllByHabit_Uuid(uuid);

        if (records == null || records.isEmpty()) {
            return 0;
        }

        Set<LocalDate> completedDates = records.stream()
                .map(HabitRecord::getCompletedAt)
                .collect(Collectors.toSet());

        LocalDate today = LocalDate.now();
        LocalDate checkDate;

        if (completedDates.contains(today)) {
            checkDate = today;
        } else if (completedDates.contains(today.minusDays(1))) {
            checkDate = today.minusDays(1);
        } else {
            return 0;
        }

        int streak = 0;
        while (completedDates.contains(checkDate)) {
            streak++;
            checkDate = checkDate.minusDays(1);
        }

        return streak;
    }

    public Habit checkHabit(UUID uuid) {
        Habit habit = getHabitByUuid(uuid);

        if (habit == null) {
            return null;
        }

        LocalDate today = LocalDate.now();

        if (habitRecordRepository.findByHabit_UuidAndCompletedAt(uuid, today).isEmpty()) {
            HabitRecord record = new HabitRecord();
            record.setHabit(habit);
            habitRecordRepository.save(record);
        }

        return habit;
    }

    public Habit getHabitByUuid(UUID uuid) {
        return habitRepository.findById(uuid).orElse(null);
    }

    public Habit createHabit(Habit habit) {
        return habitRepository.save(habit);
    }

    public void deleteHabit(UUID uuid) {
        habitRepository.deleteById(uuid);
    }
}
