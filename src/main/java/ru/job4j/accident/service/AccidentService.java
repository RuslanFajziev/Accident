package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentMem;

import java.util.HashMap;

@Service
public class AccidentService {
    private final AccidentMem accidentMem = new AccidentMem();

    public void createOrUpdate(Accident accident) {
        accidentMem.createOrUpdate(accident);
    }

    public Accident find(int id) {
        return accidentMem.find(id);
    }

    public HashMap<Integer, Accident> getAccidents() {
        return accidentMem.getAccidents();
    }
}