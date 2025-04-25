package ru.nightmirror.mypocket.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.nightmirror.mypocket.service.UserService;

@Controller
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RegistrationController {

    UserService userService;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String registerUser(@RequestParam String username,
                               @RequestParam String password,
                               Model model) {
        if (userService.existsByUsername(username)) {
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
            return "registration";
        }
        userService.save(username, password);
        return "redirect:/login?registerSuccess";
    }
}
