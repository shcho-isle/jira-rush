package com.javarush.jira.bugtracking.to;

import com.javarush.jira.common.to.TitleTo;
import com.javarush.jira.common.util.validation.Code;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = true)
public class SprintTo extends TitleTo {
    @Code
    String statusCode;

    @NotNull
    ProjectTo project;

    public SprintTo(Long id, String title, boolean enabled, String statusCode, ProjectTo project) {
        super(id, title, enabled);
        this.statusCode = statusCode;
        this.project = project;
    }
}
