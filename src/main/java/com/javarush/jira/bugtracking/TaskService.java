package com.javarush.jira.bugtracking;

import com.javarush.jira.bugtracking.internal.mapper.TaskMapper;
import com.javarush.jira.bugtracking.internal.model.Task;
import com.javarush.jira.bugtracking.internal.repository.TaskRepository;
import com.javarush.jira.bugtracking.to.ActivityTo;
import com.javarush.jira.bugtracking.to.TaskTo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
public class TaskService extends BugtrackingService<Task, TaskTo, TaskRepository> {
    public TaskService(TaskRepository repository, TaskMapper mapper) {
        super(repository, mapper);
    }

    public List<TaskTo> getAll() {
        return mapper.toToList(repository.getAll());
    }

    public static int calculateDays(List<ActivityTo> activities, String typeCode) {
        activities.sort(Comparator.comparing(ActivityTo::getUpdated));
        Optional<ActivityTo> start = activities.stream()
                .filter(a -> a.getTypeCode().equals(typeCode))
                .findFirst();
        if (start.isEmpty()) {
            return 0;
        }

        int startIndex = activities.indexOf(start.get());
        LocalDate startDate = start.get().getUpdated().toLocalDate();
        LocalDate finishDate;
        if (startIndex + 1 < activities.size()) {
            ActivityTo finish = activities.get(activities.indexOf(start.get()) + 1);
            finishDate = finish.getUpdated().toLocalDate();
        } else {
            finishDate = LocalDate.now();
        }
        return (int) DAYS.between(startDate, finishDate);
    }
}
