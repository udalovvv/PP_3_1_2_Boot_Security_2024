package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.validation.Valid;


@Controller
@RequestMapping("/admin")
@CrossOrigin
public class AdminController {

    private static final String REDIRECT_ADMIN = "redirect:/admin";
    private static final String AUTHUSER = "authUser";
    private static final String AUTHENTICATION = "authentication";

    private final UserService userService;



    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public String index(Model userModel,
                        Authentication authentication) {
        userModel.addAttribute("users", userService.findAll());
        userModel.addAttribute("newUser", new User());
        userModel.addAttribute(AUTHENTICATION, authentication);
        userModel.addAttribute(AUTHUSER, authentication.getPrincipal());
        return "pages/index";
    }

    @GetMapping("/add")
    public String add(Model userModel,
                         Authentication authentication) {
        userModel.addAttribute("newUser", new User());
        userModel.addAttribute(AUTHENTICATION, authentication);
        userModel.addAttribute(AUTHUSER, authentication.getPrincipal());
        return "pages/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("newUser") @Valid User user,
                      BindingResult bindingResult,
                      Authentication authentication,
                      Model userModel) {
        if (userService.userFoundByEmail(user.getEmail())) {
            bindingResult.rejectValue("email", "error.user",
                    "Пользователь с таким email уже существует");
        }

        if (bindingResult.hasErrors()) {
            userModel.addAttribute(AUTHENTICATION, authentication);
            userModel.addAttribute(AUTHUSER, authentication.getPrincipal());
            return "pages/add";
        }
        userService.save(user);
        return REDIRECT_ADMIN;
    }


    @GetMapping("/edit")
    public String edit(@RequestParam("id") long id,
                       Model userModel,
                       Authentication authentication) {
        userModel.addAttribute("user", userService.findById(id));
        userModel.addAttribute(AUTHENTICATION, authentication);
        userModel.addAttribute(AUTHUSER, authentication.getPrincipal());
        return "pages/edit";
    }

    @PostMapping("/edit")
    public String edit(@RequestParam("id") long id,
                       @ModelAttribute("user") @Valid User user,
                       BindingResult bindingResult,
                       Model userModel,
                       Authentication authentication) {
        if (bindingResult. hasErrors() && bindingResult.getErrorCount() > 1) {
            userModel.addAttribute(AUTHENTICATION, authentication);
            userModel.addAttribute(AUTHUSER, authentication.getPrincipal());
            return "pages/edit";
        }

        userService.updateUser(id, user);
        return REDIRECT_ADMIN;
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") long id) {
        userService.deleteById(id);
        return REDIRECT_ADMIN;
    }



}
