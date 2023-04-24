package com.javarush.jira.bugtracking.web;

import com.javarush.jira.bugtracking.to.TaskTo;
import com.javarush.jira.login.AuthUser;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = TaskRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class TaskRestController extends AbstractTaskController {
    public static final String REST_URL = "/api/task";

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody TaskTo taskTo, @AuthenticationPrincipal AuthUser authUser) {
        super.update(taskTo, authUser.id());
    }

    @PutMapping(value = "/subscribe", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void subscribe(@Valid @RequestBody TaskTo taskTo, @AuthenticationPrincipal AuthUser authUser) {
        super.subscribe(taskTo, authUser.id());
    }

    @PutMapping(value = "/unsubscribe", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void unsubscribe(@Valid @RequestBody TaskTo taskTo, @AuthenticationPrincipal AuthUser authUser) {
        super.unsubscribe(taskTo, authUser.id());
    }

    @GetMapping("/backlog")
    public List<TaskTo> getBacklog(Pageable pageable, @AuthenticationPrincipal AuthUser authUser) {
        return super.getBacklog(pageable, authUser.id());
    }
}
