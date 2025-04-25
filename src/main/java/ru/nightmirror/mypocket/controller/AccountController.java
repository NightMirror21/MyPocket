package ru.nightmirror.mypocket.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import ru.nightmirror.mypocket.service.UserService;

@Controller
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountController {

    UserService userService;

    @PostMapping("/delete-account")
    public String deleteAccount(@CurrentSecurityContext(expression = "authentication") Authentication auth) {
        userService.deleteByUsername(auth.getName());
        return "redirect:/";
    }
}
