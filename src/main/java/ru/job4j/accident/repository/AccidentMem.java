package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {
    private HashMap<Integer, Accident> accidents;
    private AtomicInteger id;

    public AccidentMem() {
        this.accidents = new HashMap<>();
        this.id = new AtomicInteger();
    }

    public Collection<Accident> getAccidents() {
        return accidents.values();
    }

    public void createOrUpdate(Accident accident) {
        int idAcc = accident.getId();
        if (idAcc != 0) {
            accidents.put(idAcc, accident);
        } else {
            idAcc = id.incrementAndGet();
            accidents.put(idAcc, accident);
            accident.setId(idAcc);
        }
    }

    public Accident find(int id) {
        return accidents.get(id);
    }

    public List<AccidentType> getLstAccType() {
        List<AccidentType> types = new ArrayList<>();
        types.add(AccidentType.of(1, "Две машины"));
        types.add(AccidentType.of(2, "Машина и человек"));
        types.add(AccidentType.of(3, "Машина и велосипед"));
        return types;
    }

    public List<Rule> getLstRules() {
        List<Rule> rules = new ArrayList<>();
        rules.add(Rule.of(1, "Статья. 1"));
        rules.add(Rule.of(2, "Статья. 2"));
        rules.add(Rule.of(3, "Статья. 3"));
        return rules;
    }
}