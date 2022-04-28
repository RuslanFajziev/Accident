package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accident.model.Accident;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
public class IndexControl {
    @GetMapping("/")
    public String index(Model model) {
        HashMap<Integer, Accident> accidents = new HashMap<>();
        Accident accident = new Accident();
        accident.setId(1);
        accident.setName("Превышение");
        accident.setText("Нарушил скоростной режим");
        accident.setAddress("г.Москва");
        accidents.put(1, accident);
        model.addAttribute("accidents", accidents);
        return "index";
    }
}