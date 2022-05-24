package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {
    private HashMap<Integer, Accident> accidents;
    private Map<Integer, AccidentType> types;
    private Map<Integer, Rule> rules;
    private AtomicInteger id;

    public AccidentMem() {
        this.accidents = new HashMap<>();

        this.types = new HashMap<>();
        this.types.put(1, AccidentType.of(1, "Две машины"));
        this.types.put(2, AccidentType.of(2, "Машина и человек"));
        this.types.put(3, AccidentType.of(3, "Машина и велосипед"));

        this.rules = new HashMap<>();
        this.rules.put(1, Rule.of(1, "Статья. 1"));
        this.rules.put(2, Rule.of(2, "Статья. 2"));
        this.rules.put(3, Rule.of(3, "Статья. 3"));

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

    public Map<Integer, AccidentType> getLstAccType() {
        return this.types;
    }

    public Map<Integer, Rule> getLstRules() {
        return this.rules;
    }
}