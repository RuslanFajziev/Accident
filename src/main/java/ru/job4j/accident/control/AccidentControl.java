package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.service.AccidentService;

import java.util.HashMap;

@Controller
public class AccidentControl {
    private AccidentService accidentService = new AccidentService();

    @GetMapping("/create")
    public String create() {
        return "accident/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident) {
        accidentService.createOrUpdate(accident);
        return "redirect:/";
    }

    @GetMapping("/update")
    public String change(String id, Model model) {
        model.addAttribute("id", id);
        return "accident/update";
    }

    @PostMapping("/updateEnd")
    public String update(@ModelAttribute Accident accident, int id) {
        accident.setId(id);
        accidentService.createOrUpdate(accident);
        return "redirect:/";
    }

    @GetMapping("/")
    public String index(Model model) {
        HashMap<Integer, Accident> accidents = accidentService.getAccidents();
        model.addAttribute("accidents", accidents);
        return "index";
    }
}