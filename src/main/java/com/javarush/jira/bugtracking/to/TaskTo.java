package com.javarush.jira.bugtracking.to;

import com.javarush.jira.common.util.validation.Code;
import com.javarush.jira.common.util.validation.NoHtml;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.*;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static com.javarush.jira.bugtracking.TaskService.calculateDays;

@Value
@EqualsAndHashCode(callSuper = true)
public class TaskTo extends NodeTo<TaskTo> {
    @Code
    String typeCode;

    @Code
    String statusCode;

    @NotBlank
    @NoHtml
    String description;

    @NotNull
    SprintTo sprint;

    @NotNull
    ProjectTo project;

    @Nullable
    LocalDateTime updated;

    @Code
    String priorityCode;

    @Positive
    int estimate;

    //    @Size(min = 1, max = 30)
    @Min(1)
    @Max(30)
    int storyPoints;

    Set<String> tags;

    List<ActivityTo> activities;

    int daysInProgress;

    int daysTesting;

    public TaskTo(Long id, String title, boolean enabled, String typeCode, String statusCode, String description, SprintTo sprint,
                  ProjectTo project, LocalDateTime updated,
                  String priorityCode, int estimate, int storyPoints, Set<String> tags, List<ActivityTo> activities, TaskTo parent) {
        super(id, title, enabled, parent);
        this.typeCode = typeCode;
        this.statusCode = statusCode;
        this.description = description;
        this.sprint = sprint;
        this.project = project;
        this.updated = updated;
        this.priorityCode = priorityCode;
        this.estimate = estimate;
        this.storyPoints = storyPoints;
        this.tags = tags;
        this.activities = activities;
        this.daysInProgress = calculateDays(activities, "in progress");
        this.daysTesting = calculateDays(activities, "testing");
    }
}
