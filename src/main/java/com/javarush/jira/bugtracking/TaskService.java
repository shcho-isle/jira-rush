package com.javarush.jira.bugtracking;

import com.javarush.jira.bugtracking.internal.mapper.TaskMapper;
import com.javarush.jira.bugtracking.internal.model.Task;
import com.javarush.jira.bugtracking.internal.repository.TaskRepository;
import com.javarush.jira.bugtracking.to.TaskTo;
import org.springframework.stereotype.Service;

@Service
public class TaskService extends BugtrackingService<Task, TaskTo> {
    public TaskService(TaskRepository repository, TaskMapper mapper) {
        super(repository, mapper);
    }
}
