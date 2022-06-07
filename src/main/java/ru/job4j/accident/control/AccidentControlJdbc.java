package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.service.AccidentServiceJdbc;

@Controller
public class AccidentControlJdbc {
    private final AccidentServiceJdbc accidentServiceJdbc;

    public AccidentControlJdbc(AccidentServiceJdbc accidentServiceJdbc) {
        this.accidentServiceJdbc = accidentServiceJdbc;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("accidents", accidentServiceJdbc.getAllAccident());
        return "index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("types", accidentServiceJdbc.getAllAccidentType());
        model.addAttribute("rules", accidentServiceJdbc.getAllRule());
        return "accident/create";
    }

    @GetMapping("/update")
    public String change(@ModelAttribute Accident accident, Model model) {
        model.addAttribute("types", accidentServiceJdbc.getAllAccidentType());
        model.addAttribute("rules", accidentServiceJdbc.getAllRule());
        model.addAttribute("accident", accident);
        return "accident/update";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident, int typeId, int[] rIds) {
        accidentServiceJdbc.createOrUpdate(accident, typeId, rIds);
        return "redirect:/";
    }

    @PostMapping("/updateEnd")
    public String update(@ModelAttribute Accident accident, int typeId, int id, int[] rIds) {
        accident.setId(id);
        accidentServiceJdbc.createOrUpdate(accident, typeId, rIds);
        return "redirect:/";
    }
}