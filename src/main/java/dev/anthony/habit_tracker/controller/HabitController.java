package dev.anthony.habit_tracker.controller;

import dev.anthony.habit_tracker.model.Habit;
import dev.anthony.habit_tracker.service.HabitService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/habits")
@AllArgsConstructor
public class HabitController {

    private final HabitService habitService;

    @GetMapping
    public List<Habit> getAllHabits() {
        return habitService.getAllHabits();
    }

    @GetMapping("/{uuid}/streak")
    public Integer getHabitStreak(@PathVariable UUID uuid) {
        return habitService.getHabitStreak(uuid);
    }

    @PostMapping("/{uuid}/check")
    public Habit checkHabit(@PathVariable UUID uuid) {
        return habitService.checkHabit(uuid);
    }

    @PostMapping
    public Habit createHabit(@RequestBody Habit habit) {
        return habitService.createHabit(habit);
    }

    @DeleteMapping("/{uuid}")
    public void deleteHabit(@PathVariable UUID uuid) {
        habitService.deleteHabit(uuid);
    }
}
