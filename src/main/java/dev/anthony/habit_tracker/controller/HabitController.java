package dev.anthony.habit_tracker.controller;

import dev.anthony.habit_tracker.model.Habit;
import dev.anthony.habit_tracker.service.HabitService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public Integer getHabitStreak(UUID uuid) {
        return habitService.getHabitStreak(uuid);
    }

    // TODO: criar um registro de hábito, ou seja, marcar um hábito como concluído em um determinado dia
    @PostMapping("/{uuid}/check")
    public Habit checkHabit(UUID uuid) {
        return habitService.getHabitByUuid(uuid);
    }

    @PostMapping
    public Habit createHabit(Habit habit) {
        return habitService.createHabit(habit);
    }

}
