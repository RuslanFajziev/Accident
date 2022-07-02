package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.service.AccidentServiceSpringBootData;

@Controller
public class AccidentControlSpringBootData {
    private final AccidentServiceSpringBootData accidentServiceSpringBootData;

    public AccidentControlSpringBootData(AccidentServiceSpringBootData accidentServiceSpringBootData) {
        this.accidentServiceSpringBootData = accidentServiceSpringBootData;
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