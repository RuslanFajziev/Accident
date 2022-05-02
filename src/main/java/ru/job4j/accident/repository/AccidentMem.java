package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {
    private static HashMap<Integer, Accident> accidents = new HashMap<>();
    private static AtomicInteger id = new AtomicInteger();

    public HashMap<Integer, Accident> getAccidents() {
        return accidents;
    }

    public static void create(Accident accident) {
        int idAcc = id.incrementAndGet();
        accidents.put(idAcc, accident);
        accident.setId(idAcc);
    }
}