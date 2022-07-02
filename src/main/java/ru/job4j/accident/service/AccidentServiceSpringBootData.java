package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentSpringBootRepos;
import ru.job4j.accident.repository.AccidentTypeSpringBootRepos;
import ru.job4j.accident.repository.RuleSpringBootRepos;

import java.util.*;

@Service
public class AccidentServiceSpringBootData {
    private AccidentSpringBootRepos accidentSpringBootRepos;
    private AccidentTypeSpringBootRepos accidentTypeSpringBootRepos;
    private RuleSpringBootRepos ruleSpringBootRepos;

    public AccidentServiceSpringBootData(AccidentSpringBootRepos accidentSpringBootRepos,
                                         AccidentTypeSpringBootRepos accidentTypeSpringBootRepos,
                                         RuleSpringBootRepos ruleSpringBootRepos) {
        this.accidentSpringBootRepos = accidentSpringBootRepos;
        this.accidentTypeSpringBootRepos = accidentTypeSpringBootRepos;
        this.ruleSpringBootRepos = ruleSpringBootRepos;
    }

    public void createOrUpdate(Accident accident, int typeId, int[] rIds) {
        int idAcc = accident.getId();
        if (idAcc == 0) {
            accident = accidentSpringBootRepos.save(accident);
        }

        var optionalAccType = accidentTypeSpringBootRepos.findById(typeId);
        AccidentType accidentType;
        if (optionalAccType.isPresent()) {
            accidentType = optionalAccType.get();
        } else {
            accidentType = new AccidentType();
        }
        accident.setType(accidentType);

        List<Integer> lstIdsR = new ArrayList<>();
        for (var elm : rIds) {
            lstIdsR.add(elm);
        }

        var iterableRule = ruleSpringBootRepos.findAllById(lstIdsR);
        Set<Rule> setRule = new HashSet<Rule>();
        iterableRule.forEach(setRule::add);
        accident.setRules(setRule);

        accidentSpringBootRepos.save(accident);
    }

    public List<Accident> getAllAccident() {
        List<Accident> lstAcc = new ArrayList<Accident>();
        accidentSpringBootRepos.findAll().forEach(lstAcc::add);
        return lstAcc;
    }

    public List<AccidentType> getAllAccidentType() {
        List<AccidentType> lstAccType = new ArrayList<AccidentType>();
        accidentTypeSpringBootRepos.findAll().forEach(lstAccType::add);
        return lstAccType;
    }

    public List<Rule> getAllRule() {
        List<Rule> lstRule = new ArrayList<Rule>();
        ruleSpringBootRepos.findAll().forEach(lstRule::add);
        return lstRule;
    }
}