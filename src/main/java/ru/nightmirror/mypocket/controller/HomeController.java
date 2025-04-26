package ru.nightmirror.mypocket.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.nightmirror.mypocket.dto.OperationDto;
import ru.nightmirror.mypocket.entity.Operation;
import ru.nightmirror.mypocket.service.OperationService;

import jakarta.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final OperationService opService;

    @GetMapping("/home")
    public String home(Model model,
                       @AuthenticationPrincipal UserDetails user) {

        String username = user.getUsername();
        BigDecimal balance = opService.getCurrentBalance(username);
        List<Operation> ops = opService.getOperations(username);

        model.addAttribute("balance", balance);
        model.addAttribute("operations", ops);
        model.addAttribute("operationForm", new OperationDto());
        return "home";
    }

    @PostMapping("/home")
    public String addOperation(
            @ModelAttribute("operationForm") @Valid OperationDto form,
            BindingResult br,
            @AuthenticationPrincipal UserDetails user,
            Model model
    ) {
        if (br.hasErrors()) {
            String username = user.getUsername();
            model.addAttribute("balance", opService.getCurrentBalance(username));
            model.addAttribute("operations", opService.getOperations(username));
            return "home";
        }
        opService.addOperation(form, user.getUsername());
        return "redirect:/home";
    }
}
