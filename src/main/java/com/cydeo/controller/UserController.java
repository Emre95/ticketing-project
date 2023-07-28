package com.cydeo.controller;

import com.cydeo.dto.UserDTO;
import com.cydeo.service.RoleService;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @GetMapping("/create")
    public String listAll(Model model) {

        model.addAttribute("user", new UserDTO());
        model.addAttribute("roles", roleService.listAll());
        model.addAttribute("users", userService.listAll());

        return "/user/create";
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("user") UserDTO user, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {

            model.addAttribute("roles", roleService.listAll());
            model.addAttribute("users", userService.listAll());

            return "/user/create";
        }

        userService.save(user);

        return "redirect:/user/create";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable("id") Long id, Model model) {

        model.addAttribute("user", userService.findById(id));
        model.addAttribute("roles", roleService.listAll());
        model.addAttribute("users", userService.listAll());

        return "/user/update";
    }

    @PostMapping("/update/{id}")
    public String update(@Valid @ModelAttribute("user") UserDTO user, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {

            model.addAttribute("roles", roleService.listAll());
            model.addAttribute("users", userService.listAll());

            return "/user/update";
        }

        userService.update(user);

        return "redirect:/user/create";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {

        userService.delete(id);

        return "redirect:/user/create";
    }

}
