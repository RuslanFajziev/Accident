package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.service.AccidentService;

import java.util.*;

@Controller
public class AccidentControl {
    private AccidentService accidentService;

    public AccidentControl(AccidentService accidentService) {
        this.accidentService = accidentService;
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("types", accidentService.getLstAccType());
        model.addAttribute("rules", accidentService.getLstRules());
        return "accident/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident, int typeId, int[] rIds) {
        Set<Rule> hashSetRule = new HashSet<>();
        for (var rId : rIds) {
            hashSetRule.add(accidentService.findRule(rId));
        }
        accident.setRules(hashSetRule);

        accident.setType(accidentService.findAccidentType(typeId));
        accidentService.createOrUpdate(accident);
        return "redirect:/";
    }

    @GetMapping("/update")
    public String change(@ModelAttribute Accident accident, Model model) {
        model.addAttribute("types", accidentService.getLstAccType());
        model.addAttribute("rules", accidentService.getLstRules());
        model.addAttribute("accident", accident);
        return "accident/update";
    }

    @PostMapping("/updateEnd")
    public String update(@ModelAttribute Accident accident, int id, int typeId, int[] rIds) {
        Set<Rule> hashSetRule = new HashSet<>();
        for (var rId : rIds) {
            hashSetRule.add(accidentService.findRule(rId));
        }
        accident.setRules(hashSetRule);

        accident.setId(id);
        accident.setType(accidentService.findAccidentType(typeId));
        accidentService.createOrUpdate(accident);
        return "redirect:/";
    }

    @GetMapping("/")
    public String index(Model model) {
        Collection<Accident> accidents = accidentService.getAccidents();
        model.addAttribute("accidents", accidents);
        return "index";
    }
}