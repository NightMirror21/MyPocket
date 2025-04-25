package ru.nightmirror.mypocket.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import ru.nightmirror.mypocket.service.UserService;

@Controller
public class AccountController {

    private final UserService userService;

    public AccountController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/delete-account")
    public String deleteAccount(@CurrentSecurityContext(expression = "authentication") Authentication auth) {
        userService.deleteByUsername(auth.getName());
        return "redirect:/";
    }
}
