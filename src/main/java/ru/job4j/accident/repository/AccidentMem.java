package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.Collection;
import java.util.HashMap;
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
}