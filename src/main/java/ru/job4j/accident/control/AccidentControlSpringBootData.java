package ru.job4j.accident.control;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.service.AccidentServiceSpringBootData;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class AccidentControlSpringBootData {
    private final AccidentServiceSpringBootData accidentServiceSpringBootData;

    public AccidentControlSpringBootData(AccidentServiceSpringBootData accidentServiceSpringBootData) {
        this.accidentServiceSpringBootData = accidentServiceSpringBootData;
    }

    @GetMapping("/login")
    public String loginPage(@RequestParam(value = "error", required = false) String error,
                            @RequestParam(value = "logout", required = false) String logout,
                            Model model) {
        String errorMessage = null;
        if (error != null) {
            errorMessage = "Username or Password is incorrect !!";
        }
        if (logout != null) {
            errorMessage = "You have been successfully logged out !!";
        }
        model.addAttribute("errorMessage", errorMessage);
        return "login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout=true";
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("accidents", accidentServiceSpringBootData.getAllAccident());
        return "index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("types", accidentServiceSpringBootData.getAllAccidentType());
        model.addAttribute("rules", accidentServiceSpringBootData.getAllRule());
        return "accident/create";
    }

    @GetMapping("/update")
    public String change(@ModelAttribute Accident accident, Model model) {
        model.addAttribute("types", accidentServiceSpringBootData.getAllAccidentType());
        model.addAttribute("rules", accidentServiceSpringBootData.getAllRule());
        model.addAttribute("accident", accident);
        return "accident/update";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident, int typeId, int[] rIds) {
        accidentServiceSpringBootData.createOrUpdate(accident, typeId, rIds);
        return "redirect:/";
    }

    @PostMapping("/updateEnd")
    public String update(@ModelAttribute Accident accident, int typeId, int id, int[] rIds) {
        accident.setId(id);
        accidentServiceSpringBootData.createOrUpdate(accident, typeId, rIds);
        return "redirect:/";
    }
}