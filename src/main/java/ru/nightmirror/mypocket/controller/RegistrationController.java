package ru.nightmirror.mypocket.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.nightmirror.mypocket.dto.RegistrationDto;
import ru.nightmirror.mypocket.service.UserService;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String showForm(Model model) {
        model.addAttribute("registrationForm", new RegistrationDto());
        return "registration";
    }

    @PostMapping
    public String processForm(
            @ModelAttribute("registrationForm") @Valid RegistrationDto form,
            BindingResult bindingResult,
            Model model
    ) {

        if (!form.getPassword().equals(form.getPasswordConfirm())) {
            bindingResult.rejectValue(
                    "passwordConfirm",
                    "error.passwordConfirm",
                    "Пароли не совпадают"
            );
        }

        if (userService.existsByUsername(form.getUsername())) {
            bindingResult.rejectValue(
                    "username",
                    "error.username",
                    "Пользователь уже существует"
            );
        }
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        userService.save(form.getUsername(), form.getPassword());
        return "redirect:/login?registerSuccess";
    }
}

