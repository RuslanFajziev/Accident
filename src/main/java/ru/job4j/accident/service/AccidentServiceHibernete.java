package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentHibernateRepos;

import java.util.List;

//@Service
public class AccidentServiceHibernete {
    private AccidentHibernateRepos accidentHibernateRepos;

    public AccidentServiceHibernete(AccidentHibernateRepos accidentHibernateRepos) {
        this.accidentHibernateRepos = accidentHibernateRepos;
    }

    public void createOrUpdate(Accident accident, int typeId, int[] rIds) {
        accidentHibernateRepos.createOrUpdate(accident, typeId, rIds);
    }

    public List<Accident> getAllAccident() {
        return accidentHibernateRepos.getAllAccident();
    }

    public List<AccidentType> getAllAccidentType() {
        return accidentHibernateRepos.getAllAccidentType();
    }

    public List<Rule> getAllRule() {
        return accidentHibernateRepos.getAllRule();
    }

    public AccidentType getAccidentType(int id) {
        return accidentHibernateRepos.getAccidentType(id);
    }

    public List<Rule> getRuleForIds(int[] rIds) {
        return accidentHibernateRepos.getRuleForIds(rIds);
    }
}