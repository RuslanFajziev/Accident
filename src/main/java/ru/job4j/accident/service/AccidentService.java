package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentMem;

import java.util.Collection;
import java.util.Map;

@Service
public class AccidentService {
    private AccidentMem accidentMem;

    public AccidentService(AccidentMem accidentMem) {
        this.accidentMem = accidentMem;
    }

    public void createOrUpdate(Accident accident) {
        accidentMem.createOrUpdate(accident);
    }

    public Accident find(int id) {
        return accidentMem.find(id);
    }

    public Collection<Accident> getAccidents() {
        return accidentMem.getAccidents();
    }

    public Map<Integer, AccidentType> getLstAccType() {
        return accidentMem.getLstAccType();
    }

    public Map<Integer, Rule> getLstRules() {
        return accidentMem.getLstRules();
    }
}