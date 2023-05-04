package com.javarush.jira.bugtracking;

import com.javarush.jira.bugtracking.internal.model.Task;
import com.javarush.jira.bugtracking.web.AbstractTaskController;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static com.javarush.jira.bugtracking.BacklogUIController.BACKLOG_URL;

@Controller
@AllArgsConstructor
@RequestMapping(value = BACKLOG_URL)
public class BacklogUIController extends AbstractTaskController {
    static final String BACKLOG_URL = "/ui/backlog";

    private TaskService taskService;

    @GetMapping
    public String getBacklog(Model model) {
        return getBacklogPage(model, 1);
    }

    @GetMapping("/{pageNumber}")
    public String getBacklogPage(Model model, @PathVariable("pageNumber") int pageNumber) {
        Page<Task> tasksPage = taskService.getBacklogPage(PageRequest.of(pageNumber - 1, 5));
        int totalPages = tasksPage.getTotalPages();
        long toalTasks = tasksPage.getTotalElements();
        List<Task> tasksList = tasksPage.getContent();

        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalTasks", toalTasks);
        model.addAttribute("tasksList", taskMapper.toToList(tasksList));

        return "backlog";
    }
}
