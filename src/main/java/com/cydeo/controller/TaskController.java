package com.cydeo.controller;

import com.cydeo.dto.TaskDTO;
import com.cydeo.enums.Status;
import com.cydeo.service.ProjectService;
import com.cydeo.service.TaskService;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/task")
public class TaskController {

    private final TaskService taskService;
    private final UserService userService;

    private final ProjectService projectService;

    public TaskController(TaskService taskService, UserService userService, ProjectService projectService) {
        this.taskService = taskService;
        this.userService = userService;
        this.projectService = projectService;
    }

    @GetMapping("/create")
    public String create(Model model) {

        model.addAttribute("task", new TaskDTO());
        model.addAttribute("projects", projectService.listAll());
        model.addAttribute("employees", userService.listAllByRole("employee"));
        model.addAttribute("tasks", taskService.listAll());

        return "/task/create";
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("task") TaskDTO task, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {

            model.addAttribute("projects", projectService.listAll());
            model.addAttribute("employees", userService.listAllByRole("employee"));
            model.addAttribute("tasks", taskService.listAll());

            return "/task/create";
        }

        taskService.save(task);

        return "redirect:/task/create";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable("id") Long id, Model model) {

        model.addAttribute("task", taskService.findById(id));
        model.addAttribute("projects", projectService.listAll());
        model.addAttribute("employees", userService.listAllByRole("employee"));
        model.addAttribute("tasks", taskService.listAll());

        return "/task/update";
    }

    @PostMapping("/update/{id}")
    public String update(@Valid @ModelAttribute("task") TaskDTO task, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {

            model.addAttribute("projects", projectService.listAll());
            model.addAttribute("employees", userService.listAllByRole("employee"));
            model.addAttribute("tasks", taskService.listAll());

            return "/task/update";
        }

        taskService.update(task);

        return "redirect:/task/create";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {

        taskService.delete(id);

        return "redirect:/task/create";
    }

    @GetMapping("/employee/pending-tasks")
    public String pendingTasks(Model model) {

        model.addAttribute("tasks", taskService.listAllByTaskStatusIsNot(Status.COMPLETED));

        return "/task/pending-tasks";
    }

    @GetMapping("/employee/archive")
    public String archive(Model model) {

        model.addAttribute("tasks", taskService.listAllByTaskStatus(Status.COMPLETED) );

        return "/task/archive";
    }

    @GetMapping("/employee/update/{id}")
    public String employeeUpdateTask(@PathVariable("id") Long id, Model model) {

        model.addAttribute("task", taskService.findById(id));
        model.addAttribute("projects", projectService.listAll());
        model.addAttribute("employees", userService.listAllByRole("employee"));
        model.addAttribute("tasks", taskService.listAllByTaskStatusIsNot(Status.COMPLETED));
        model.addAttribute("statuses", Status.values());

        return "/task/status-update";
    }

    @PostMapping("/employee/update/{id}")
    public String employeeUpdateTask(@ModelAttribute TaskDTO dto) {

        taskService.update(dto);

        return "redirect:/task/employee/pending-tasks";
    }


}
