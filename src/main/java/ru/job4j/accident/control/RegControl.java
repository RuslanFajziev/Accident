package ru.job4j.accident.control;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.accident.model.User;
import ru.job4j.accident.service.AuthorityService;
import ru.job4j.accident.service.UserService;

@Controller
public class RegControl {

    private final PasswordEncoder encoder;
    private final UserService userService;
    private final AuthorityService authorityService;

    public RegControl(PasswordEncoder encoder, UserService userService, AuthorityService authorityService) {
        this.encoder = encoder;
        this.userService = userService;
        this.authorityService = authorityService;
    }

    @PostMapping("/reg")
    public String regSave(@ModelAttribute User user, int authorityId, Model model) {
        String userName = user.getUsername();
        if (userService.checkUser(user)) {
            String errorMessage = "There is already a user with this var, try another NAME!!".replace("var", userName);
            model.addAttribute("errorMessage", errorMessage);
            return "login";
        } else {
            user.setEnabled(true);
            user.setPassword(encoder.encode(user.getPassword()));
            user.setAuthority(authorityService.findById(authorityId));
            userService.save(user);
            return "redirect:/login";
        }
    }

    @GetMapping("/reg")
    public String regPage(Model model) {
        model.addAttribute("authorities", authorityService.getAllAuthority());
        return "reg";
    }
}