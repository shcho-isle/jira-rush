package com.javarush.jira.bugtracking.web;

import com.javarush.jira.bugtracking.internal.mapper.TaskMapper;
import com.javarush.jira.bugtracking.internal.model.Task;
import com.javarush.jira.bugtracking.internal.repository.TaskRepository;
import com.javarush.jira.bugtracking.to.TaskTo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class AbstractTaskController {
    @Autowired
    protected TaskMapper taskMapper;
    @Autowired
    private TaskRepository taskRepository;

    public void update(TaskTo taskTo, long id) {
        log.info("update {}, user {}", taskTo, id);
        Task task = taskMapper.updateFromTo(taskRepository.getExisted(taskTo.id()), taskTo);
        taskRepository.save(task);
    }
}
