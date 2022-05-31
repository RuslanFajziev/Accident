package ru.job4j.accident.service;

import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentMemRepos;

import java.util.Collection;

//@Service
public class AccidentService {
    private AccidentMemRepos accidentMemRepos;

    public AccidentService(AccidentMemRepos accidentMemRepos) {
        this.accidentMemRepos = accidentMemRepos;
    }

    public void createOrUpdate(Accident accident) {
        accidentMemRepos.createOrUpdate(accident);
    }

    public Accident find(int id) {
        return accidentMemRepos.find(id);
    }

    public AccidentType findAccidentType(int id) {
        return accidentMemRepos.findAccidentType(id);
    }

    public Rule findRule(int id) {
        return accidentMemRepos.findRule(id);
    }

    public Collection<Accident> getAccidents() {
        return accidentMemRepos.getAccidents();
    }

    public Collection<AccidentType> getLstAccType() {
        return accidentMemRepos.getLstAccType();
    }

    public Collection<Rule> getLstRules() {
        return accidentMemRepos.getLstRules();
    }
}