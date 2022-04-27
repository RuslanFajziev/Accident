package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexControl {
    @GetMapping("/")
    public String index(Model model) {
        List<String> lst = new ArrayList<>();
        lst.add("Вася");
        lst.add("Петя");
        lst.add("Саня");
        lst.add("Вовчик");
        lst.add("Руслан");
        model.addAttribute("user", "Petr Arsentev");
        model.addAttribute("names", lst);
        return "index";
    }
}