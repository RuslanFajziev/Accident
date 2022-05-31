package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentJdbcRepos;

import java.util.Collection;
import java.util.List;

@Service
public class AccidentServiceJdbc {
    private AccidentJdbcRepos accidentJdbcRepos;

    public AccidentServiceJdbc(AccidentJdbcRepos accidentJdbcRepos) {
        this.accidentJdbcRepos = accidentJdbcRepos;
    }

    public void createOrUpdate(Accident accident) {
        accidentJdbcRepos.createOrUpdate(accident);
    }

    public List<Accident> getAll() {
        return accidentJdbcRepos.getAll();
    }

    public Collection<AccidentType> getAllAccidentType() {
        return accidentJdbcRepos.getAllAccidentType();
    }

    public Collection<Rule> getAllRule() {
        return accidentJdbcRepos.getAllRule();
    }

    public Accident find(int id) {
        return accidentJdbcRepos.find(id);
    }

    public AccidentType findAccidentType(int id) {
        return accidentJdbcRepos.findAccidentType(id);
    }

    public Rule findRule(int id) {
        return accidentJdbcRepos.findRule(id);
    }
}