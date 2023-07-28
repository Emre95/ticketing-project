package com.cydeo.controller;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.service.ProjectService;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/project")
public class ProjectController {

    private final ProjectService projectService;
    private final UserService userService;

    public ProjectController(ProjectService projectService, UserService userService) {
        this.projectService = projectService;
        this.userService = userService;
    }

    @GetMapping("/create")
    public String create(Model model) {

        model.addAttribute("project", new ProjectDTO());
        model.addAttribute("managers", userService.listAllByRole("manager"));
        model.addAttribute("projects", projectService.listAll());


        return "/project/create";
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("project") ProjectDTO project, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {

            model.addAttribute("managers", userService.listAllByRole("manager"));
            model.addAttribute("projects", projectService.listAll());

            return "/project/create";
        }

        projectService.save(project);

        return "redirect:/project/create";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable("id") Long id, Model model) {

        model.addAttribute("project", projectService.findById(id));
        model.addAttribute("managers", userService.listAllByRole("manager"));
        model.addAttribute("projects", projectService.listAll());

        return "/project/update";
    }

    @PostMapping("/update/{id}")
    public String update(@Valid @ModelAttribute("project") ProjectDTO project, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {

            model.addAttribute("managers", userService.listAllByRole("manager"));
            model.addAttribute("projects", projectService.listAll());

            return "/project/update";
        }

        projectService.update(project);

        return "redirect:/project/create";
    }


    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {

        projectService.delete(id);

        return "redirect:/project/create";
    }

    @GetMapping("/complete/{id}")
    public String complete(@PathVariable("id") Long id) {

        projectService.complete(id);

        return "redirect:/project/create";
    }

    @GetMapping("/manager/project-status")
    public String projectStatus(Model model) {

        model.addAttribute("projects", projectService.findAllByAssignedManagerWithCompletedAndNonCompletedTaskCounts());

        return "/manager/project-status";
    }

    @GetMapping("/manager/complete/{id}")
    public String managerComplete(@PathVariable("id") Long id) {

        projectService.complete(id);

        return "redirect:/project/manager/project-status";
    }


}
