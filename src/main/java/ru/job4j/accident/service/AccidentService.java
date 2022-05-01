package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentMem;

import java.util.HashMap;

@Service
public class AccidentService {
    private final AccidentMem accidentMem = AccidentMem.getInstance();

    public void save(Accident accident) {
        accidentMem.create(accident);
    }

    public HashMap<Integer, Accident> getAccidents() {
        return accidentMem.getAccidents();
    }
}