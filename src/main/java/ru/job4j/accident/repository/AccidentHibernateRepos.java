package ru.job4j.accident.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class AccidentHibernateRepos {
    private final SessionFactory sf;

    public AccidentHibernateRepos(SessionFactory sf) {
        this.sf = sf;
    }

    public void createOrUpdate(Accident accident, int typeId, int[] rIds) {
        int idAcc = accident.getId();
        AccidentType accidentType = getAccidentType(typeId);
        accident.setType(accidentType);

//        List<Rule> ruleList = getRuleForIds(rIds);
//        Set<Rule> ruleSet = new HashSet<>(ruleList);
//        accident.setRules(ruleSet);

        for (int id : rIds) {
            accident.addSetRules(getRuleForId(id));
        }

        try (Session session = sf.openSession()) {
            if (idAcc != 0) {
                session.update(accident);
            } else {
                session.save(accident);
            }
        }
    }

    public List<Accident> getAllAccident() {
        try (Session session = sf.openSession()) {
            String queryTxt = "from ru.job4j.accident.model.Accident acc join fetch acc.rules";
            var query = session.createQuery(queryTxt, Accident.class);
            return query.list();
        }
    }

    public List<AccidentType> getAllAccidentType() {
        try (Session session = sf.openSession()) {
            return session.createQuery("from ru.job4j.accident.model.AccidentType", AccidentType.class).list();
        }
    }

    public List<Rule> getAllRule() {
        try (Session session = sf.openSession()) {
            return session.createQuery("from ru.job4j.accident.model.Rule", Rule.class).list();
        }
    }

    public AccidentType getAccidentType(int id) {
        try (Session session = sf.openSession()) {
            return session.get(AccidentType.class, id);
        }
    }

    public Rule getRuleForId(int id) {
        try (Session session = sf.openSession()) {
            return session.get(Rule.class, id);
        }
    }

    public List<Rule> getRuleForIds(int[] rIds) {
        List<String> lstIdsRule = new ArrayList<>();
        for (int idR : rIds) {
            lstIdsRule.add(String.valueOf(idR));
        }
        String queryIdsRule = String.join(",", lstIdsRule);
        String queryTxt = String.format("from ru.job4j.accident.model.Rule rul where rul.id IN (%s)", queryIdsRule);
        try (Session session = sf.openSession()) {
            return session.createQuery(queryTxt, Rule.class).list();
        }
    }
}