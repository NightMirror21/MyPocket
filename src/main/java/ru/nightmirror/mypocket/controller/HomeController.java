package ru.nightmirror.mypocket.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.nightmirror.mypocket.dto.OperationDto;
import ru.nightmirror.mypocket.entity.Operation;
import ru.nightmirror.mypocket.service.CategoryService;
import ru.nightmirror.mypocket.service.OperationService;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final OperationService opService;
    private final CategoryService categoryService;

    @GetMapping("/home")
    public String home(Model model,
                       @AuthenticationPrincipal UserDetails user) {

        String username = user.getUsername();
        BigDecimal balance = opService.getCurrentBalance(username);
        List<Operation> ops = opService.getOperations(username);

        model.addAttribute("balance", balance);
        model.addAttribute("operations", ops);
        model.addAttribute("operationForm", new OperationDto());
        model.addAttribute("categories", categoryService.getCategories(username));

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
            model.addAttribute("categories", categoryService.getCategories(username));
            return "home";
        }
        opService.addOperation(form, user.getUsername());
        return "redirect:/home";
    }

    @PostMapping("/home/categories")
    public String addCategory(@RequestParam("name") String name,
                              @AuthenticationPrincipal UserDetails user) {
        categoryService.createCategory(name, user.getUsername());
        return "redirect:/home";
    }

    @PostMapping("/home/categories/update")
    public String updateCategory(@RequestParam("categoryId") Long categoryId,
                                @RequestParam("newName") String newName,
                                @AuthenticationPrincipal UserDetails user) {
        categoryService.updateCategoryName(categoryId, newName, user.getUsername());
        return "redirect:/home";
    }
}
