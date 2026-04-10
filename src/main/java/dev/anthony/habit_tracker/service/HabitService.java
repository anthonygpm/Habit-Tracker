package dev.anthony.habit_tracker.service;

import dev.anthony.habit_tracker.model.Habit;
import dev.anthony.habit_tracker.repository.HabitRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class HabitService {

    private final HabitRepository habitRepository;

    public List<Habit> getAllHabits() {
        return habitRepository.findAll();
    }

    public Habit getHabitByUuid(UUID uuid) {
        return habitRepository.findById(uuid).orElse(null);
    }

    public Habit createHabit(Habit habit) {
        return habitRepository.save(habit);
    }

    public void deleteHabitByUuid(UUID uuid) {
        habitRepository.deleteById(uuid);
    }
}
