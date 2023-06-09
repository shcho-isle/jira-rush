package com.javarush.jira.bugtracking.web;

import com.javarush.jira.bugtracking.TaskService;
import com.javarush.jira.bugtracking.internal.mapper.TaskMapper;
import com.javarush.jira.bugtracking.internal.model.Task;
import com.javarush.jira.bugtracking.internal.model.UserBelong;
import com.javarush.jira.bugtracking.internal.repository.UserBelongRepository;
import com.javarush.jira.bugtracking.to.ObjectType;
import com.javarush.jira.bugtracking.to.TaskTo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@Slf4j
public class AbstractTaskController {
    @Autowired
    protected TaskMapper taskMapper;
    @Autowired
    private TaskService taskService;
    @Autowired
    UserBelongRepository userBelongRepository;

    public void update(TaskTo taskTo, long userId) {
        log.info("update {}, user {}", taskTo, userId);
        taskService.save(taskTo);
    }

    public void subscribe(TaskTo taskTo, long userId) {
        log.info("subscribe {}, user {}", taskTo, userId);
        UserBelong userBelong = new UserBelong();
        userBelong.setObjectId(taskTo.id());
        userBelong.setObjectType(ObjectType.TASK);
        userBelong.setUserId(userId);
        userBelong.setUserTypeCode("subscriber");
        userBelongRepository.save(userBelong);
    }

    public void unsubscribe(TaskTo taskTo, long userId) {
        log.info("unsubscribe {}, user {}", taskTo, userId);
        UserBelong userBelong = new UserBelong();
        userBelong.setObjectId(taskTo.id());
        userBelong.setObjectType(ObjectType.TASK);
        userBelong.setUserId(userId);
        userBelong.setUserTypeCode("subscriber");
        Optional<UserBelong> userBelongOptional = userBelongRepository.findOne(Example.of(userBelong));
        userBelongOptional.ifPresent(ub -> userBelongRepository.delete(ub));
    }

    public List<TaskTo> getBacklog(Pageable pageable, long userId) {
        log.info("backlog, user {}", userId);
        Page<Task> backlogPage = taskService.getBacklogPage(pageable);
        return taskMapper.toToList(backlogPage.getContent());
    }
}
